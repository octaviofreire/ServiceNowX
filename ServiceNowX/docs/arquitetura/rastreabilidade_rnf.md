# Matriz de rastreabilidade — RNFs x arquitetura

| RNF | Parte arquitetural afetada | Como a arquitetura atende |
|---|---|---|
| RNF01 | Projeto/execução | Java 17 padroniza ambiente de execução. |
| RNF02 | Build | Maven centraliza dependências e execução. |
| RNF03 | Testes | JUnit 5 valida as regras de serviço. |
| RNF04 | Serviço/Domínio | cálculo de SLA é determinístico e usa `openedAt`. |
| RNF05 | Serviço/Domínio | atribuição e resolução mantêm estado consistente do técnico. |
| RNF06 | Serviço | validações críticas ficam na camada de serviço. |
| RNF07 | Todas as camadas | serviço, domínio, persistência e integração possuem responsabilidades separadas. |
| RNF08 | Serviço/Testes | regras podem ser testadas sem interface gráfica. |
| RNF09 | Build | UTF-8 é definido no Maven. |
| RNF10 | Build/Testes | Maven permite verificar compilação e testes antes da entrega. |
