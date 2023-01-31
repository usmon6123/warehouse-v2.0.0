package uz.ataboyev.warehouse.entity;

import lombok.*;
import uz.ataboyev.warehouse.entity.template.AbsLongEntity;
import uz.ataboyev.warehouse.enums.OrderType;
import uz.ataboyev.warehouse.enums.TradeType;
import uz.ataboyev.warehouse.payload.SaveOrderDTO;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "trade")
public class Trade extends AbsLongEntity {

    @Enumerated(EnumType.STRING)
    private TradeType tradeType;

    @Column(columnDefinition = "text")
    private String description = " ";

    //umumiy jamlangan summa bitta savdodagi
    private Double tradePriceSum = 0D;
    private Double tradePriceDollar = 0D;

    @OneToMany(mappedBy = "trade", fetch = FetchType.LAZY)
    private List<TradeItem> tradeItems;

//------------------------------------------------------------------------------
    @JoinColumn(insertable = false, updatable = false, name = "warehouse_id")
    @ManyToOne(optional = false)
    private Warehouse warehouse;

    @Column(name = "warehouse_id")
    private Long warehouseId;
//------------------------------------------------------------------------------


    public Trade( String description, Long warehouseId) {
        this.description = description;
        this.warehouseId = warehouseId;
    }

    public static Trade make(SaveOrderDTO orderDTO) {

        return null;
    }
}
