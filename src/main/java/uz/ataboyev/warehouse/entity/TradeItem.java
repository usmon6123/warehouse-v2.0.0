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
public class TradeItem extends AbsLongEntity {


    //--------------------------------------------------------------------------
    @JoinColumn(name = "trade_id", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Trade trade;

    @Column(name = "trade_id", nullable = false)
    private Long tradeId;

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

    private Double mainPrice; // umumiy tannarx summa




    public static List<TradeItem> makeList(List<OrderItemDto> orderItemDtoList, Long orderId) {

//        return orderItemDtoList.stream().map(orderItemDto -> make(orderItemDto, orderId))
//                .collect(Collectors.toList());
        return null;

    }

    public TradeItem(Long tradeId, Long productId, Double count, CurrencyTypeEnum currencyType, Double amount, Double mainPrice) {
        this.tradeId = tradeId;
        this.productId = productId;
        this.count = count;
        this.currencyType = currencyType;
        this.amount = amount;
        this.mainPrice = mainPrice;
    }




}
