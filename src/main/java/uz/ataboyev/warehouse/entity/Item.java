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
public class Item extends AbsLongEntity {

//--------------------------------------------------------------------------

    @JoinColumn(insertable = false, updatable = false, name = "product_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @Column(nullable = false, name = "product_id")
    private Long productId;

//--------------------------------------------------------------------------

    @Column(nullable = false)
    private Double count;

    @Column(nullable = false)
    private Double amount;//dona summasi

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CurrencyTypeEnum currencyType; //to'lov valyutasi SUM yoki DOLLAR

    //-----------------pastdagi fieldlar backendchi uchun-----------------------------------

    private Double originalAmount;//tannarx dona summasi

    private Double mainPrice; //count * amount

    private Double OriginalMainPrice; //mahsulotning tannarxi kirim bo'lganda amount*count ga teng bo'lsa, chiqimda esa originalAmount*count  ga tengdir

    //asosiy narxni hisoblash uchun if(count > 0) countni o'zlashtiramiz bunga
    //aks holda 0 beramiz
    //BU FIELD FRONTGA KO'RINMAYDI BACKEND UCHUN
    @Column(nullable = false)
    private Double helperCount;





}
