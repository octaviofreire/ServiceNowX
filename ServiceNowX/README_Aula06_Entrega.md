# ServiceNowX — Aula 06

## Evidências da atividade

### 1. Problema observado

Durante a análise do projeto ServiceNowX foram identificados pontos relacionados às regras de SLA e atribuição de técnicos que precisavam de critérios mais claros e verificáveis.

---

### 2. Problema — Cálculo do SLA

O cálculo do prazo de atendimento deve utilizar a data e hora real de abertura do chamado e considerar a prioridade definida.

#### Alteração realizada

A regra de SLA deve utilizar a abertura do chamado como referência para calcular o prazo (`dueAt`), aplicando o período correspondente à prioridade.

#### Critério de aceitação

Dado um chamado com uma data/hora de abertura e uma prioridade definida, o sistema deve calcular o `dueAt` a partir da abertura do chamado e do SLA correspondente.

---

### 3. Problema — Atribuição de técnico

A atribuição de chamados deve considerar a disponibilidade do técnico responsável.

#### Alteração realizada

A regra de atribuição deve verificar se o técnico está disponível antes de permitir uma nova atribuição.

#### Critério de aceitação

Quando `available = false`, o técnico não deve receber um novo chamado.

Quando `available = true`, a atribuição deve ser permitida conforme as demais regras do sistema.

---

### 4. Problema — Disponibilidade após resolução

Após a resolução de um chamado, deve ser tratado o estado de disponibilidade do técnico responsável.

#### Alteração realizada

A resolução do chamado deve atualizar a disponibilidade do técnico de acordo com a regra de negócio definida para o sistema.

#### Critério de aceitação

Após a resolução, o técnico deve ficar disponível para uma nova atribuição quando não possuir outro atendimento ativo.

---

### 5. Critérios verificáveis

| Item       | Critério de aceitação                                           |
| ---------- | --------------------------------------------------------------- |
| SLA        | O `dueAt` é calculado a partir da data/hora de abertura         |
| Prioridade | A prioridade influencia o prazo de SLA                          |
| Atribuição | Técnico indisponível não recebe chamado                         |
| Resolução  | Técnico é liberado após finalizar o atendimento                 |
| Testes     | As regras alteradas são verificadas por testes quando aplicável |

---

### 6. Requisitos não funcionais

#### Segurança

O sistema deve restringir operações às funcionalidades permitidas para cada usuário.

**Critério verificável:** operações protegidas não devem ser executadas por usuários sem autorização.

#### Desempenho

As operações principais devem ser executadas sem processamento desnecessário.

**Critério verificável:** cálculo de SLA e atribuição devem ser executados sem operações redundantes.

#### Disponibilidade

Falhas em operações externas não devem causar perda dos dados já registrados.

**Critério verificável:** uma falha externa deve ser tratada sem apagar o chamado.

#### Manutenibilidade

As regras de negócio devem permanecer organizadas e testáveis.

**Critério verificável:** alterações nas regras de SLA e atribuição devem ficar concentradas nos serviços responsáveis por essas regras.

---

### 7. Matriz de rastreabilidade

| Requisito        | Problema relacionado        | Alteração                       | Critério de aceitação                  |
| ---------------- | --------------------------- | ------------------------------- | -------------------------------------- |
| SLA              | Prazo incorreto             | Ajuste do cálculo               | `dueAt` baseado na abertura            |
| Prioridade       | Regra pouco definida        | Definição do impacto no SLA     | Prioridade altera o prazo              |
| Atribuição       | Técnico indisponível        | Validação de disponibilidade    | Indisponível não recebe chamado        |
| Resolução        | Técnico não liberado        | Atualização da disponibilidade  | Técnico fica disponível após resolução |
| Segurança        | Controle de acesso          | Restrição de operações          | Usuário sem autorização é bloqueado    |
| Desempenho       | Processamento desnecessário | Organização das operações       | Operações executadas de forma adequada |
| Disponibilidade  | Falhas externas             | Tratamento de erros             | Dados não são perdidos                 |
| Manutenibilidade | Regras espalhadas           | Separação das responsabilidades | Código permanece testável              |

---

### 8. Evidência de compilação/teste

Após as alterações de código, executar:

```bash
mvn test
```

O resultado da execução deverá ser registrado como evidência.

Resultado esperado:

```text
BUILD SUCCESS
```

---

### 9. Commits

As alterações da Aula 06 devem possuir mensagens explicativas, por exemplo:

```text
docs: adiciona documentação da aula 06
fix: ajusta regra de SLA
fix: valida disponibilidade do tecnico
test: adiciona testes da aula 06
```

---

### 10. Pull Request

Ao finalizar a atividade, será criado um Pull Request da branch:

```text
aula-06
```

para:

```text
main
```

O Pull Request deverá permitir a revisão das alterações realizadas na Aula 06.

---

## Conclusão

As evidências da Aula 06 são compostas pela Issue, branch `aula-06`, commits explicativos, documentação dos problemas e alterações, critérios de aceitação, evidência de testes/compilação quando houver código alterado e Pull Request para revisão.
