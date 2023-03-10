package uz.ataboyev.warehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.ataboyev.warehouse.enums.CurrencyTypeEnum;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorkerSalaryReqDto {

    private Long workerId;
    private String description;
    private Double sum;
    private CurrencyTypeEnum type; //SUM, DOLLAR

}
