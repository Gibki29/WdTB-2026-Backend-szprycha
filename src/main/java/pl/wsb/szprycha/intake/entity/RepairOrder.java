package pl.wsb.szprycha.intake.entity;

import jakarta.persistence.*;
import pl.wsb.szprycha.customer.entity.Customer;
import pl.wsb.szprycha.repair.entity.RepairTask;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/** Zgłoszenie naprawy: rower przyjęty do serwisu, prowadzony od przyjęcia do odbioru. */
@Entity
@Table(name = "repair_order")
public class RepairOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // klient, który oddał rower; jeden klient może mieć wiele zgłoszeń
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

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

    /** Strona odwrotna relacji RepairTask.repairOrder: nie tworzy kolumny, pozwala przejść od zgłoszenia do jego usług. */
    @OneToMany(mappedBy = "repairOrder")
    private List<RepairTask> tasks = new ArrayList<>();

    protected RepairOrder() {
    }

    public RepairOrder(Customer customer, String problemDescription, RepairStatus status,
                       LocalDateTime receivedAt, BigDecimal estimatedCost, BikeInfo bike) {
        this.customer = customer;
        this.problemDescription = problemDescription;
        this.status = status;
        this.receivedAt = receivedAt;
        this.estimatedCost = estimatedCost;
        this.bike = bike;
    }

    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
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

    public List<RepairTask> getTasks() {
        return tasks;
    }
}
