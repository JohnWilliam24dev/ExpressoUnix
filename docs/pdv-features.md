# ExpressoUnix PDV — Features

> Projeto de prova de conhecimento. Este documento descreve o escopo alvo de um PDV (ponto de venda) de passagens rodoviárias sobre a base atual do ExpressoUnix.
> Emissão fiscal (BP-e) e integrações externas estão **fora do escopo** (ver [Fora do escopo](#fora-do-escopo)).

## Sumário

1. [Decisões de modelagem](#1-decisões-de-modelagem)
2. [Modelo de domínio alvo](#2-modelo-de-domínio-alvo)
3. [Features por módulo](#3-features-por-módulo)
4. [Endpoints propostos](#4-endpoints-propostos)
5. [Requisitos não funcionais](#5-requisitos-não-funcionais)
6. [Roadmap](#6-roadmap)
7. [Decisões em aberto](#7-decisões-em-aberto)
8. [Fora do escopo](#fora-do-escopo)

Legenda de prioridade: **MVP** (fase 1) · **F2** (fase 2) · **F3** (fase 3).
Legenda de situação: ✅ existe · 🟡 existe parcialmente · ❌ não existe.

---

## 1. Decisões de modelagem

### 1.1 Uma venda tem várias passagens

`Venda` deixa de ser 1:1 com `Passagem`. Uma venda é o **ato comercial** (quem vendeu, quando, quanto, como foi pago) e agrupa 1..N passagens.

```
Venda 1 ──── N Passagem
```

Hoje a `Venda` aponta para uma passagem (`idPassagem`). No alvo, é a `Passagem` que aponta para a venda (`idVenda`).

### 1.2 Ida e volta são passagens diferentes, compradas juntas

Esta é a regra mais importante do modelo:

- **Ida e volta são duas `Passagem` distintas**, cada uma com sua própria viagem, assento, preço, status, código de embarque (QR Code) e histórico.
- Podem ser **compradas juntas** na mesma `Venda` (um único pagamento), mas **não são acopladas sistemicamente**.
- O vínculo entre elas é apenas informativo (`tipoTrecho` + `idPassagemVinculada`) e nunca comanda o comportamento de nenhuma das duas.

| Aspecto | Comportamento |
|---|---|
| Cancelamento | Cancelar a ida **não** cancela a volta (e vice-versa). |
| Remarcação | Cada passagem é remarcada de forma independente. |
| Embarque | Cada passagem é validada no embarque da sua própria viagem. |
| Reembolso | Calculado **por passagem**, com base no valor efetivamente pago por ela. |
| Assento | Cada passagem ocupa o assento da sua própria viagem. |
| Status | Cada passagem tem seu próprio ciclo de vida. |

**Desconto de ida e volta:** se houver promoção para compra conjunta, o desconto é **rateado** entre as passagens no momento da venda, e cada passagem guarda o `valorPago` final. Assim, um reembolso individual nunca depende da outra passagem.

**Tipos de trecho:**

| `tipoTrecho` | Significado |
|---|---|
| `AVULSA` | Passagem única, sem vínculo. |
| `IDA` | Trecho de ida de uma compra conjunta. |
| `VOLTA` | Trecho de volta; guarda `idPassagemVinculada` apontando para a ida. |

### 1.3 Preço sempre calculado no servidor

O cliente (front) nunca envia o valor. Ele informa o que quer comprar; o servidor calcula com base em rota, classe do veículo e tipo de tarifa.

### 1.4 Funcionário vem do usuário autenticado

O `idFuncionario` da venda é o do operador logado, nunca um campo do body.

---

## 2. Modelo de domínio alvo

```
Rota ─────────── TabelaPreco (por classe / tipo de tarifa)
  │
Viagem ── N Assento
  │            │
  │            │ (assento da viagem)
  └──── N Passagem ── 1 Passageiro
              │
              │ N:1
            Venda ── 1 Funcionario (operador)
              │  └── 1 Caixa
              │
              ├── N Pagamento
              └── (cada Passagem) ── 0..N Cancelamento/Estorno
                                  └─ 0..1 Reserva (antes da emissão)
```

### Entidades novas e alterações

| Entidade | Situação | Campos principais |
|---|---|---|
| `Venda` | 🟡 alterar | `id`, `idFuncionario`, `idCaixa`, `horarioEmissao`, `status`, `valorTotal`, `descontoTotal` |
| `Passagem` | 🟡 alterar | `idVenda`, `tipoTrecho`, `idPassagemVinculada`, `idViagem`, `idAssento`, `idPassageiro`, `tipoTarifa`, `tarifaBase`, `desconto`, `valorPago`, `status`, `codigoEmbarque` |
| `Pagamento` | ❌ nova | `idVenda`, `forma`, `valor`, `valorRecebido`, `troco`, `status`, `horario` |
| `Rota` | ❌ nova | `origem`, `destino`, `distanciaKm`, `precoBase` |
| `TabelaPreco` / regra | ❌ nova | multiplicador por `Classe`, percentual por `tipoTarifa` |
| `Reserva` | ❌ nova | `idViagem`, `idAssento`, `idFuncionario`, `expiraEm` |
| `Cancelamento` | ❌ nova | `idPassagem`, `motivo`, `taxa`, `valorReembolsado`, `horario`, `idFuncionario` |
| `Caixa` | ❌ nova | `idFuncionario`, `abertura`, `fechamento`, `saldoInicial`, `saldoFinal`, `status` |
| `MovimentoCaixa` | ❌ nova | `idCaixa`, `tipo` (VENDA, ESTORNO, SANGRIA, SUPRIMENTO), `valor`, `horario`, `referencia` |
| `Usuario` | ❌ nova | credenciais, `papel`, vínculo com `Funcionario` |

### Ciclo de vida da passagem

```
Reservada ──(pagamento confirmado)──► Emitida ──(embarque)──► Utilizada
    │                                    │
    │ (expirou / liberada)               ├──(cancelamento)──► Cancelada
    ▼                                    └──(remarcação)────► Remarcada ──► (nova Passagem Emitida)
 Expirada
```

Substitui o enum atual `StatusPassagem { Valido, Invalido }`.

### Ciclo de vida da venda

```
Aberta ──(pagamentos cobrem o total)──► Finalizada
   │                                        │
   └──(abandonada)──► Cancelada             ├──(todas as passagens canceladas)──► Cancelada
                                            └──(algumas canceladas)──► ParcialmenteCancelada
```

---

## 3. Features por módulo

### 3.1 Catálogo e consulta

| ID | Feature | Prioridade | Situação | Regras / critérios de aceite |
|---|---|---|---|---|
| CAT-01 | Cadastro de veículos (classe, capacidade 2–60, status) | MVP | ✅ | Capacidade validada; veículo indisponível não pode ser usado em viagem. |
| CAT-02 | Cadastro de viagens (veículo, data, hora, origem, destino) | MVP | ✅ | Ao criar, gera assentos `1..capacidade` como `Livre`, na mesma transação. |
| CAT-03 | Cadastro de rotas (origem, destino, distância, preço base) | MVP | ✅ | Substitui a distância obtida por scraping no handler Fastify como fonte de preço. |
| CAT-04 | Tabela de preços por classe e tipo de tarifa | MVP | ✅ | Preço = `precoBase` × multiplicador da classe × percentual do tipo de tarifa. |
| CAT-05 | Busca de viagens por origem, destino e data | MVP | ❌ | Retorna apenas viagens futuras, com quantidade de assentos livres e preço calculado. |
| CAT-06 | Mapa de assentos da viagem | MVP | ❌ | Lista todos os assentos com status (`Livre`, `Reservado`, `Ocupado`). |
| CAT-07 | Paginação e ordenação nas listagens | F2 | ❌ | Todas as listagens `GET /recurso` aceitam `page`, `size`, `sort`. |
| CAT-08 | Cancelamento/alteração de viagem pela empresa | F3 | ❌ | Passagens afetadas são sinalizadas para remarcação ou reembolso integral. |

### 3.2 Passageiros e funcionários

| ID | Feature | Prioridade | Situação | Regras / critérios de aceite |
|---|---|---|---|---|
| PES-01 | Cadastro de passageiros | MVP | ✅ | CPF com 11 dígitos, e-mail válido, nascimento no passado. |
| PES-02 | Cadastro de funcionários | MVP | ✅ | Mesmas validações de dados pessoais. |
| PES-03 | Busca de passageiro por CPF | MVP | ❌ | Agiliza a venda no balcão; evita cadastro duplicado. |
| PES-04 | Unicidade de CPF | MVP | ❌ | Segundo cadastro com o mesmo CPF retorna `409`. |
| PES-05 | Validação de dígitos verificadores do CPF | F2 | ❌ | Rejeita CPF matematicamente inválido. |
| PES-06 | Histórico de viagens do passageiro | F3 | ❌ | Lista de passagens por passageiro. |

### 3.3 Venda

| ID | Feature | Prioridade | Situação | Regras / critérios de aceite |
|---|---|---|---|---|
| VEN-01 | Venda com N passagens em uma única operação atômica | MVP | ❌ | Tudo ou nada: se um item falhar, nenhuma passagem é emitida e nenhum assento é ocupado. |
| VEN-02 | Compra de **ida e volta** na mesma venda | MVP | ❌ | Gera duas passagens independentes (`IDA` e `VOLTA`) vinculadas apenas informativamente. Ver [1.2](#12-ida-e-volta-são-passagens-diferentes-compradas-juntas). |
| VEN-03 | Validação de coerência ida × volta | MVP | ❌ | Viagem da volta deve ser posterior à da ida. Origem da volta = destino da ida (regra configurável). |
| VEN-04 | Assento deve pertencer à viagem e estar livre | MVP | ✅ | Pertencimento → `400`; ocupado → `409`. |
| VEN-05 | Cálculo de preço no servidor | MVP | ✅ | O body não contém preço; o servidor calcula por passagem. |
| VEN-06 | Tipos de tarifa (inteira, meia, gratuidade) | F2 | ❌ | Percentuais parametrizáveis; passagem guarda `tarifaBase`, `desconto` e `valorPago`. |
| VEN-07 | Desconto promocional de ida e volta | F2 | ❌ | Rateado entre as duas passagens no momento da venda. |
| VEN-08 | Operador vem do usuário autenticado | MVP | ❌ | `idFuncionario` não é aceito no body. |
| VEN-09 | Venda vinculada ao caixa aberto do operador | MVP | ❌ | Sem caixa aberto, a venda é recusada (`409`). |
| VEN-10 | Consulta de venda com suas passagens | MVP | 🟡 | Retorna a venda e a lista de passagens (cada uma com seu status). |
| VEN-11 | Limite de passagens por venda | F2 | ❌ | Parametrizável (ex.: máximo de 10). |
| VEN-12 | Um mesmo passageiro não pode ter duas passagens ativas na mesma viagem | F2 | ❌ | Evita venda duplicada acidental. |

### 3.4 Reserva temporária de assento

| ID | Feature | Prioridade | Situação | Regras / critérios de aceite |
|---|---|---|---|---|
| RES-01 | Reservar assento por tempo limitado | F2 | ❌ | Assento fica `Reservado` durante o atendimento; expira automaticamente. |
| RES-02 | Liberação automática ao expirar | F2 | ❌ | Job agendado libera reservas vencidas. |
| RES-03 | Liberação manual da reserva | F2 | ❌ | Operador desiste; assento volta a `Livre`. |
| RES-04 | Confirmação converte reserva em passagem emitida | F2 | ❌ | Reserva do próprio operador é consumida na venda. |

### 3.5 Pagamento

| ID | Feature | Prioridade | Situação | Regras / critérios de aceite |
|---|---|---|---|---|
| PAG-01 | Registro de pagamento por forma (dinheiro, débito, crédito, Pix) | MVP | ❌ | Soma dos pagamentos deve cobrir o `valorTotal` da venda. |
| PAG-02 | Pagamento em dinheiro com cálculo de troco | MVP | ❌ | `troco = valorRecebido − valor`; nunca negativo. |
| PAG-03 | Pagamento misto (mais de uma forma na mesma venda) | F2 | ❌ | Lista de pagamentos por venda. |
| PAG-04 | Pagamento simulado (sem gateway real) | MVP | ❌ | Cartão/Pix são registrados como aprovados; como é projeto didático, não há integração externa. |
| PAG-05 | Estorno vinculado a cancelamento | F2 | ❌ | Gera `Pagamento` negativo ou `MovimentoCaixa` de estorno. |

### 3.6 Cancelamento, reembolso e remarcação

Todas as operações abaixo atuam **por passagem**, nunca sobre a venda inteira ou sobre a passagem "par" de uma compra de ida e volta.

| ID | Feature | Prioridade | Situação | Regras / critérios de aceite |
|---|---|---|---|---|
| CAN-01 | Cancelar uma passagem | MVP | ❌ | Passagem vai para `Cancelada`, assento volta a `Livre`, na mesma transação. |
| CAN-02 | Cancelar ida sem afetar a volta (e vice-versa) | MVP | ❌ | A outra passagem permanece `Emitida`. |
| CAN-03 | Taxa de cancelamento por antecedência | F2 | ❌ | Faixas parametrizáveis (ex.: mais de 24 h, menos de 24 h, após a partida). |
| CAN-04 | Reembolso calculado sobre o `valorPago` da passagem | F2 | ❌ | Independe do desconto de ida e volta, pois já foi rateado. |
| CAN-05 | Registro de cancelamento (motivo, operador, valor) | F2 | ❌ | Histórico auditável. |
| CAN-06 | Não cancelar passagem já utilizada ou de viagem já partida | MVP | ❌ | Retorna `409`. |
| REM-01 | Remarcar passagem para outra viagem | F3 | ❌ | Cancela logicamente a original (`Remarcada`) e emite nova passagem; diferença de preço é cobrada ou devolvida. |
| REM-02 | Remarcar ida sem afetar a volta | F3 | ❌ | Independência total; validação de coerência ida × volta é reaplicada. |

### 3.7 Embarque

| ID | Feature | Prioridade | Situação | Regras / critérios de aceite |
|---|---|---|---|---|
| EMB-01 | Código de embarque único por passagem | F2 | ❌ | Gerado na emissão; cada passagem (ida e volta) tem o seu. |
| EMB-02 | Validação de embarque por código | F2 | ❌ | Só passagens `Emitida` da viagem correta e do dia; marca como `Utilizada`. |
| EMB-03 | Bloqueio de reuso de código | F2 | ❌ | Segunda leitura retorna `409`. |
| EMB-04 | Comprovante (bilhete) com QR Code | F3 | ❌ | Uma via por passagem; venda com ida e volta gera dois bilhetes. |
| EMB-05 | Lista de passageiros da viagem (manifesto) | F3 | ❌ | Somente passagens `Emitida`/`Utilizada`. |

### 3.8 Caixa

| ID | Feature | Prioridade | Situação | Regras / critérios de aceite |
|---|---|---|---|---|
| CXA-01 | Abertura de caixa com saldo inicial | MVP | ❌ | Um operador tem no máximo um caixa aberto. |
| CXA-02 | Movimentos automáticos por venda e estorno | MVP | ❌ | Cada pagamento em dinheiro gera movimento. |
| CXA-03 | Sangria e suprimento | F2 | ❌ | Registro manual com valor e justificativa. |
| CXA-04 | Fechamento de caixa com conferência | MVP | ❌ | Compara saldo esperado × informado e registra diferença. |
| CXA-05 | Relatório do caixa por período | F3 | ❌ | Totais por forma de pagamento. |

### 3.9 Segurança e acesso

| ID | Feature | Prioridade | Situação | Regras / critérios de aceite |
|---|---|---|---|---|
| SEG-01 | Autenticação (login) com Spring Security + JWT | MVP | ❌ | Sem token válido, endpoints (exceto login) retornam `401`. |
| SEG-02 | Papéis: `BILHETEIRO`, `GERENTE`, `ADMIN` | MVP | ❌ | Bilheteiro vende e cancela do próprio caixa; gerente cancela qualquer venda e vê relatórios; admin gerencia cadastros. |
| SEG-03 | Senha armazenada com hash (BCrypt) | MVP | ❌ | Nunca em texto puro. |
| SEG-04 | Auditoria de operações sensíveis | F3 | ❌ | Venda, cancelamento, estorno e fechamento registram quem, quando e o quê. |
| SEG-05 | Proteção de dados pessoais (LGPD) | F3 | ❌ | CPF mascarado em listagens; acesso restrito por papel. |

### 3.10 Relatórios

| ID | Feature | Prioridade | Situação | Regras / critérios de aceite |
|---|---|---|---|---|
| REL-01 | Vendas por período e por operador | F3 | ❌ | Totais e quantidade de passagens. |
| REL-02 | Ocupação por viagem | F3 | ❌ | Percentual de assentos ocupados. |
| REL-03 | Receita por rota e por classe | F3 | ❌ | Agregações sobre `valorPago`. |
| REL-04 | Cancelamentos e reembolsos por período | F3 | ❌ | Total reembolsado e taxas retidas. |

### 3.11 Base técnica (já existente e evolução)

| ID | Feature | Prioridade | Situação | Observação |
|---|---|---|---|---|
| TEC-01 | Tratamento global de exceções (400/404/409/500) | MVP | ✅ | `GlobalExceptionHandler` + `ApiError`. |
| TEC-02 | Bean Validation nos DTOs | MVP | ✅ | `@Valid` nos controllers. |
| TEC-03 | Contrato REST (201/204, ID da URL no PUT) | MVP | ✅ | Documentado em `docs/api.md`. |
| TEC-04 | POST retorna o recurso criado (ou `Location`) | MVP | ❌ | Necessário para o front conhecer o ID da venda e das passagens. |
| TEC-05 | Testes unitários e de integração dos fluxos críticos | MVP | ❌ | Venda com N passagens, ida e volta, cancelamento independente, assento duplicado, caixa. |
| TEC-06 | Migrações de banco (Flyway) no lugar de `ddl-auto=update` | F2 | ❌ | Necessário para evoluir o schema com segurança. |
| TEC-07 | Documentação OpenAPI/Swagger | F3 | ❌ | Complementa `docs/api.md`. |
| TEC-08 | Perfis de configuração (dev/prod) e segredos fora do repositório | F2 | ❌ | Remover credenciais de `application.properties`. |

---

## 4. Endpoints propostos

### Consulta

| Método | Rota | Descrição |
|---|---|---|
| GET | `/viagens/busca?origem=&destino=&data=` | Busca de viagens com assentos livres e preço |
| GET | `/viagens/{id}/assentos` | Mapa de assentos da viagem |
| GET | `/passageiros?cpf=` | Busca por CPF |

### Venda

| Método | Rota | Descrição |
|---|---|---|
| POST | `/vendas` | Cria venda com N passagens e pagamentos |
| GET | `/vendas/{id}` | Venda com passagens e pagamentos |
| GET | `/vendas?caixa={id}` | Vendas de um caixa |

Exemplo de **ida e volta** na mesma venda:

```json
{
  "itens": [
    { "tipoTrecho": "IDA",   "idViagem": 10, "idAssento": 101, "idPassageiro": 5, "tipoTarifa": "INTEIRA" },
    { "tipoTrecho": "VOLTA", "idViagem": 18, "idAssento": 220, "idPassageiro": 5, "tipoTarifa": "INTEIRA", "vinculadaAoItem": 0 }
  ],
  "pagamentos": [
    { "forma": "DINHEIRO", "valor": 90.00, "valorRecebido": 100.00 }
  ]
}
```

Resposta (resumo): venda `Finalizada` com **duas passagens independentes**, cada uma com seu `id`, `codigoEmbarque`, `valorPago` e `status`. `vinculadaAoItem` referencia o índice do item da ida no mesmo request; após a criação, o vínculo passa a ser `idPassagemVinculada`.

### Passagem

| Método | Rota | Descrição |
|---|---|---|
| GET | `/passagens/{id}` | Detalhe da passagem |
| POST | `/passagens/{id}/cancelamento` | Cancela **somente esta** passagem |
| POST | `/passagens/{id}/remarcacao` | Remarca **somente esta** passagem |
| POST | `/passagens/{id}/embarque` | Valida embarque pelo código |

### Reserva

| Método | Rota | Descrição |
|---|---|---|
| POST | `/reservas` | Reserva assento por tempo limitado |
| DELETE | `/reservas/{id}` | Libera reserva |

### Caixa

| Método | Rota | Descrição |
|---|---|---|
| POST | `/caixa/abertura` | Abre caixa do operador logado |
| POST | `/caixa/movimentos` | Sangria / suprimento |
| POST | `/caixa/fechamento` | Fecha com conferência |
| GET | `/caixa/atual` | Situação do caixa do operador |

### Auth

| Método | Rota | Descrição |
|---|---|---|
| POST | `/auth/login` | Retorna o token JWT |

---

## 5. Requisitos não funcionais

| ID | Requisito |
|---|---|
| NFR-01 | Operações compostas (venda, cancelamento, remarcação) são transacionais. |
| NFR-02 | Venda concorrente do mesmo assento nunca gera duas passagens ativas (constraint no banco + tratamento `409`). |
| NFR-03 | Erros seguem o formato `ApiError` em todos os endpoints. |
| NFR-04 | Valores monetários em `BigDecimal` com 2 casas; nunca `double`. |
| NFR-05 | Nenhum valor monetário aceito do cliente na venda: o servidor é a fonte de verdade. |
| NFR-06 | Cobertura de testes nos fluxos críticos (TEC-05) antes de qualquer PR para `main`. |

---

## 6. Roadmap

Ordem sugerida de implementação (cada bloco em commits na `develop`, com PR para `main` somente após validação local):

| Fase | Bloco | Itens |
|---|---|---|
| **1** | Preço | CAT-03, CAT-04, VEN-05 |
| **1** | Modelo de venda | Ajuste `Venda` 1:N `Passagem`, `tipoTrecho`, novo enum de status, VEN-01, VEN-02, VEN-03, VEN-04, TEC-04 |
| **1** | Pagamento | PAG-01, PAG-02, PAG-04 |
| **1** | Segurança | SEG-01, SEG-02, SEG-03, VEN-08 |
| **1** | Caixa | CXA-01, CXA-02, CXA-04, VEN-09 |
| **1** | Consulta | CAT-05, CAT-06, PES-03, PES-04 |
| **1** | Cancelamento básico | CAN-01, CAN-02, CAN-06 |
| **1** | Testes | TEC-05 |
| **2** | Tarifas e promoções | VEN-06, VEN-07, VEN-11, VEN-12 |
| **2** | Reserva | RES-01 a RES-04 |
| **2** | Cancelamento completo | CAN-03, CAN-04, CAN-05, PAG-03, PAG-05, CXA-03 |
| **2** | Embarque | EMB-01, EMB-02, EMB-03 |
| **2** | Base técnica | TEC-06, TEC-08, CAT-07, PES-05 |
| **3** | Remarcação | REM-01, REM-02 |
| **3** | Comprovante e manifesto | EMB-04, EMB-05 |
| **3** | Relatórios, auditoria, LGPD | REL-01 a REL-04, SEG-04, SEG-05, CXA-05, TEC-07, CAT-08, PES-06 |

---

## 7. Decisões em aberto

1. **Constraint de assento × passagens canceladas.** Hoje a unicidade `id_viagem + id_assento` vale para qualquer status, então uma passagem cancelada continuaria bloqueando o reuso do assento. Duas saídas:
   - **(a)** Coluna gerada que só recebe valor quando a passagem está ativa, com unicidade sobre ela. O MySQL permite vários `NULL` em índice único, então passagens canceladas não conflitam:
     ```sql
     ativo_chave BIGINT GENERATED ALWAYS AS (
         CASE WHEN status IN ('Reservada','Emitida','Utilizada') THEN id_assento ELSE NULL END
     ) STORED,
     UNIQUE (id_viagem, ativo_chave)
     ```
   - **(b)** Mover passagens canceladas para uma tabela de histórico.
   
   Recomendação: (a), por manter tudo em `Passagem` e preservar o histórico.

2. **Dados duplicados em `Passagem`.** `origem`, `destino`, `dataPassagem` e `horaPassagem` repetem informação que já está na `Viagem`. Avaliar removê-los e derivar da viagem, para evitar divergência (especialmente na remarcação).

3. **Como representar o vínculo ida × volta.** Opção adotada: `tipoTrecho` + `idPassagemVinculada` (nulo em `AVULSA` e `IDA`, apontando para a ida na `VOLTA`). Alternativa: uma entidade `PacoteIdaVolta`. A primeira é mais simples e mantém as passagens independentes; a segunda só vale se surgirem regras próprias do pacote.

4. **Regra de coerência da volta** (VEN-03): exigir que a origem da volta seja o destino da ida, ou apenas que a data/hora seja posterior? (ex.: o passageiro pode voltar por outra cidade).

5. **Faixas de taxa de cancelamento** (CAN-03): definir os valores e se variam por classe do veículo.

6. **Cancelamento de venda inteira:** oferecer um atalho que cancele todas as passagens de uma vez (chamando o cancelamento individual em cada uma) ou exigir sempre o cancelamento passagem a passagem?

---

## Fora do escopo

Por se tratar de projeto de prova de conhecimento, não fazem parte deste documento:

- Emissão de documento fiscal eletrônico (BP-e) e integração com a SEFAZ.
- Gateway de pagamento real (cartão e Pix são simulados).
- Impressora térmica e integração com hardware de PDV.
- Integração com terminais rodoviários ou ERPs de terceiros.
- Aplicativo mobile do passageiro (previsto em `Documentação/comercial.md`, mas separado do PDV).
