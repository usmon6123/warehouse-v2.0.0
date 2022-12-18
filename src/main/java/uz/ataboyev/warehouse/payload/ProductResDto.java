package uz.ataboyev.warehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.ataboyev.warehouse.entity.Product;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResDto {
    private Long id;
    private String name;
    private String code;
    private Long categoryId;
    private Double minCount;
    private Double count;




    public static ProductResDto makeDTO(Product product) {
        return new ProductResDto(
                product.getId(),
                product.getName(),
                product.getCode(),
                product.getCategoryId(),
                product.getMinCount(),
                product.getCount()
        );
    }
}
