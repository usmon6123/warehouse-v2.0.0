package uz.ataboyev.warehouse.entity;

import lombok.*;
import uz.ataboyev.warehouse.entity.template.AbsLongEntity;
import uz.ataboyev.warehouse.enums.OrderType;
import uz.ataboyev.warehouse.payload.SaveOrderDTO;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "orders")
public class Order extends AbsLongEntity {

    @Enumerated(EnumType.STRING)
    private OrderType orderType;

//------------------------------------------------------------------------------
    @JoinColumn(updatable = false, insertable = false, name = "client_id")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Client client;

    @Column(name = "client_id", nullable = false)
    private Long clientId;
//------------------------------------------------------------------------------

    @Column(columnDefinition = "text")
    private String description = " ";

    //umumiy jamlangan summa bitta savdodagi
    private Double orderPriceSum = 0D;
    private Double orderPriceDollar = 0D;


    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderItem> orderItems;


//------------------------------------------------------------------------------
    @JoinColumn(insertable = false, updatable = false, name = "warehouse_id")
    @ManyToOne(optional = false)
    private Warehouse warehouse;

    @Column(name = "warehouse_id")
    private Long warehouseId;
//------------------------------------------------------------------------------

    private Double currencyRate;// ordereni ichida summ qatnashsa valyuta kursini kiritish kerak bo'ladi




    public Order(OrderType orderType, Long clientId, String description, Long warehouseId) {
        this.orderType = orderType;
        this.clientId = clientId;
        this.description = description;
        this.warehouseId = warehouseId;
    }

    public static Order make(SaveOrderDTO orderDTO) {

        return new Order(
                orderDTO.getOrderType(),
                orderDTO.getClientId(),
                orderDTO.getDescription(),
                orderDTO.getWarehouseId()
        );
    }
}
