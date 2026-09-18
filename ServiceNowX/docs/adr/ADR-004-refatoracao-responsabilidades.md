# ADR-004 — Correção das responsabilidades de SLA, atribuição e resolução

## Status
Aceita

## Contexto

A análise do ServiceNowX identificou quatro problemas de comportamento relacionados ao ciclo de vida dos chamados:

1. O SLA utilizava datas fixas e ignorava `openedAt`.
2. A atribuição podia selecionar técnico indisponível.
3. A resolução não liberava o técnico nem reduzia `activeTickets`.
4. Um chamado sem técnico podia ser marcado como resolvido.

Esses problemas comprometem a consistência do domínio e tornam os critérios de aceitação difíceis de verificar.

## Decisão

Refatorar as responsabilidades sem introduzir tecnologia ou padrão desnecessário:

- `ServiceDeskService.calculateDue()` passa a calcular `dueAt` usando `openedAt` e a política de SLA da prioridade.
- `AssignmentService` passa a considerar somente técnicos disponíveis antes das regras de especialidade/prioridade/estratégia.
- `ServiceDeskService.resolve()` valida a existência de técnico, decrementa `activeTickets` e restaura `available=true`.
- As regras críticas passam a ter testes JUnit 5 automatizados.

## Antes e depois

| Elemento | Antes | Depois |
|---|---|---|
| SLA | Datas hardcoded | Data de abertura + horas de SLA |
| AssignmentService | Podia receber técnicos ocupados | Recebe somente técnicos disponíveis |
| assign() | Marca técnico como ocupado | Mantém estado consistente após seleção |
| resolve() | Resolvia sem técnico | Rejeita chamado sem técnico |
| resolve() | Não liberava técnico | Decrementa carga e libera técnico |
| Testes | Sem cobertura das regras | Testes para SLA, disponibilidade e resolução |

## Critérios de aceitação

- `dueAt` deve mudar de acordo com `openedAt`.
- Técnico com `available=false` não pode ser escolhido.
- Ao resolver, `activeTickets` deve diminuir e `available` deve voltar para `true`.
- Chamado sem técnico deve ser rejeitado.
- `mvn clean test` deve executar os testes sem falhas.

## Consequências

### Positivas
- Regras de negócio ficam verificáveis.
- Estado dos técnicos permanece consistente.
- O SLA deixa de depender de datas fixas.
- O comportamento crítico possui testes automatizados.

### Negativas
- O cálculo atual ainda é baseado em horas corridas; regras de horário comercial podem exigir nova decisão futura.
- Chamados sem técnico agora geram erro explícito na resolução.

## Alternativas consideradas

1. Manter as datas fixas — rejeitada porque não representa o momento real de abertura.
2. Filtrar técnicos somente em `ServiceDeskService` — rejeitada porque deixaria `AssignmentService` inseguro quando usado diretamente.
3. Criar uma nova camada de framework — rejeitada por adicionar complexidade sem necessidade para o problema atual.

## Rastreabilidade

Achados relacionados: SLA, disponibilidade de técnico, liberação de técnico e resolução sem atribuição.

Backlog relacionado: `BL-01` a `BL-04`.
