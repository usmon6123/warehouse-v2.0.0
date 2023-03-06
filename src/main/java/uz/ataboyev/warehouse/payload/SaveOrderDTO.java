package uz.ataboyev.warehouse.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.ataboyev.warehouse.enums.OrderType;

import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SaveOrderDTO {

    private OrderType orderType;

    @NotNull(message = "order_items_not_null")
    private List<OrderItemDto> orderItemDtoList;

    @NotNull(message = "clientId_not_null")
    private Long clientId;

    private String description;

    @NotNull(message = "warehouseId_not_null")
    private Long warehouseId;

    private Double currencyRate;

    public SaveOrderDTO(OrderType orderType, @NotNull(message = "order_items_not_null") List<OrderItemDto> orderItemDtoList, @NotNull(message = "clientId_not_null") Long clientId, String description, @NotNull(message = "warehouseId_not_null") Long warehouseId) {
        this.orderType = orderType;
        this.orderItemDtoList = orderItemDtoList;
        this.clientId = clientId;
        this.description = description;
        this.warehouseId = warehouseId;
    }
}
