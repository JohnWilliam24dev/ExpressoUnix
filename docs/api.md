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

Recursos: `/veiculo`, `/viagem`, `/assento`, `/passageiro`, `/funcionario`, `/passagem`, `/venda`.

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
  "origem": "Feira de Santana", "destino": "Salvador", "distancia": 108.5, "preco": 45.00
}
```

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
