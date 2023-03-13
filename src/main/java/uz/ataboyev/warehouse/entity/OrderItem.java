package uz.ataboyev.warehouse.entity;

import lombok.*;
import uz.ataboyev.warehouse.entity.template.AbsLongEntity;
import uz.ataboyev.warehouse.enums.CurrencyTypeEnum;
import uz.ataboyev.warehouse.enums.OrderType;
import uz.ataboyev.warehouse.enums.PayTypeEnum;
import uz.ataboyev.warehouse.payload.OrderItemDto;

import javax.persistence.*;
import java.util.Objects;


@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderItem extends AbsLongEntity {


    //--------------------------------------------------------------------------
    @JoinColumn(insertable = false, updatable = false, name = "order_id")
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

    private Double mainPrice; //count * amount odatda kirim bo'lganda chiqimda sumda ham qiymat aralashib qolishi mumkin

    private Double originalAmount;//tannarx dona summasi dollarda har doim

    private Double OriginalMainPrice; //mahsulotning tannarxi kirim bo'lganda amount*count ga teng bo'lsa, chiqimda esa originalAmount*count  ga tengdir


    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PayTypeEnum payTypeEnum;//to'lov turi

    public OrderItem(Long orderId, Long productId, Double count, Double helperCount, CurrencyTypeEnum currencyType, Double amount, Double originalAmount, Double mainPrice, Double originalMainPrice, PayTypeEnum payTypeEnum) {
        this.orderId = orderId;
        this.productId = productId;
        this.count = count;
        this.helperCount = helperCount;
        this.currencyType = currencyType;
        this.amount = amount;
        this.originalAmount = originalAmount;
        this.mainPrice = mainPrice;
        OriginalMainPrice = originalMainPrice;
        this.payTypeEnum = payTypeEnum;
    }

    public static OrderItem make(OrderItemDto orderItemDto, Order order, Double originalMainPrise,Client savdo) {
        CurrencyTypeEnum type = orderItemDto.getCurrencyTypeEnum();
        PayTypeEnum payTypeEnum = Objects.equals(orderItemDto.getPayTypeEnum(), null) ? PayTypeEnum.DEFAULT : orderItemDto.getPayTypeEnum();
        double mainPrice = 0d, originalAmount = 0d;

        if (order.getClientId().equals(savdo.getId())) {
            if (order.getOrderType().equals(OrderType.INCOME)) {
                if (orderItemDto.getCurrencyTypeEnum().equals(CurrencyTypeEnum.SUM)) {
                    double inDollar = sumToDollar(orderItemDto.getAmount(), order.getCurrencyRate());
                    originalMainPrise = inDollar * orderItemDto.getCount();
                }
            }
        }

        //agar kirim bo'lsa
        else if (order.getOrderType().equals(OrderType.INCOME)) {
            if (type == CurrencyTypeEnum.DOLLAR) originalAmount = orderItemDto.getAmount();
            else originalAmount = sumToDollar(orderItemDto.getAmount(), order.getCurrencyRate());
        } else {
            //Bir donaning o'rtacha narxini dollarda ifodalaydi, sumda kirib kelgan bo'lsa orderdagi kurs orqali do'llarga o'giradi
            originalAmount = orderItemDto.getCurrencyTypeEnum().equals(CurrencyTypeEnum.SUM)
                    ? sumToDollar(originalMainPrise / orderItemDto.getCount(), order.getCurrencyRate())
                    : originalMainPrise / orderItemDto.getCount();
        }
        return new OrderItem(
                order.getId(),
                orderItemDto.getProductId(),
                orderItemDto.getCount(),
                orderItemDto.getCount() > 0 ? orderItemDto.getCount() : 0d,//helper count
                type,
                orderItemDto.getAmount(),//dona summasi
                originalAmount,
                orderItemDto.getCount() * orderItemDto.getAmount(),
                originalMainPrise,
                payTypeEnum
        );
    }

    public static double sumToDollar(Double sum, Double currencyRate) {
        return sum / currencyRate;
    }


}
