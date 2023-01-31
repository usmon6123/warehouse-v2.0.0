package uz.ataboyev.warehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.ataboyev.warehouse.enums.TradeType;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TradePriceResDto {

   private Double sum;
   private Double dollar;

}
