package uz.ataboyev.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.ataboyev.warehouse.entity.Company;
import uz.ataboyev.warehouse.entity.Warehouse;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Long id);




}
