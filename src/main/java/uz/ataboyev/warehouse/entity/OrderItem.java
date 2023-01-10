package uz.ataboyev.warehouse.entity;

import lombok.*;
import uz.ataboyev.warehouse.entity.template.AbsLongEntity;
import uz.ataboyev.warehouse.enums.CurrencyTypeEnum;
import uz.ataboyev.warehouse.enums.PayTypeEnum;
import uz.ataboyev.warehouse.payload.OrderItemDto;

import javax.persistence.*;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderItem extends AbsLongEntity {


    //--------------------------------------------------------------------------
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    @Column(name = "order_id", nullable = false)
    private Long orderId;

//--------------------------------------------------------------------------

    @JoinColumn(insertable = false, updatable = false, name = "product_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @Column(nullable = false, name = "product_id")
    private Long productId;

//--------------------------------------------------------------------------

    @Column(nullable = false)
    private Double count;

    //asosiy narxni hisoblash uchun if(count > 0) countni o'zlashtiramiz bunga
    //aks holda 0 beramiz
    //BU FIELD FRONTGA KO'RINMAYDI BACKEND UCHUN
    @Column(nullable = false)
    private Double helperCount;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CurrencyTypeEnum currencyType; //to'lov valyutasi SUM yoki DOLLAR

    @Column(nullable = false)
    private Double amount;//dona summasi

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PayTypeEnum payTypeEnum;//to'lov turi

    private Double mainPrice;


    public OrderItem(Long orderId, Long productId, Double count, CurrencyTypeEnum currencyType, Double amount, PayTypeEnum payTypeEnum) {
        this.orderId = orderId;
        this.productId = productId;
        this.count = count;
        this.currencyType = currencyType;
        this.amount = amount;
        this.payTypeEnum = payTypeEnum;
    }

    public OrderItem(Long orderId, Long productId, Double count, Double helperCount, CurrencyTypeEnum currencyType, Double amount, PayTypeEnum payTypeEnum, Double mainPrice) {
        this.orderId = orderId;
        this.productId = productId;
        this.count = count;
        this.helperCount = helperCount;
        this.currencyType = currencyType;
        this.amount = amount;
        this.payTypeEnum = payTypeEnum;
        this.mainPrice = mainPrice;
    }

    public OrderItem(Long orderId, Long productId, Double count, Double helperCount, CurrencyTypeEnum currencyType, Double amount, PayTypeEnum payTypeEnum) {
        this.orderId = orderId;
        this.productId = productId;
        this.count = count;
        this.helperCount = helperCount;
        this.currencyType = currencyType;
        this.amount = amount;
        this.payTypeEnum = payTypeEnum;
    }

    public static List<OrderItem> makeList(List<OrderItemDto> orderItemDtoList, Long orderId) {

//        return orderItemDtoList.stream().map(orderItemDto -> make(orderItemDto, orderId))
//                .collect(Collectors.toList());
        return null;

    }

    public static OrderItem make(OrderItemDto orderItemDto, Long orderId, Double mainPrice) {

        return new OrderItem(
                orderId,
                orderItemDto.getProductId(),
                orderItemDto.getCount(),
                orderItemDto.getCount() > 0 ? orderItemDto.getCount() : 0d,
                orderItemDto.getCurrencyTypeEnum(),
                orderItemDto.getAmount(),//dona summasi
                orderItemDto.getPayTypeEnum(),
                mainPrice
        );
    }


}
