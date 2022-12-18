package uz.ataboyev.warehouse.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.ataboyev.warehouse.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.ataboyev.warehouse.payload.GetCodesForProduct;
import uz.ataboyev.warehouse.payload.OptionResIn;
import uz.ataboyev.warehouse.payload.ProductResDtoByWhIdImpl;

import java.util.Collection;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByCategoryId(Long categoryId);

    boolean existsByCategory_Name(String category_name);

    boolean existsByNameAndCode(String name, String code);

    boolean existsByNameAndCodeAndIdNot(String name, String code, Long id);


    Integer countByIdIn(Collection<Long> id);


    List<Product> findAllByCategoryId(Long categoryId);

    @Query(value = "select distinct p.name  from product p " +
            "where p.category_id =:categoryId",nativeQuery = true)
    List<OptionResIn> findDistinctByCategoryId(@Param("categoryId") Long categoryId);



    @Query(value = "select p.* from product p where p.category_id in (" +
            "    select c.id from category c where c.warehouse_id = :whId)",
            nativeQuery = true)
    List<Product> findAllByWarehouseById(@Param("whId") Long whId);


    @Query(value = "select " +
            "cast (p.id as varchar) as productId, " +
            "c.name as categoryName, " +
            "p.name as productName, " +
            "p.code as code, " +
            "cast (p.count as varchar ) as count " +
            "from product p " +
            "inner join category c on c.id = p.category_id" +
            " where p.category_id in (" +
            "        select c.id from category c where c.warehouse_id = :whId)" +
            " order by p.updated_at desc ", nativeQuery = true)
    List<ProductResDtoByWhIdImpl> getProductByWarehouseId(@Param("whId") Long whId);


    @Query(value = "select cast(p.id as varchar) as productId," +
            "       c.name                as categoryName," +
            "       p.name                as productName," +
            "       p.code                as code," +
            "       p.count               as count " +
            "from product p " +
            "         inner join category c on c.id = p.category_id " +
            "where c.warehouse_id = :whId and p.count<= p.min_count order by p.count ASC  ",
            nativeQuery = true)
    List<ProductResDtoByWhIdImpl> getLittleProductByWarehouseId(@Param("whId") Long whId);


    @Query(value = "select cast (p.id as varchar )as id,p.code as code from product p where p.name = :name order by p.code ASC", nativeQuery = true)
    List<GetCodesForProduct> getCodesForProduct(@Param("name") String name);

}
