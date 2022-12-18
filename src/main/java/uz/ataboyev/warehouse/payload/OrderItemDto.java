package uz.ataboyev.warehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.ataboyev.warehouse.enums.CurrencyTypeEnum;
import uz.ataboyev.warehouse.enums.PayTypeEnum;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class    OrderItemDto {

    @NotNull(message = "productId_not_null")
    private Long productId;

    @NotNull(message = "count_not_null")
    private Double count;

    @NotNull(message = "price_not_null")
    private Double amount; //dona summasi

    @NotNull(message = "currencyTypeEnum_not_null")
    private CurrencyTypeEnum currencyTypeEnum;

    @NotNull(message = "pay_type_enum_not_null")
    private PayTypeEnum payTypeEnum;

}
