package uz.ataboyev.warehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.ataboyev.warehouse.enums.PayTypeEnum;

import java.util.List;

@AllArgsConstructor@NoArgsConstructor@Data
public class OrderPriceDtoForPayTypeRes {

    private List<OrderPriceDtoForPayType> list;
    private Double allSum;
    private Double allDollar;
}
