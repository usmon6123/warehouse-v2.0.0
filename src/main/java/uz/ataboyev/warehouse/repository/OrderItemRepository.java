package uz.ataboyev.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.ataboyev.warehouse.entity.OrderItem;
import uz.ataboyev.warehouse.payload.OrderPriceForPayType;
import uz.ataboyev.warehouse.payload.SoldProducts;
import uz.ataboyev.warehouse.payload.clientDtos.OrderItemByOrderId;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query(value = "select * from order_item oi " +
            "where oi.order_id in(" +
            "select o.id from orders o where o.client_id = :clientId) " +
            "order by oi.created_at desc ", nativeQuery = true)
    List<OrderItem> findAllByOrder_ClientId(@Param("clientId") Long clientId);

    @Query(value = "select to_char(oi.created_at, 'dd-MM-yyyy')  as date, " +
            "       pc.name                               as productCompanyName, " +
            "       p.name                                as productName, " +
            "       cast(oi.count as varchar)             as count, " +
            "       cast(oi.amount as varchar)            as countSum, " +//dona summa
            "       cast(oi.currency_type as varchar )    as currencyTypeenum, " +
            "       cast(oi.count * oi.amount as varchar) as price, " +
            "       cast (oi.pay_type_enum as varchar)    as payType " +
            "from order_item oi " +
            "         inner join product p " +
            "                    on oi.product_id = p.id " +
            "         inner join product_company pc on pc.id = p.product_company_id " +
            "where oi.order_id = :orderId " +
            "order by oi.created_at desc", nativeQuery = true)
    List<OrderItemByOrderId> findAllByOrderId(Long orderId);


    //TO'LOV TURI VA VALYUTA BO'YICHA GURUHLAB UMUMIY BALANSNI YIG'IB KELYABDI
    @Query(value = "select oi.pay_type_enum as payType, " +         //cash...
            "       oi.currency_type        as currencyType, " +    //sum,dollar
            "       sum(oi.count*oi.amount) as price " +
            "from order_item oi " +
            "inner join orders o on o.id = oi.order_id " +
            "where o.warehouse_id = :whId " +
            "group by payType, currencyType " +
            "order by currencyType,payType ",
            nativeQuery = true)
    List<OrderPriceForPayType> getAllPriceByType(Long whId);

    //bu yo'l ombordan maxsulot chiqim bo'layotgan paytda pulni
    // hisoblash uchun FIFO qoidasi boyicha eng oldin kiritilganni narhida minus qilinadi
    @Query(value = "select oi.* from order_item as oi " +
            "    where (oi.helper_count>0 and oi.product_id = :productId) order by oi.created_at asc limit 1 ",nativeQuery = true)
    Optional<OrderItem> getFIFOOrderItem (@Param("productId")Long productId);
    
    @Query(value = "select row_number() over (order by sum(oi.count) desc )                   as rowNum, " +
            "       p.id                                                               as productId, " +
            "       p.name                                                             as productName, " +
            "       sum(-1 * oi.count)                                                 as soldCount, " +
            "       round(cast(-1 * sum(oi.original_main_price) as numeric(36, 2)), 2) as originalTotalPrice, " +
            "       sum(CASE " +
            "               WHEN (OI.currency_type = 'DOLLAR') THEN -1 * oi.main_price " +
            "               ELSE -1 * oi.main_price / o.currency_rate END)             as totalPrice, " +
            "       sum(CASE " +
            "               WHEN (OI.currency_type = 'DOLLAR') THEN -1 * oi.main_price " +
            "               ELSE -1 * oi.main_price / o.currency_rate END - " +
            "           (-1 * oi.original_main_price))                                   as difference " +
            "from order_item oi " +
            "         join product p on p.id = oi.product_id " +
            "         join orders o on o.id = oi.order_id " +
            "where o.order_type = 'EXPENDITURE' and o.warehouse_id = :whId " +
            "                                   and ws.created_at >= :startDate " +
            "                                   and ws.created_at <= :endDate" +
            "group by p.id, p.name ", nativeQuery = true)
    List<SoldProducts> getSoldProducts(@Param("whId") Long whId,
                                       @Param("startDate") Timestamp startDate,
                                       @Param("endDate") Timestamp endDate);
    
}
