# Requisitos do ServiceNowX

## 1. Requisitos Funcionais (RF)

| ID | Requisito | Critério de aceitação |
|---|---|---|
| RF01 | O sistema deve permitir abrir chamados. | Um chamado deve ser criado com identificador, solicitante, categoria, prioridade e data de abertura. |
| RF02 | O sistema deve calcular o prazo de SLA conforme a prioridade. | `dueAt` deve ser calculado a partir de `openedAt` real somando o tempo definido para a prioridade. |
| RF03 | O sistema deve atribuir chamados a técnicos elegíveis. | Somente técnicos disponíveis podem ser escolhidos para uma nova atribuição. |
| RF04 | O sistema deve considerar a especialidade do técnico na atribuição. | Para chamados de rede, um técnico disponível de especialidade NETWORK deve ser priorizado. |
| RF05 | O sistema deve controlar a disponibilidade dos técnicos. | Ao receber um chamado, o técnico fica indisponível; ao resolver, volta a ficar disponível. |
| RF06 | O sistema deve controlar a quantidade de chamados ativos por técnico. | A atribuição incrementa `activeTickets` e a resolução decrementa o contador sem permitir valor negativo. |
| RF07 | O sistema deve permitir resolver chamados. | A resolução deve mudar o status para `RESOLVED` somente quando houver técnico atribuído. |
| RF08 | O sistema deve permitir escalonar chamados para suporte externo. | Ao escalonar, deve ser aberto um caso no fornecedor e o status deve mudar para `ESCALATED`. |
| RF09 | O sistema deve receber alertas de monitoramento como chamados. | Um alerta recebido deve gerar um ticket de monitoramento com prioridade CRITICAL e registrar o evento no histórico. |
| RF10 | O sistema deve notificar eventos relevantes do ciclo do chamado. | A abertura, atribuição, resolução e escalonamento devem publicar os respectivos eventos. |

## 2. Requisitos Não Funcionais (RNF)

| ID | Requisito | Critério de aceitação |
|---|---|---|
| RNF01 | O sistema deve ser executável em Java 17 ou superior. | O projeto deve compilar com `mvn clean compile` usando Java 17+. |
| RNF02 | O projeto deve utilizar Maven para gerenciamento e execução. | O projeto deve possuir `pom.xml` válido e os comandos Maven devem executar sem erro. |
| RNF03 | Os testes automatizados devem utilizar JUnit 5. | `mvn clean test` deve executar os testes e apresentar `BUILD SUCCESS`. |
| RNF04 | O cálculo de SLA deve ser determinístico. | Para a mesma prioridade e `openedAt`, o resultado de `dueAt` deve ser sempre igual. |
| RNF05 | O sistema deve preservar a consistência do estado dos técnicos. | Não pode haver resolução que deixe `activeTickets` negativo ou técnico ocupado após a liberação. |
| RNF06 | O sistema deve rejeitar dados obrigatórios ausentes nas regras críticas. | `openedAt` ausente deve gerar erro e chamado sem técnico não pode ser resolvido. |
| RNF07 | O código deve manter separação de responsabilidades. | A seleção de técnicos deve ficar no serviço de atribuição e o ciclo do chamado no serviço de atendimento. |
| RNF08 | As regras de negócio devem ser testáveis independentemente da interface. | As regras de SLA, atribuição e resolução devem possuir testes JUnit no projeto. |
| RNF09 | O sistema deve manter codificação de texto UTF-8. | O `pom.xml` deve definir `project.build.sourceEncoding` como UTF-8. |
| RNF10 | Alterações de código devem ser verificáveis por compilação e testes antes da entrega. | A entrega deve apresentar evidência dos comandos Maven e dos resultados dos testes. |

## Regras de SLA adotadas nesta etapa

- CRITICAL: 2 horas
- HIGH: 12 horas
- Demais prioridades: 24 horas

O cálculo considera o instante real de abertura informado em `openedAt`. A regra de horário comercial/pausas pode ser refinada posteriormente caso seja definida pelo domínio.
