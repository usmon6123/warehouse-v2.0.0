package uz.ataboyev.warehouse.entity;

import lombok.*;
import uz.ataboyev.warehouse.entity.template.AbsLongEntity;
import uz.ataboyev.warehouse.enums.CurrencyTypeEnum;
import uz.ataboyev.warehouse.enums.OrderType;
import uz.ataboyev.warehouse.enums.PayTypeEnum;
import uz.ataboyev.warehouse.payload.OrderItemDto;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

import static uz.ataboyev.warehouse.service.base.BaseService.minus1;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderItem extends AbsLongEntity {

    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;


    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @JoinColumn(insertable = false, updatable = false, name = "product_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @Column(nullable = false, name = "product_id")
    private Long productId;

    @Column(nullable = false)
    private Double count;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CurrencyTypeEnum currencyType;

    @Column(nullable = false)
    private Double amount;//dona summasi

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PayTypeEnum payTypeEnum;//to'lov turi


    public OrderItem(Long orderId, Long productId, Double count, CurrencyTypeEnum currencyType, Double amount, PayTypeEnum payTypeEnum) {
        this.orderId = orderId;
        this.productId = productId;
        this.count = count;
        this.currencyType = currencyType;
        this.amount = amount;
        this.payTypeEnum = payTypeEnum;
    }

    public static List<OrderItem> makeList(List<OrderItemDto> orderItemDtoList, Long orderId, OrderType orderType) {

        return orderItemDtoList.stream().map(orderItemDto -> make(orderItemDto, orderId, orderType))
                .collect(Collectors.toList());

    }

    public static OrderItem make(OrderItemDto orderItemDto, Long orderId, OrderType orderType) {
        double a = 1D;
        if (orderType.equals(OrderType.EXPENDITURE)) a = minus1;

        return new OrderItem(
                orderId,
                orderItemDto.getProductId(),
                a * orderItemDto.getCount(),
                orderItemDto.getCurrencyTypeEnum(),
                orderItemDto.getAmount(),
                orderItemDto.getPayTypeEnum()
        );
    }
}
