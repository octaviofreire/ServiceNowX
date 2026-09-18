# Evidência — Aula 04

## Aula
Aula 04 — Arquitetura, separação de responsabilidades, refatoração, teste e ADR

## Tema
ServiceNowX — ciclo de vida do chamado, SLA, atribuição e resolução.

## Problemas observados

- SLA calculado com datas fixas.
- Atribuição ignorando disponibilidade do técnico.
- Resolução sem liberação do técnico.
- Resolução permitida sem técnico atribuído.

## Alterações realizadas

1. Cálculo de SLA baseado em `openedAt`.
2. Filtro de técnicos disponíveis no `AssignmentService`.
3. Liberação do técnico e redução de `activeTickets` na resolução.
4. Validação de técnico obrigatório na resolução.
5. Inclusão de testes JUnit 5 para as quatro regras.
6. Configuração Maven/JUnit no `pom.xml`.

## Critérios de aceitação

- SLA usa a data real de abertura.
- Técnico indisponível não é elegível.
- Resolução libera o técnico.
- Resolução sem técnico é rejeitada.
- Testes automatizados cobrem as regras.

## Arquivos principais

- `src/main/java/br/edu/servicenowx/service/ServiceDeskService.java`
- `src/main/java/br/edu/servicenowx/patterns/strategy/AssignmentService.java`
- `src/main/java/br/edu/servicenowx/model/Technician.java`
- `src/test/java/br/edu/servicenowx/service/ServiceDeskServiceTest.java`
- `docs/adr/ADR-004-refatoracao-responsabilidades.md`
- `docs/requisitos_iniciais.md`
- `docs/backlog_aula05.md`

## Verificação

Executar:

```bash
mvn clean compile
mvn clean test
```

A entrega deve apresentar `BUILD SUCCESS`.

## GitHub

- Issue: criar/usar `Aula 04 — Refatoração e responsabilidades`
- Branch: `aula-04`
- Commits sugeridos:
  - `fix: corrigir calculo de SLA`
  - `fix: respeitar disponibilidade dos tecnicos`
  - `fix: liberar tecnico ao resolver chamado`
  - `test: adicionar testes das regras do ServiceNowX`
  - `docs: registrar ADR e requisitos`
- Pull Request: relacionar a Issue da aula.
