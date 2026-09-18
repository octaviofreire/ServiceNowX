# ADR-0001 — Arquitetura em Camadas para o ServiceNowX

## Status

**Aceita**

## Contexto

A análise do código mostrou uma aplicação Java única, organizada em pacotes de domínio (`model`), serviços (`service`), persistência (`repository`), padrões de integração (`patterns`) e APIs legadas (`legacy`).

## Decisão

Adotar e formalizar **Arquitetura em Camadas** como o estilo arquitetural principal do ServiceNowX.

Os padrões Strategy, Adapter, Factory, Observer e Facade permanecem como padrões de projeto complementares.

## Alternativas consideradas

1. Monolítico sem separação explícita;
2. Arquitetura em Camadas;
3. Microserviços.

## Justificativa

A arquitetura em camadas preserva a aplicação única e as fronteiras que já aparecem no código, facilitando manutenção, testes e evolução sem introduzir comunicação distribuída.

## Consequências

### Positivas
- responsabilidades mais claras;
- testes de regras de negócio independentes da interface;
- persistência e integrações mais isoladas;
- baixo custo operacional.

### Negativas
- exige disciplina para não cruzar camadas indevidamente;
- não oferece escalabilidade independente por componente.

## Evidências

- `src/main/java/br/edu/servicenowx/model`
- `src/main/java/br/edu/servicenowx/service`
- `src/main/java/br/edu/servicenowx/repository`
- `src/main/java/br/edu/servicenowx/patterns`
- `src/main/java/br/edu/servicenowx/legacy`
- `src/test/java/br/edu/servicenowx/service/ServiceDeskServiceTest.java`
