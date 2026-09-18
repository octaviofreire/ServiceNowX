# Backlog técnico — Aula 05

## Objetivo

Transformar os achados levantados anteriormente em itens de backlog verificáveis, priorizados por risco e impacto no ServiceNowX.

## Kanban

| Backlog | Prioridade | Status | Evidência |
|---|---|---|---|
| BL-01 Corrigir cálculo de SLA | Alta | Concluído | Teste `deveCalcularSlaAPartirDaAberturaReal` |
| BL-02 Impedir atribuição para técnico indisponível | Alta | Concluído | Teste `deveIgnorarTecnicoIndisponivelNaAtribuicao` |
| BL-03 Liberar técnico após resolução | Alta | Concluído | Teste `deveLiberarTecnicoAoResolverChamado` |
| BL-04 Impedir resolução sem técnico | Alta | Concluído | Teste `naoDeveResolverChamadoSemTecnico` |
| BL-05 Documentar RF e RNF | Média | Concluído | `docs/requisitos_iniciais.md` |
| BL-06 Registrar decisão arquitetural | Média | Concluído | `docs/adr/ADR-004-refatoracao-responsabilidades.md` |
| BL-07 Executar compilação e testes Maven | Alta | A fazer na entrega | Saída do `mvn clean compile` e `mvn clean test` |

## Itens detalhados

### BL-01 — Corrigir cálculo de SLA

**Problema:** `calculateDue()` utilizava datas fixas.

**Alteração:** calcular a data de vencimento a partir de `openedAt`.

**Critério de aceitação:** para `CRITICAL` aberto em `2026-09-09T19:00:00`, `dueAt` deve ser `2026-09-09T21:00:00`.

### BL-02 — Respeitar disponibilidade

**Problema:** técnico ocupado podia ser selecionado.

**Alteração:** `AssignmentService` filtra técnicos com `available=true` antes de escolher.

**Critério de aceitação:** técnico indisponível nunca deve ser retornado por `assign()`.

### BL-03 — Liberar técnico

**Problema:** resolução não alterava a carga nem disponibilidade.

**Alteração:** resolução decrementa `activeTickets` e define `available=true`.

**Critério de aceitação:** após resolver, o técnico deve estar disponível e com carga reduzida.

### BL-04 — Exigir técnico para resolução

**Problema:** chamado sem técnico podia ser resolvido.

**Alteração:** `resolve()` lança `IllegalStateException`.

**Critério de aceitação:** chamado sem `technicianId` permanece com status `OPEN`.

### BL-05 — Requisitos

**Critério de aceitação:** existência de 10 RF e 10 RNF com critérios verificáveis.

### BL-06 — ADR

**Critério de aceitação:** ADR deve registrar contexto, decisão, alternativas e consequências.

### BL-07 — Verificação

**Critério de aceitação:** compilação e testes devem terminar com sucesso.

## Sugestão de colunas no GitHub Projects/Kanban

`Backlog` → `A Fazer` → `Em andamento` → `Em revisão` → `Concluído`

Itens BL-01 a BL-06 podem ser movidos para `Concluído` após revisão. BL-07 depende da execução local do Maven.
