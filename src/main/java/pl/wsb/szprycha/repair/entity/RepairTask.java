package pl.wsb.szprycha.repair.entity;

import jakarta.persistence.*;
import pl.wsb.szprycha.intake.entity.RepairOrder;

import java.math.BigDecimal;

/**
 * Encja łącząca: relacja wiele-do-wielu zgłoszenie ↔ usługa z cennika.
 * Dwie relacje @ManyToOne zamiast @ManyToMany, bo para ma własne dane (cena w wycenie, wykonanie).
 */
@Entity
@Table(name = "repair_task",
        uniqueConstraints = @UniqueConstraint(columnNames = {"repair_order_id", "service_item_id"}))
public class RepairTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "repair_order_id", nullable = false)
    private RepairOrder repairOrder;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "service_item_id", nullable = false)
    private ServiceItem serviceItem;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;                   // cena z chwili wyceny, cennik może się później zmienić

    @Column(name = "done", nullable = false)
    private Boolean done;                       // usługa wykonana przez mechanika

    protected RepairTask() {
    }

    public RepairTask(RepairOrder repairOrder, ServiceItem serviceItem, BigDecimal price, Boolean done) {
        this.repairOrder = repairOrder;
        this.serviceItem = serviceItem;
        this.price = price;
        this.done = done;
    }

    public Long getId() {
        return id;
    }

    public RepairOrder getRepairOrder() {
        return repairOrder;
    }

    public ServiceItem getServiceItem() {
        return serviceItem;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Boolean getDone() {
        return done;
    }
}
