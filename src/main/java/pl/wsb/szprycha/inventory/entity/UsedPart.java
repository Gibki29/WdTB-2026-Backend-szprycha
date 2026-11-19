package pl.wsb.szprycha.inventory.entity;

import jakarta.persistence.*;
import pl.wsb.szprycha.intake.entity.RepairOrder;

import java.math.BigDecimal;

/**
 * Encja łącząca: relacja wiele-do-wielu zgłoszenie ↔ część zamienna.
 * Dwie relacje @ManyToOne zamiast @ManyToMany, bo para ma własne dane (ilość, cena w wycenie).
 */
@Entity
@Table(name = "used_part",
        uniqueConstraints = @UniqueConstraint(columnNames = {"repair_order_id", "spare_part_id"}))
public class UsedPart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "repair_order_id", nullable = false)
    private RepairOrder repairOrder;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "spare_part_id", nullable = false)
    private SparePart sparePart;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "unit_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;               // cena z chwili wyceny, cena części może się później zmienić

    protected UsedPart() {
    }

    public UsedPart(RepairOrder repairOrder, SparePart sparePart, Integer quantity, BigDecimal unitPrice) {
        this.repairOrder = repairOrder;
        this.sparePart = sparePart;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public Long getId() {
        return id;
    }

    public RepairOrder getRepairOrder() {
        return repairOrder;
    }

    public SparePart getSparePart() {
        return sparePart;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }
}
