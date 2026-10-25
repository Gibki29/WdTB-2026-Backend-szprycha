package pl.wsb.szprycha.intake.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/** Zgłoszenie naprawy: rower przyjęty do serwisu, prowadzony od przyjęcia do odbioru. */
@Entity
@Table(name = "repair_order")
public class RepairOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_name", nullable = false, length = 80)
    private String customerName;

    @Column(name = "customer_phone", nullable = false, length = 20)
    private String customerPhone;

    @Column(name = "problem_description", nullable = false, length = 255)
    private String problemDescription;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 30)
    private RepairStatus status;

    @Column(name = "received_at", nullable = false)
    private LocalDateTime receivedAt;

    @Column(name = "estimated_cost", precision = 10, scale = 2)
    private BigDecimal estimatedCost;           // wycena, uzupełniana po diagnozie

    @Embedded
    private BikeInfo bike;                      // marka, model, numer ramy

    protected RepairOrder() {
    }

    public RepairOrder(String customerName, String customerPhone, String problemDescription,
                       RepairStatus status, LocalDateTime receivedAt, BigDecimal estimatedCost, BikeInfo bike) {
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.problemDescription = problemDescription;
        this.status = status;
        this.receivedAt = receivedAt;
        this.estimatedCost = estimatedCost;
        this.bike = bike;
    }

    public Long getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public RepairStatus getStatus() {
        return status;
    }

    public LocalDateTime getReceivedAt() {
        return receivedAt;
    }

    public BigDecimal getEstimatedCost() {
        return estimatedCost;
    }

    public BikeInfo getBike() {
        return bike;
    }
}
