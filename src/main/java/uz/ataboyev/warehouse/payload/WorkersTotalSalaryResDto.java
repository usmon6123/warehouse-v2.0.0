package uz.ataboyev.warehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.ataboyev.warehouse.enums.CurrencyTypeEnum;
import uz.ataboyev.warehouse.exception.RestException;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorkersTotalSalaryResDto {

    private Long workerId;
    private String workerName;
    private Double totalSum;
    private Double totalDollar;


    public static WorkersTotalSalaryResDto make(GetAllWorkersTotalSalaries workerTotalSalaries) {
        try {
            double totalSum = 0d, totalDollar = 0d;
            Long workerId = Long.parseLong(workerTotalSalaries.getWorkerId());
            if (workerTotalSalaries.getCurrencyType().equals(CurrencyTypeEnum.SUM.toString()))
                totalSum = Double.parseDouble(workerTotalSalaries.getPrice());
            else if (workerTotalSalaries.getCurrencyType().equals(CurrencyTypeEnum.DOLLAR.toString()))
                totalDollar = Double.parseDouble(workerTotalSalaries.getPrice());
            return new WorkersTotalSalaryResDto(
                    workerId,
                    workerTotalSalaries.getWorkerName(),
                    totalSum,
                    totalDollar
            );
        } catch (Exception e) {
            throw RestException.restThrow("Xatolik yuz berdi");
        }
    }
}
