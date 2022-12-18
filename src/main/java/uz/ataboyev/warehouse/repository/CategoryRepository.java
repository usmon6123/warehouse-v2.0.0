package uz.ataboyev.warehouse.repository;

import uz.ataboyev.warehouse.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByNameAndWarehouseId(String name, Long warehouseId);

    boolean existsById(Long id);
    boolean existsByWarehouseId(Long warehouseId);

    void deleteByName(String name);

    List<Category> findAllByWarehouseId(Long warehouseId);
}
