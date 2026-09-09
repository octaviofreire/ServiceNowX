# Evidência — Aula 03: Problemas de design no legado

**Branch:** `aula-03`
**Issue relacionada:** Aula 03 — Achados de design no legado (SLA, atribuição, resolução)

## Metodologia
Análise do código-fonte do pacote `br.edu.servicenowx`, com foco nas áreas indicadas
pela atividade: atribuição, SLA, resolução e monitoramento. Evidências coletadas por
leitura direta do código (comportamento real, não apenas nomes de classes/pacotes).

---

## Achado 1 — SLA calculado com datas fixas
**Problema observado:** `ServiceDeskService.calculateDue(priority, openedAt)` retorna
valores de data hardcoded (ex: `"2026-08-11T01:00:00"` para `CRITICAL`) e não utiliza
o parâmetro `openedAt` recebido. O prazo do SLA não reflete o momento real de abertura.

**Alteração realizada:** nenhuma nesta etapa — Aula 03 é fase de levantamento.
Correção prevista para a Aula 04.

**Critério de aceitação:** `dueAt` deve ser calculado somando as horas de SLA da
prioridade ao `openedAt` real do chamado.

---

## Achado 2 — Atribuição ignora disponibilidade do técnico
**Problema observado:** `ServiceDeskService.assign()` marca `tech.available=false`
após atribuir um chamado, mas `AssignmentService.assign()` nunca verifica
`tech.available` antes de escolher um técnico. Um técnico já ocupado pode receber
novo chamado.

**Alteração realizada:** nenhuma nesta etapa — prevista para a Aula 04.

**Critério de aceitação:** técnicos com `available == false` não devem ser elegíveis
para nova atribuição.

---

## Achado 3 — Resolução não libera o técnico
**Problema observado:** `ServiceDeskService.resolve()` não decrementa
`activeTickets` nem restaura `available=true` do técnico responsável. O workload do
técnico só cresce, nunca é liberado.

**Alteração realizada:** nenhuma nesta etapa — prevista para a Aula 04.

**Critério de aceitação:** ao resolver um chamado, o técnico responsável deve ter
`activeTickets` decrementado e `available` restaurado.

---

## Achado 4 — Resolução sem técnico atribuído
**Problema observado:** `ServiceDeskService.resolve()` não valida se
`t.technicianId != null` antes de marcar o chamado como `RESOLVED`. Um chamado nunca
atribuído pode ser resolvido.

**Alteração realizada:** nenhuma nesta etapa — prevista para a Aula 04.

**Critério de aceitação:** `resolve()` deve rejeitar ou sinalizar erro para chamados
sem técnico atribuído.

---

## Achado 5 — Observer notifica apenas um assinante
**Problema observado:** `TicketPublisher` armazena um único observer
(`private TicketObserver observer`). No construtor de `ServiceDeskService`, o segundo
`subscribe(new ManagerObserver())` substitui o primeiro (`RequesterObserver`) —
comentário no próprio código confirma: `// replaces requester`. O requester nunca é
notificado de fato.

**Alteração realizada:** nenhuma nesta etapa — prevista para a Aula 04.

**Critério de aceitação:** `TicketPublisher` deve notificar todos os observers
inscritos a cada evento publicado, não apenas o último.

---

## Compilação/teste
Projeto compila normalmente nesta etapa (`javac -d out $(find src/main/java -name "*.java")`).
Nenhuma alteração de código foi feita — apenas levantamento e documentação.