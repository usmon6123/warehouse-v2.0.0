package uz.ataboyev.warehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.ataboyev.warehouse.enums.CurrencyTypeEnum;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetOneSalaryByIWorkerId {

    private String date;
    private String description;
    private Double sum;
    private CurrencyTypeEnum type; //SUM, DOLLAR

}
