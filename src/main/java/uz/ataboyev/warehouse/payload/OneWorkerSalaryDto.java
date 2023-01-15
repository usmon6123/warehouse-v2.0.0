package uz.ataboyev.warehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.ataboyev.warehouse.enums.CurrencyTypeEnum;
import uz.ataboyev.warehouse.payload.clientDtos.ClientOrderDto;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OneWorkerSalaryDto {

   private String date;
   private String description;
   private String sum;
   private String type;


}
