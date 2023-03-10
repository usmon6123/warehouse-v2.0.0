package uz.ataboyev.warehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor@NoArgsConstructor@Data
public class ProductResDtoByWhId {

    private Long productId;
    private String productCompany;
    private String productName;
    private String count;

    public static ProductResDtoByWhId makeDTO(ProductResDtoByWhIdImpl productImpl) {
        return new ProductResDtoByWhId(
                Long.parseLong(productImpl.getProductId()),
                productImpl.getProductCompanyName() ,
                productImpl.getProductName(),
                productImpl.getCount()
        );
    }
}
