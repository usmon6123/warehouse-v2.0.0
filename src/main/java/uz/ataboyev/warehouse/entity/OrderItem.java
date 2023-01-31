package uz.ataboyev.warehouse.entity;

import lombok.*;
import uz.ataboyev.warehouse.entity.template.AbsLongEntity;
import uz.ataboyev.warehouse.enums.CurrencyTypeEnum;
import uz.ataboyev.warehouse.enums.OrderType;
import uz.ataboyev.warehouse.enums.PayTypeEnum;
import uz.ataboyev.warehouse.payload.OrderItemDto;

import javax.persistence.*;


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

    private Double mainPrice; //mahsulotning tannarxi kirim bo'lganda amount*count ga teng bo'lsa, chiqimda esa sonini sotish narxiga emas bazadagi kirim narxiga hisoblab ko'paytiradi


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


    public static OrderItem make(OrderItemDto orderItemDto, Order order, Double mainPrice) {
        CurrencyTypeEnum type = orderItemDto.getCurrencyTypeEnum();
        PayTypeEnum payTypeEnum = orderItemDto.getPayTypeEnum();
        if ( order.getOrderType().equals(OrderType.INCOME)){
            type = CurrencyTypeEnum.DOLLAR;
            payTypeEnum=PayTypeEnum.DEFAULT;
        }
        return new OrderItem(
                order.getId(),
                orderItemDto.getProductId(),
                orderItemDto.getCount(),
                orderItemDto.getCount() > 0 ? orderItemDto.getCount() : 0d,
                type,
                orderItemDto.getAmount(),//dona summasi
                payTypeEnum,
                mainPrice
        );
    }


}
