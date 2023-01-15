package uz.ataboyev.warehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.ataboyev.warehouse.entity.Client;
import uz.ataboyev.warehouse.service.base.BaseService;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorkerSalaryResDto {
    private Long workerId;
    private String workerName;
    private double totalSum;
    private double totalDollar;
    private List<OneWorkerSalaryDto> oneWorkerSalariesDto;

    public static WorkerSalaryResDto make(Client worker, List<OneWorkerSalary> salaries, List<PriceAndType> workerSum) {
        double sum = 0d, dollar = 0d;
        for (PriceAndType priceAndType : workerSum) {
            if (priceAndType.getType().equals("SUM"))
                sum = Double.parseDouble(priceAndType.getPrice());
            else if (priceAndType.getType().equals("DOLLAR"))
                dollar = Double.parseDouble(priceAndType.getPrice());
        }

        return new WorkerSalaryResDto(
                worker.getId(),
                worker.getName(),
                sum,
                dollar,
                calculateSumma(salaries)
        );
    }


    private static List<OneWorkerSalaryDto> calculateSumma(List<OneWorkerSalary> salaries) {
    return salaries.stream().map(BaseService::OneWorkerSalaryToDTO).collect(Collectors.toList());
    }



}
