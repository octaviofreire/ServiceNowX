# Visão parcial

```mermaid
classDiagram
 class ServiceDeskService
 class Ticket
 class Technician
 class SlaPolicy
 class DirectoryLegacyApi
 class MonitoringLegacyApi
 class VendorSupportLegacyApi
 ServiceDeskService --> Ticket
 ServiceDeskService --> Technician
 ServiceDeskService --> DirectoryLegacyApi
 ServiceDeskService --> MonitoringLegacyApi
 ServiceDeskService --> VendorSupportLegacyApi
```
