package uz.ataboyev.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.ataboyev.warehouse.entity.ProductCompany;
import uz.ataboyev.warehouse.payload.ProductCompRes;

import java.util.List;

public interface ProductCompanyRepository extends JpaRepository<ProductCompany,Long> {
    boolean existsByName(String name);

    boolean existsByNameAndWarehouseId(String name, Long warehouseId);

    boolean existsById(Long id);
    boolean existsByWarehouseId(Long warehouseId);

    void deleteByName(String name);

    List<ProductCompany> findAllByWarehouseId(Long warehouseId);
//
//    @Query(value ="select * from product_", nativeQuery = true)
//    List<ProductCompRes> getAllByWarehouseId(Long warehouse_id);
}
