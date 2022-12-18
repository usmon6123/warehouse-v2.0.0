package uz.ataboyev.warehouse.payload.clientDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientHistoryDto {

    @NotNull(message = "client_order_list")
    private List<ClientOrderDto> clientOrderList;

    @NotNull(message = "general_price_sum")
    private Double generalPriceSum;

    @NotNull(message = "general_price_dollar")
    private Double generalPriceDollar;

}
