package uz.ataboyev.warehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.ataboyev.warehouse.enums.CurrencyTypeEnum;
import uz.ataboyev.warehouse.enums.PayTypeEnum;

@AllArgsConstructor@NoArgsConstructor@Data
public class OrderPriceDtoForPayType {

    private PayTypeEnum payTypeEnum;
    private Double sum;
    private Double dollar;

//        private PayTypeEnum payTypeEnum;
//        private CurrencyTypeEnum type;
//        private Double price;
//
//        public static OrderPriceDtoForPayType make(OrderPriceForPayType orderPriceForPayType) {
//        return new OrderPriceDtoForPayType(
//                PayTypeEnum.valueOf(orderPriceForPayType.getPayType()),
//                CurrencyTypeEnum.valueOf(orderPriceForPayType.getCurrencyType()),
//                Double.parseDouble(orderPriceForPayType.getPrice())
//        );
//        }

}
