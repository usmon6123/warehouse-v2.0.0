package uz.ataboyev.warehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.ataboyev.warehouse.enums.CurrencyTypeEnum;

import java.sql.Timestamp;

import static uz.ataboyev.warehouse.service.base.BaseService.timestampToString_dd_MM_yyyy;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorkerHistorySalaryResDto {

    private String date;
    private Long workerId;
    private String workerName;
    private Double sum;
    private CurrencyTypeEnum type; //SUM, DOLLAR
    private String description;

    public static WorkerHistorySalaryResDto make(WorkerHistorySalary workerHistorySalary) {
        String date = timestampToString_dd_MM_yyyy(Timestamp.valueOf(workerHistorySalary.getDate()));
        return new WorkerHistorySalaryResDto(
                date,
                Long.parseLong(workerHistorySalary.getWorkerId()),
                workerHistorySalary.getWorkerName(),
                Double.parseDouble(workerHistorySalary.getSum()),
                CurrencyTypeEnum.valueOf(workerHistorySalary.getType()),
                workerHistorySalary.getDescription()
            );
    }
}
