package uz.ataboyev.warehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.ataboyev.warehouse.entity.TradeItem;
import uz.ataboyev.warehouse.enums.TradeType;
import uz.ataboyev.warehouse.payload.clientDtos.ClientOrderDto;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TradeReqDto {

   private TradeType tradeType;

   @NotNull(message = "trade_items_not_null")
   private List<TradeItemDto> tradeItemDtoList;

   private String description;

   @NotNull(message = "warehouseId_not_null")
   private Long warehouseId;
}
