package pl.wsb.szprycha.customer.entity;

import jakarta.persistence.*;
import pl.wsb.szprycha.account.domain.UserAccount;

import java.time.LocalDate;

/** Klient serwisu: dane kontaktowe osoby oddającej rower, opcjonalnie powiązane z kontem w aplikacji. */
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false, length = 80)
    private String fullName;

    @Column(name = "phone", nullable = false, unique = true, length = 20)
    private String phone;                       // po numerze telefonu mechanik znajduje zgłoszenia klienta

    @Column(name = "email", length = 120)
    private String email;

    @Column(name = "registered_on", nullable = false)
    private LocalDate registeredOn;

    // konto w aplikacji, opcjonalne: klient „z ulicy” go nie ma
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_account_id")
    private UserAccount account;

    protected Customer() {
    }

    public Customer(String fullName, String phone, String email, LocalDate registeredOn, UserAccount account) {
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.registeredOn = registeredOn;
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getRegisteredOn() {
        return registeredOn;
    }

    public UserAccount getAccount() {
        return account;
    }
}
