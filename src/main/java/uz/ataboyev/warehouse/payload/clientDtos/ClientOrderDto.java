package uz.ataboyev.warehouse.payload.clientDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.ataboyev.warehouse.enums.CurrencyTypeEnum;
import uz.ataboyev.warehouse.enums.PayTypeEnum;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientOrderDto {

    @NotNull(message = "date")
    private String date;

    @NotNull(message = "categoryId_not_null")
    private String categoryName;

    @NotNull(message = "productId_not_null")
    private String productName;

    @NotNull(message = "code_not_null")
    private String code;

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
                orderItemByOrderId.getCategoryName(),
                orderItemByOrderId.getProductName(),
                orderItemByOrderId.getCode(),
                Double.parseDouble(orderItemByOrderId.getCount()),
                Double.parseDouble(orderItemByOrderId.getCountSum()),
                CurrencyTypeEnum.valueOf(orderItemByOrderId.getCurrencyTypeEnum()),
                Double.parseDouble(orderItemByOrderId.getPrice()),
                PayTypeEnum.valueOf(orderItemByOrderId.getPayType())
        );
    }
}
