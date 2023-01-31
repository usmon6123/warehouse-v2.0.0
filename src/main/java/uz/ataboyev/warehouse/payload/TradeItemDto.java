package uz.ataboyev.warehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.ataboyev.warehouse.enums.CurrencyTypeEnum;

@AllArgsConstructor@NoArgsConstructor@Data
public class TradeItemDto {

    private Long productId;

    private String productName;

    private Double count;

    private Double amount;//dona summasi

    private Double originalAmount; //maxsulot bazaga qaytib kelganda bunga qiymat dollarda kiritilishi kerak

    private CurrencyTypeEnum currencyType; //to'lov valyutasi SUM yoki DOLLAR


}
