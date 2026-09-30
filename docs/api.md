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

Exceções ao padrão CRUD:
- `POST /rota` e `POST /venda` devolvem o recurso criado (com `id`) no body.
- `/venda` e `/passagem` são **somente criação via venda + consulta**: `POST /venda` (cria venda com passagens), `GET /venda`, `GET /venda/{id}`, `GET /passagem`, `GET /passagem/{id}`. Não há `PUT`/`DELETE` (cancelamento entra no bloco de cancelamento).

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

### Passagem (somente saída)
```json
{
  "id": 7, "idVenda": 3, "tipoTrecho": "IDA", "idPassagemVinculada": null, "status": "Emitida",
  "idViagem": 1, "idAssento": 10, "idPassageiro": 1,
  "dataPassagem": "2026-10-15", "horaPassagem": "08:30:00",
  "origem": "Feira de Santana", "destino": "Salvador", "distancia": 108.50,
  "tipoTarifa": "INTEIRA", "tarifaBase": 56.25, "desconto": 0.00, "valorPago": 56.25
}
```
`status`: `Reservada`, `Emitida`, `Utilizada`, `Cancelada`, `Remarcada`, `Expirada` (hoje só `Emitida` é gerado). `tipoTrecho`: `AVULSA`, `IDA`, `VOLTA`. `origem`, `destino`, data e hora são copiados da viagem pelo servidor.

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
`POST /venda` cria a venda com N passagens em uma única transação (tudo ou nada). O body **não aceita preço**:
```json
{
  "idFuncionario": 1,
  "itens": [
    { "tipoTrecho": "IDA",   "idViagem": 10, "idAssento": 101, "idPassageiro": 5 },
    { "tipoTrecho": "VOLTA", "idViagem": 18, "idAssento": 220, "idPassageiro": 5, "vinculadaAoItem": 0 }
  ],
  "pagamentos": [
    { "forma": "DINHEIRO", "valor": 90.00, "valorRecebido": 100.00 }
  ]
}
```
`tipoTarifa` é opcional por item (padrão `INTEIRA`). `vinculadaAoItem` é o índice (base 0) do item `IDA` na mesma lista e só vale para `VOLTA`. `idFuncionario` no body é **temporário**: sai quando a autenticação entrar (VEN-08).

Resposta `201`:
```json
{
  "id": 3, "horarioEmissao": "2026-10-01T10:00:00", "idFuncionario": 1, "status": "Finalizada",
  "valorTotal": 112.50, "descontoTotal": 0.00,
  "passagens": [ { "id": 7, "tipoTrecho": "IDA", "...": "..." }, { "id": 8, "tipoTrecho": "VOLTA", "idPassagemVinculada": 7, "...": "..." } ],
  "pagamentos": [
    { "id": 1, "idVenda": 3, "forma": "DINHEIRO", "valor": 90.00, "valorRecebido": 100.00, "troco": 10.00, "status": "Aprovado", "horario": "2026-10-01T10:00:00" }
  ]
}
```
`forma`: `DINHEIRO`, `DEBITO`, `CREDITO`, `PIX`. Não há gateway: todo pagamento válido é registrado como `Aprovado`. Como os pagamentos precisam cobrir o total na própria requisição, a venda já nasce `Finalizada` e as passagens `Emitida`.

## Regras de negócio

- Uma venda tem 1..N passagens. Ida e volta são **passagens independentes**, compradas juntas; o vínculo (`tipoTrecho` + `idPassagemVinculada`) é só informativo.
- Toda a venda é atômica: se qualquer item falhar, nenhuma passagem é emitida e nenhum assento é ocupado.
- Cada item exige viagem, passageiro e assento existentes (404); o assento deve pertencer à viagem (400) e estar `Livre` (409). O assento passa a `Ocupado` na mesma transação.
- O mesmo assento da mesma viagem não pode aparecer duas vezes na venda (400).
- `VOLTA` exige `vinculadaAoItem` apontando para um item `IDA`; toda `IDA` exige exatamente uma `VOLTA`; `AVULSA` e `IDA` não aceitam `vinculadaAoItem` (400).
- A viagem da volta deve partir depois da ida (400). Com `expressounix.venda.volta-origem-igual-destino-ida=true` (padrão), a origem da volta também deve ser o destino da ida.
- Um assento só pode ter uma passagem por viagem (constraint `id_viagem + id_assento`); violação resulta em 409.
- Venda exige funcionário existente (404).
- O preço total é calculado pelo servidor. A soma dos `valor` dos pagamentos deve ser **exatamente** esse total (400 se faltar ou sobrar); a sobra só existe como troco de dinheiro.
- `DINHEIRO` exige `valorRecebido` maior ou igual ao `valor`, e `troco = valorRecebido - valor`. Nas demais formas, `valorRecebido` não pode ser informado (400).
- Uma venda aceita mais de um pagamento (pagamento misto).
