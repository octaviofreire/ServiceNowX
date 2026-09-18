# Arquitetura do ServiceNowX — Aula de Arquitetura

## 1. Arquitetura atual identificada

O ServiceNowX **já apresenta uma arquitetura em camadas**, embora a documentação legada não a descrevesse de forma suficiente.

Evidências no código:

- `br.edu.servicenowx.model`: entidades e dados do domínio (`Ticket`, `Technician`, `SlaPolicy`);
- `br.edu.servicenowx.service`: regras e orquestração do atendimento (`ServiceDeskService`);
- `br.edu.servicenowx.repository`: persistência atualmente em memória (`InMemoryRepository`);
- `br.edu.servicenowx.patterns`: componentes especializados para responsabilidades específicas, como Strategy, Adapter, Factory, Observer e Facade;
- `br.edu.servicenowx.legacy`: APIs externas/legadas simuladas.

### Classificação

**Estilo arquitetural escolhido: Arquitetura em Camadas.**

Os padrões de projeto existentes complementam a arquitetura, mas não substituem o estilo arquitetural principal.

## 2. Por que não escolher outra arquitetura

O ServiceNowX é um sistema acadêmico pequeno, executado como uma aplicação única, com regras de negócio locais e integrações simuladas. Não existem atualmente múltiplos serviços independentes, infraestrutura distribuída ou uma interface MVC que justifique microserviços, SOA, cliente-servidor ou MVC como estilo principal.

## 3. Arquitetura proposta

A proposta é **formalizar e reforçar a arquitetura em camadas**, mantendo a aplicação como uma unidade implantável.

### Camadas

1. **Apresentação/entrada**
   - `Main`
   - `ServiceNowXFacade`

2. **Serviço / aplicação**
   - `ServiceDeskService`
   - `AssignmentService`

3. **Domínio**
   - `Ticket`
   - `Technician`
   - `SlaPolicy`

4. **Persistência**
   - `InMemoryRepository`

5. **Integrações**
   - `DirectoryAdapter`
   - `VendorSupportAdapter`
   - APIs legadas de diretório, monitoramento, notificação e fornecedor

6. **Eventos**
   - `TicketPublisher`
   - `RequesterObserver`
   - `ManagerObserver`

> Os eventos e padrões são componentes transversais da solução. A arquitetura principal continua sendo em camadas.

## 4. Trade-offs

### Benefícios
- separação clara de responsabilidades;
- facilidade de testar regras de negócio;
- manutenção mais simples;
- baixo custo de implantação;
- permite trocar a persistência em memória por banco posteriormente;
- permite substituir APIs legadas por adapters sem alterar o núcleo do atendimento.

### Custos
- mais classes e pacotes do que uma implementação totalmente monolítica sem separação;
- disciplina de dependências entre camadas é necessária;
- não oferece, por si só, escalabilidade independente como microserviços.

## 5. Consequência da decisão

Para a fase atual do ServiceNowX, a arquitetura em camadas apresenta o melhor equilíbrio entre simplicidade, separação de responsabilidades, testabilidade e possibilidade de evolução.

Microserviços/SOA/EDA distribuídos poderiam aumentar a complexidade operacional sem necessidade comprovada neste estágio.

## 6. Relação com os requisitos

Os RNFs ligados à arquitetura são principalmente:

- **RNF07:** separação de responsabilidades;
- **RNF08:** regras testáveis independentemente da interface;
- **RNF05:** consistência do estado dos técnicos;
- **RNF10:** verificabilidade por compilação e testes.

Os RFs mais diretamente afetados são:

- RF02: cálculo de SLA;
- RF03/RF04/RF05/RF06: atribuição e disponibilidade;
- RF07: resolução;
- RF08/RF09/RF10: integrações e eventos.

## 7. Critério de aceitação arquitetural

A solução será considerada coerente quando:

1. regras de atendimento estiverem concentradas na camada de serviço;
2. entidades permanecerem no domínio;
3. persistência ficar isolada pelo repositório;
4. integrações externas forem acessadas por adapters/APIs de integração;
5. os testes puderem exercitar as regras sem depender de uma interface gráfica;
6. `mvn clean compile` e `mvn clean test` forem executados com sucesso.
