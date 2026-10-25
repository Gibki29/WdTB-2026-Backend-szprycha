package pl.wsb.szprycha.inventory.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

/** Część zamienna na stanie serwisu, używana w wycenie naprawy. */
@Entity
@Table(name = "spare_part")
public class SparePart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 80)
    private String name;

    @Column(name = "manufacturer", length = 40)
    private String manufacturer;

    @Column(name = "sku", nullable = false, unique = true, length = 30)
    private String sku;                         // kod katalogowy części

    @Column(name = "unit_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @Column(name = "stock_quantity", nullable = false)
    private Integer stockQuantity;

    @Column(name = "active", nullable = false)
    private Boolean active;                     // część nadal w sprzedaży

    protected SparePart() {
    }

    public SparePart(String name, String manufacturer, String sku, BigDecimal unitPrice,
                     Integer stockQuantity, Boolean active) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.sku = sku;
        this.unitPrice = unitPrice;
        this.stockQuantity = stockQuantity;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getSku() {
        return sku;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public Boolean getActive() {
        return active;
    }
}
