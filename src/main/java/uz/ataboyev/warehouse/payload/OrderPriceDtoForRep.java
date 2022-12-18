package uz.ataboyev.warehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public interface OrderPriceDtoForRep {
    String getSum();
    String getDollar();
}
