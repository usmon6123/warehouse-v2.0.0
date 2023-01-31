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
    private Double totalDollar;
    private Double totalSum;


    public static WorkersTotalSalaryResDto make(GetAllWorkersTotalSalaries workerTotalSalaries) {
        try {
           return new WorkersTotalSalaryResDto(
                   Long.parseLong(workerTotalSalaries.getWorkerId()),
                   workerTotalSalaries.getWorkerName(),
                   parseStringToDouble(workerTotalSalaries.getTotalDollar()),
                   parseStringToDouble(workerTotalSalaries.getTotalSum())
           );
        } catch (Exception e) {
            throw RestException.restThrow("Xatolik yuz berdi");
        }
    }

    private static Double parseStringToDouble(String price) {
       try {
           return Double.parseDouble(price);
       }catch (Exception e){
           return 0d;
       }

    }
}
