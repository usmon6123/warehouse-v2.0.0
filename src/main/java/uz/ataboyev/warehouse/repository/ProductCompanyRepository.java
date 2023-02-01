package uz.ataboyev.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.ataboyev.warehouse.entity.ProductCompany;
import uz.ataboyev.warehouse.payload.OptionInIdName;
import uz.ataboyev.warehouse.payload.OptionResIn;

import java.util.List;

public interface ProductCompanyRepository extends JpaRepository<ProductCompany, Long> {
    boolean existsByName(String name);

    boolean existsByNameAndWarehouseIdAndIdNot(String name, Long warehouse_id, Long pcId);

    boolean existsById(Long id);

    boolean existsByWarehouseId(Long warehouseId);

    void deleteByName(String name);

    @Query(value = "select pc.id as id, pc.name as name from product_company pc where pc.wh_id = :whId ", nativeQuery = true)
    List<OptionInIdName> getAllForOptionByWhId(@Param("whId") Long warehouseId);

    @Query(value = "select pc.name as name from product_company pc where pc.wh_id = :whId ", nativeQuery = true)
    List<OptionResIn> findAllForOptionByWhId(@Param("whId") Long warehouseId);
//
//    @Query(value ="select * from product_", nativeQuery = true)
//    List<ProductCompRes> getAllByWarehouseId(Long warehouse_id);
}
