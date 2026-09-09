# ServiceNowX — Projeto Semestral

O ServiceNowX simula uma central de serviços técnicos com chamados, técnicos, prioridades, SLA, notificações e integrações externas.

O projeto inicial **compila e executa**, porém representa um legado propositalmente imperfeito. O aluno deverá analisar e evoluir o sistema progressivamente conforme os conteúdos apresentados em aula.

## Escopo inicial
- abertura e acompanhamento de chamados;
- técnicos;
- categorias e prioridades;
- SLA;
- atribuição de responsável;
- notificações;
- escalonamento;
- integrações externas.

## Execução
Requer Java 17.

```bash
javac -d out $(find src/main/java -name "*.java")
java -cp out br.edu.servicenowx.Main
```

As atividades estão em `atividades/README_ServiceNowX_AulaXX.md`.

> A presença de classes com nomes de padrões não comprova aplicação correta. O aluno deve analisar problema, necessidade, comportamento e consequências.
