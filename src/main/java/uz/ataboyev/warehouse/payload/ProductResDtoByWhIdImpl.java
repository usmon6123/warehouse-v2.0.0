package uz.ataboyev.warehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.ataboyev.warehouse.entity.Product;

public interface ProductResDtoByWhIdImpl {

    String getProductId();
    String getProductCompanyName();
    String getProductName();
    String getCount();




//    public static ProductResDtoByWhId makeDTO(Product product) {
//        return new ProductResDtoByWhId(
//                product.getCategory().getName(),
//                product.getName(),
//                product.getCode(),
//                product.getCount().toString()
//        );
//    }
}
