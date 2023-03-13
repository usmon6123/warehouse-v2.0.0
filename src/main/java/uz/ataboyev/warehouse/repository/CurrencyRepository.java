package uz.ataboyev.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.ataboyev.warehouse.entity.Company;
import uz.ataboyev.warehouse.entity.CurrencyPrise;

public interface CurrencyRepository extends JpaRepository<CurrencyPrise, Long> {


}
