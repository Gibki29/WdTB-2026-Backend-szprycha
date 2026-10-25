package pl.wsb.szprycha.repair.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

/** Usługa z cennika serwisu, np. regulacja hamulców albo centrowanie koła. */
@Entity
@Table(name = "service_item")
public class ServiceItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true, length = 60)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false, length = 20)
    private ServiceCategory category;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "estimated_minutes", nullable = false)
    private Integer estimatedMinutes;

    @Column(name = "active", nullable = false)
    private Boolean active;                     // usługa w ofercie

    protected ServiceItem() {
    }

    public ServiceItem(String name, ServiceCategory category, BigDecimal price, Integer estimatedMinutes, Boolean active) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.estimatedMinutes = estimatedMinutes;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ServiceCategory getCategory() {
        return category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getEstimatedMinutes() {
        return estimatedMinutes;
    }

    public Boolean getActive() {
        return active;
    }
}
