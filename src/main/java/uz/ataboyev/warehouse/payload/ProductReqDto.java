package uz.ataboyev.warehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.ataboyev.warehouse.entity.Product;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductReqDto {



    private String name;

    private String code;

    private Long categoryId;

    private Double minCount;


}
