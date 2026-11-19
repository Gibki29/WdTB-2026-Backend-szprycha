package pl.wsb.szprycha.intake.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsb.szprycha.intake.entity.RepairOrder;

import java.util.List;

/**
 * Repozytorium zgłoszeń: interfejs bez implementacji, Spring Data buduje zapytania z nazw metod.
 * {@code findByCustomer_Phone} przechodzi przez relację {@code RepairOrder.customer} do kolumny {@code customer.phone}:
 * klient dzwoni, a mechanik po numerze telefonu widzi jego zgłoszenia i ich status.
 */
public interface RepairOrderRepository extends JpaRepository<RepairOrder, Long> {

    List<RepairOrder> findByCustomer_Phone(String phone);
}
