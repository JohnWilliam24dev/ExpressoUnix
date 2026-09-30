# API — ExpressoUnix

Base URL local: `http://localhost:8080`

Todos os recursos seguem o mesmo padrão CRUD:

| Método | Rota | Sucesso | Descrição |
|---|---|---|---|
| POST | `/{recurso}` | `201 Created` | Cria (o `id` do body é ignorado) |
| GET | `/{recurso}/{id}` | `200 OK` | Busca por ID |
| GET | `/{recurso}` | `200 OK` | Lista todos |
| PUT | `/{recurso}/{id}` | `204 No Content` | Atualiza (o `id` da URL é a fonte de verdade) |
| DELETE | `/{recurso}/{id}` | `204 No Content` | Remove |

Recursos: `/veiculo`, `/viagem`, `/assento`, `/passageiro`, `/funcionario`, `/passagem`, `/venda`, `/rota`.

Exceção ao padrão: `POST /rota` devolve a rota criada (com `id`) no body.

Relacionamentos são sempre referenciados por ID (`idVeiculo`, `idViagem`, `idAssento`...), tanto na entrada quanto na saída.

## Erros

Formato padrão:

```json
{
  "timestamp": "2026-09-29T10:00:00",
  "status": 400,
  "erro": "Bad Request",
  "mensagem": "Dados invalidos na requisicao",
  "campos": { "capacidade": "capacidade maxima e 60 assentos" }
}
```

`campos` só aparece em erros de validação.

| Status | Quando |
|---|---|
| 400 | Validação do body, JSON malformado, parâmetro inválido ou regra de negócio violada |
| 404 | Recurso (ou recurso referenciado) não existe |
| 409 | Conflito: assento já ocupado, registro duplicado ou em uso (constraint do banco) |
| 500 | Erro inesperado |

## Payloads

### Veículo
```json
{ "classe": "Executivo", "capacidade": 44, "statusVeiculo": "Disponivel" }
```
`classe`: `Convencional`, `Executivo`, `SemiLeito`, `Leito`, `Premium` · `capacidade`: 2 a 60 · `statusVeiculo`: `Disponivel`, `Indisponivel`

### Viagem
```json
{ "idVeiculo": 1, "dataViagem": "2026-10-15", "horaViagem": "08:30:00", "origem": "Feira de Santana", "destino": "Salvador" }
```
Ao criar, os assentos `1..capacidade` do veículo são gerados automaticamente como `Livre`. Veículo `Indisponivel` não pode ser usado.

### Assento
```json
{ "idViagem": 1, "numeroAssento": 10, "statusAssento": "Livre" }
```

### Passageiro
```json
{ "nome": "Maria Silva", "email": "maria@email.com", "telefone": "75999999999", "cpf": "12345678901", "dataNascimento": "1990-05-20" }
```
`cpf`: 11 dígitos numéricos.

### Funcionário
```json
{ "nome": "Joao", "email": "joao@empresa.com", "telefone": "75988888888", "cpf": "12345678901", "dataNascimento": "1985-01-10", "cargo": "Bilheteiro" }
```

### Passagem
```json
{
  "status": "Valido", "idViagem": 1, "idAssento": 10, "idPassageiro": 1,
  "dataPassagem": "2026-10-15", "horaPassagem": "08:30:00",
  "origem": "Feira de Santana", "destino": "Salvador"
}
```
`distancia` e `preco` **não são aceitos no body**: o servidor calcula (rota × classe × tarifa) e devolve nas consultas.

### Rota
```json
{ "origem": "Feira de Santana", "destino": "Salvador", "distanciaKm": 108.50, "precoBase": 45.00 }
```
Origem e destino não podem ser iguais (400); o par origem/destino é único, sem diferenciar maiúsculas (409).

### Tabela de preços
`GET /tabela-preco` lista o multiplicador de cada classe. `PUT /tabela-preco/{classe}` (204) atualiza:
```json
{ "multiplicador": 1.25 }
```
Na primeira subida a tabela é populada com: Convencional 1.00, Executivo 1.25, SemiLeito 1.50, Leito 1.80, Premium 2.20.

### Cotação de preço
`GET /preco/cotacao?idViagem=1&tipoTarifa=INTEIRA` (`tipoTarifa` é opcional; hoje só existe `INTEIRA`).

`preco = precoBase da rota × multiplicador da classe do veículo × percentual da tarifa`, arredondado a 2 casas (HALF_UP). Retorna `400` se não houver rota cadastrada para origem/destino da viagem.

### Venda
```json
{ "idFuncionario": 1, "idPassagem": 1 }
```
`horarioEmissao` é preenchido pelo banco e devolvido nas consultas.

## Regras de negócio

- Emitir uma passagem exige que o assento pertença à viagem (400) e esteja `Livre` (409); o assento passa a `Ocupado` na mesma transação.
- Excluir uma passagem libera o assento.
- Não é permitido trocar viagem ou assento de uma passagem existente via `PUT` (400): exclua e emita outra.
- Um assento só pode ter uma passagem por viagem (constraint `id_viagem + id_assento`); violação resulta em 409.
- Venda exige funcionário e passagem existentes (404).
