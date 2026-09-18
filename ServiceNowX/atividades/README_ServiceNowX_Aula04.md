# ServiceNowX — Aula 04

## Tema
Arquitetura, separação de responsabilidades, refatoração, teste e ADR.

## Problema escolhido

Foram selecionados quatro problemas reais do ciclo de vida dos chamados:

1. SLA calculado com datas fixas;
2. atribuição que ignorava disponibilidade;
3. resolução que não liberava o técnico;
4. resolução sem técnico atribuído.

## Solução

Os problemas foram corrigidos preservando o domínio do ServiceNowX. A seleção de técnicos ficou protegida no `AssignmentService`, enquanto o ciclo do chamado permanece no `ServiceDeskService`.

## Entregas

- Diagnóstico: `evidencias/AULA-04.md`
- ADR: `docs/adr/ADR-004-refatoracao-responsabilidades.md`
- RF/RNF: `docs/requisitos_iniciais.md`
- Testes: `src/test/java/br/edu/servicenowx/service/ServiceDeskServiceTest.java`
- Configuração Maven/JUnit: `pom.xml`

## Verificação

```bash
mvn clean compile
mvn clean test
```
