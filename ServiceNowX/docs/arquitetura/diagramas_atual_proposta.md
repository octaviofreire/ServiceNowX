# Diagrama atual x proposta

## Atual — evidenciado no código

```text
             Main / Facade
                  |
                  v
        ServiceDeskService
          |      |       |
          v      v       v
       Model  Strategy  Observer
          |
          v
   InMemoryRepository

ServiceDeskService também acessa APIs legadas:
Directory / Monitoring / Notification / Vendor
```

## Proposta — arquitetura em camadas formalizada

```text
+--------------------------------------+
| ENTRADA / APRESENTAÇÃO               |
| Main + ServiceNowXFacade             |
+-------------------+------------------+
                    |
                    v
+--------------------------------------+
| APLICAÇÃO / SERVIÇOS                 |
| ServiceDeskService + AssignmentService|
+-------------------+------------------+
                    |
                    v
+--------------------------------------+
| DOMÍNIO                              |
| Ticket + Technician + SlaPolicy      |
+----------+-------------------+-------+
           |                   |
           v                   v
+-------------------+   +-------------------+
| PERSISTÊNCIA      |   | INTEGRAÇÕES       |
| InMemoryRepository|   | Adapters/APIs     |
+-------------------+   +-------------------+
           |
           v
     dados dos chamados

Eventos/Observer atuam transversalmente para
notificações e reação ao ciclo do chamado.
```

### Melhoria arquitetural

A mudança principal não é trocar toda a tecnologia. É **tornar explícitas as fronteiras das camadas**, preservando os padrões já utilizados.
