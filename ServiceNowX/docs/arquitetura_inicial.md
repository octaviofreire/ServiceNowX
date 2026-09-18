# Arquitetura inicial — ServiceNowX

## Identificação

A implementação atual é mais coerente com o estilo **Arquitetura em Camadas**.

Há evidências concretas de separação em:
- `model` — domínio;
- `service` — regras/orquestração;
- `repository` — persistência;
- `patterns` — responsabilidades especializadas;
- `legacy` — integrações simuladas.

A documentação anterior dizia apenas “arquitetura moderna e escalável” e “orientada a serviços”, sem registrar fronteiras ou critérios verificáveis. Essa descrição foi considerada insuficiente.

## Decisão para a atividade

Formalizar **Arquitetura em Camadas** como o estilo arquitetural principal do ServiceNowX.

Os padrões Strategy, Adapter, Factory, Observer e Facade continuam sendo usados como mecanismos de projeto dentro dessa arquitetura.

Ver:
- `docs/arquitetura/arquitetura_proposta.md`
- `docs/arquitetura/matriz_decisao.md`
- `docs/arquitetura/diagramas_atual_proposta.md`
- `docs/arquitetura/rastreabilidade_rnf.md`
