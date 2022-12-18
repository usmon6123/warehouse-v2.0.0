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

    @NotNull(message = "order_items_not_null")
    private List<OrderItemDto> orderItemDtoList;

    @NotNull(message = "order_type_not_null")
    private OrderType orderType;

    @NotNull(message = "clientId_not_null")
    private Long clientId;

    private String description;

    @NotNull(message = "warehouseId_not_null")
    private Long warehouseId;
}
