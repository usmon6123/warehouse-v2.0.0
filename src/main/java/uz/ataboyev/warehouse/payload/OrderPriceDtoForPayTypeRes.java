package uz.ataboyev.warehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderPriceDtoForPayTypeRes {

    private List<OrderPriceDtoForPayType> list;
    private Double allSum;
    private Double allDollar;



}
