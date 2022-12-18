package uz.ataboyev.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.ataboyev.warehouse.entity.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

    boolean existsByName(String name);

    boolean existsByCompanyId(Long companyId);

}
