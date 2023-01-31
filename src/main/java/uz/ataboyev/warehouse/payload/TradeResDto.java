package uz.ataboyev.warehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.ataboyev.warehouse.enums.TradeType;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TradeResDto {

   private String date;

   private TradeType tradeType;

   private List<TradeItemDto> tradeItemDtoList;

   private String description;

   private Double totalSum;
   private Double totalDollar;

}
