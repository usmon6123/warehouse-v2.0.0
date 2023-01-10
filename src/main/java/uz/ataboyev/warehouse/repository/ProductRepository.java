package uz.ataboyev.warehouse.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.ataboyev.warehouse.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.ataboyev.warehouse.payload.OptionResIn;
import uz.ataboyev.warehouse.payload.ProductResDtoByWhIdImpl;

import java.util.Collection;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

//    boolean existsByCategoryId(Long categoryId);
//
//    boolean existsByCategory_Name(String category_name);

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Long id);


    Integer countByIdIn(Collection<Long> id);


    List<Product> findAllByProductCompanyId(Long pCId);

    @Query(value = "select distinct p.name " +
            "from product p " +
            "where p.product_company_id = :pCId",nativeQuery = true)
    List<OptionResIn> findDistinctByProductCompanyId(@Param("pCId") Long pCId);



    @Query(value = "select p.* " +
            "from product p " +
            "where p.product_company_id in (select pc.id from product_company pc where pc.wh_id = :whId)",
            nativeQuery = true)
    List<Product> findAllByWarehouseById(@Param("whId") Long whId);


    @Query(value = "select cast(p.id as varchar)    as productId, " +
            "       pc.name                  as productCompanyName, " +
            "       p.name                   as productName, " +
            "       cast(p.count as varchar) as count " +
            "from product p " +
            "         inner join product_company pc on pc.id = p.product_company_id " +
            "where p.product_company_id in ( " +
            "    select pc.id " +
            "    from product_company pc " +
            "    where pc.wh_id = :whId) " +
            "order by pc.name, p.created_at asc ", nativeQuery = true)
    List<ProductResDtoByWhIdImpl> getProductByWarehouseId(@Param("whId") Long whId);


    @Query(value = "select cast(p.id as varchar) as productId, " +
            "       pc.name               as productCompanyName, " +
            "       p.name                as productName, " +
            "       p.count               as count " +
            "from product p " +
            "         inner join product_company pc on pc.id = p.product_company_id " +
            "where pc.wh_id = :whId " +
            "  and p.count <= p.min_count " +
            "order by p.count ASC  ",
            nativeQuery = true)
    List<ProductResDtoByWhIdImpl> getLittleProductByWarehouseId(@Param("whId") Long whId);


//    @Query(value = "select cast (p.id as varchar )as id,p.code as code from product p where p.name = :name order by p.code ASC", nativeQuery = true)
//    List<GetCodesForProduct> getCodesForProduct(@Param("name") String name);

}
