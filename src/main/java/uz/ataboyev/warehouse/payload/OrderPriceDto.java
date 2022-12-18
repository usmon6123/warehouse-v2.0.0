package uz.ataboyev.warehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderPriceDto {
    private Double sum;
    private Double dollar;


    public static OrderPriceDto make(Double sum, Double dollar) {
        return new OrderPriceDto(sum, dollar);
    }
}
