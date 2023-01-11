package uz.ataboyev.warehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.ataboyev.warehouse.enums.CurrencyTypeEnum;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorkersTotalSalaryResDto {

    private Long workerId;
    private String workerName;
    private Double sum;
    private Double dollar;


}
