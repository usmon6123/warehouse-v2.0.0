package uz.ataboyev.warehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.ataboyev.warehouse.enums.CurrencyTypeEnum;


public interface WorkerHistorySalary {

     String getDate();
     String getWorkerId();
     String getWorkerName();
     String getSum();
     String getType(); //SUM, DOLLAR
     String getDescription();


}
