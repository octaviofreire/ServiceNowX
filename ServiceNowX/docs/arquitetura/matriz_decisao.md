# Matriz de decisão — escolha do estilo arquitetural

Escala: 1 = baixo atendimento ao critério; 5 = excelente atendimento.

Pesos: aderência ao domínio (30%), separação de responsabilidades (20%), simplicidade (20%), testabilidade (15%), evolução/escalabilidade (15%).

| Alternativa | Aderência 30% | Separação 20% | Simplicidade 20% | Testabilidade 15% | Evolução 15% | Resultado ponderado |
|---|---:|---:|---:|---:|---:|---:|
| Monolítico | 4 | 2 | 5 | 4 | 2 | 3,45 |
| **Em camadas** | **5** | **5** | **5** | **5** | **4** | **4,80** |
| MVC | 2 | 4 | 3 | 4 | 3 | 3,10 |
| Cliente-servidor | 3 | 3 | 3 | 3 | 4 | 3,20 |
| SOA | 3 | 5 | 2 | 3 | 5 | 3,55 |
| EDA | 3 | 4 | 2 | 3 | 5 | 3,30 |
| Microserviços | 2 | 5 | 1 | 3 | 5 | 3,00 |
| Mensageria | 2 | 4 | 2 | 3 | 5 | 2,95 |

## Decisão

**Escolhemos Arquitetura em Camadas.**

Ela atende melhor ao contexto atual do ServiceNowX porque o sistema possui uma única aplicação, regras de negócio bem definidas e necessidade de manutenção/testes sem exigir infraestrutura distribuída.

## Trade-off principal

Abrimos mão da escalabilidade independente típica de microserviços em favor de menor complexidade, menor custo operacional e maior facilidade de desenvolvimento e teste nesta fase.
