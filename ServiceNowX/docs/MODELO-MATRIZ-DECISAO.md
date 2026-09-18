# Matriz de Decisão Arquitetural — ServiceNowX

## Alternativas analisadas

- **Alternativa A:** Manter/Formalizar Arquitetura em Camadas (situação atual)
- **Alternativa B:** Monolítico sem separação explícita de camadas
- **Alternativa C:** Microserviços

## Critérios e pesos

Escala de peso: 1 = baixa importância, 2 = média, 3 = alta.
Escala de nota: 1 = atende fracamente, 2 = parcialmente, 3 = adequadamente, 4 = bem, 5 = muito bem.

| Critério | Peso |
|---|---:|
| Aderência ao domínio e aos requisitos | 3 |
| Separação de responsabilidades | 3 |
| Simplicidade/custo para o estágio atual | 3 |
| Testabilidade | 2 |
| Evolução futura | 2 |

## Matriz

| Critério | Peso | A — Camadas | P×N | B — Monolítico simples | P×N | C — Microserviços | P×N |
|---|---:|---:|---:|---:|---:|---:|---:|
| Aderência ao domínio e aos requisitos | 3 | 5 | 15 | 4 | 12 | 3 | 9 |
| Separação de responsabilidades | 3 | 5 | 15 | 2 | 6 | 5 | 15 |
| Simplicidade/custo para o estágio atual | 3 | 5 | 15 | 5 | 15 | 1 | 3 |
| Testabilidade | 2 | 5 | 10 | 4 | 8 | 3 | 6 |
| Evolução futura | 2 | 4 | 8 | 2 | 4 | 5 | 10 |
| **TOTAL** |  |  | **63** |  | **45** |  | **43** |

## Resultado

- **Alternativa A — Camadas:** 63
- **Alternativa B — Monolítico simples:** 45
- **Alternativa C — Microserviços:** 43

### Alternativa com maior pontuação

**Arquitetura em Camadas.**

A pontuação orienta a decisão, mas a escolha também considera o contexto real, os riscos e os trade-offs do ServiceNowX.
