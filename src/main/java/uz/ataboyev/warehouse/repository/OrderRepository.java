package uz.ataboyev.warehouse.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.ataboyev.warehouse.entity.Order;
import uz.ataboyev.warehouse.payload.GetDescriptionByOrderId;
import uz.ataboyev.warehouse.payload.OrderPriceDtoForRep;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    boolean existsByClientId(Long clientId);
    boolean existsByWarehouseId(Long warehouseId);

    @Query(value = "select SUM(o.order_price_sum) as sum, " +
            "SUM(o.order_price_dollar) as dollar " +
            "from orders o where o.warehouse_id=:whId",nativeQuery = true)
    OrderPriceDtoForRep orderPriceByWhId(@Param("whId")Long whId);

    //WAREHOUSE ID ORQALI BARCHA ORDERLARNI SANASI BO'YICHA ORDERLAB OLIB KELADI
    Page<Order> findAllByWarehouseIdOrderByUpdatedAtDesc(Long warehouseId, Pageable pageable);

    @Query(value = "select o.description as description from orders o where o.id = :id",nativeQuery = true)
    Optional<GetDescriptionByOrderId> getDescriptionById(@Param("id")Long id);

}
