package uz.ataboyev.warehouse.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.ataboyev.warehouse.enums.OrderType;
import uz.ataboyev.warehouse.payload.clientDtos.ClientDtoForPageable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderPageDTO {
    private Long orderId;
    private String date;
    private ClientDtoForPageable clientDto;
    private Double orderPriceSum;
    private Double orderPriceDollar;
    private OrderType orderType;


}
