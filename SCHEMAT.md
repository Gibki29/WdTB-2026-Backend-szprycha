# Schemat bazy danych

Plik generują reguły projektu (panel Maven → Lifecycle → verify). Nie edytujcie go ręcznie.

Tabele: 7 · kolumny: 43 · relacje: 5 × @ManyToOne, 1 × @OneToMany, 1 × @OneToOne, 0 × @ManyToMany

| Tabela | Encja | Kolumny | Klucze obce |
|---|---|---|---|
| customer | Customer | 6 | user_account_id |
| repair_order | RepairOrder | 9 | customer_id |
| repair_task | RepairTask | 5 | repair_order_id, service_item_id |
| service_item | ServiceItem | 6 | — |
| spare_part | SparePart | 7 | — |
| used_part | UsedPart | 5 | repair_order_id, spare_part_id |
| user_account | UserAccount | 5 | — |

```mermaid
erDiagram
    customer {
        Long id PK
        String email
        String full_name
        String phone
        LocalDate registered_on
        Long user_account_id FK
    }
    repair_order {
        Long id PK
        String bike_brand
        String bike_model
        Long customer_id FK
        BigDecimal estimated_cost
        String frame_number
        String problem_description
        LocalDateTime received_at
        RepairStatus status
    }
    repair_task {
        Long id PK
        Boolean done
        BigDecimal price
        Long repair_order_id FK
        Long service_item_id FK
    }
    service_item {
        Long id PK
        Boolean active
        ServiceCategory category
        Integer estimated_minutes
        String name
        BigDecimal price
    }
    spare_part {
        Long id PK
        Boolean active
        String manufacturer
        String name
        String sku
        Integer stock_quantity
        BigDecimal unit_price
    }
    used_part {
        Long id PK
        Integer quantity
        Long repair_order_id FK
        Long spare_part_id FK
        BigDecimal unit_price
    }
    user_account {
        Long id PK
        LocalDateTime created_at
        String display_name
        String email
        AccountRole role
    }
    customer ||--o{ repair_order : "customer_id"
    repair_order ||--o{ repair_task : "repair_order_id"
    repair_order ||--o{ used_part : "repair_order_id"
    service_item ||--o{ repair_task : "service_item_id"
    spare_part ||--o{ used_part : "spare_part_id"
    user_account |o--o| customer : "user_account_id"
```
