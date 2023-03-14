package uz.ataboyev.warehouse.payload.clientDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.ataboyev.warehouse.enums.CurrencyTypeEnum;
import uz.ataboyev.warehouse.enums.PayTypeEnum;
import uz.ataboyev.warehouse.payload.ClientItemsForHistory;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientOrderDto {

    @NotNull(message = "date")
    private String date;

//    @NotNull(message = "productCompanyName_not_null")
//    private String productCompanyName;

    @NotNull(message = "productName_not_null")
    private String productName;

    @NotNull(message = "count_not_null")
    private Double count;

    @NotNull(message = "price_not_null")
    private Double countSum;//bir donaning bahosi

    @NotNull(message = "currencyTypeEnum_not_null")
    private CurrencyTypeEnum currencyTypeEnum;

    @NotNull(message = "price_not_null")
    private Double price;//count * countSum

    @NotNull(message = "pay_type_enum_not_null")
    private PayTypeEnum payTypeEnum;

    public static ClientOrderDto make(OrderItemByOrderId orderItemByOrderId) {
        return new ClientOrderDto(
                orderItemByOrderId.getDate(),
//                orderItemByOrderId.getProductCompanyName(),
                orderItemByOrderId.getProductName(),
                StringParseDouble(orderItemByOrderId.getCount()),
                StringParseDouble(orderItemByOrderId.getCountSum()),
                CurrencyTypeEnum.valueOf(orderItemByOrderId.getCurrencyTypeEnum()),
                StringParseDouble(orderItemByOrderId.getPrice()),
                PayTypeEnum.valueOf(orderItemByOrderId.getPayType())
        );
    }

    private static Double StringParseDouble(String stringNumber) {
        return Double.parseDouble(stringNumber);
    }

    public static ClientOrderDto make2(ClientItemsForHistory clientItem) {
        Double counts = Double.parseDouble(clientItem.getCount());
        Double countSum = Double.parseDouble(clientItem.getCountSum());
        return new ClientOrderDto(
                clientItem.getData(),
                clientItem.getProductName(),
                counts,
                countSum,
                CurrencyTypeEnum.valueOf(clientItem.getCurrencyTypeEnum()),
                counts * countSum,
                PayTypeEnum.DEFAULT
        );
    }
}
