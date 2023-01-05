package uz.ataboyev.warehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.ataboyev.warehouse.entity.Company;
import uz.ataboyev.warehouse.entity.ProductCompany;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductCompanyResDto {

    private Long id;
    private String name;


    public static ProductCompanyResDto make(ProductCompany productCompany) {
        return new ProductCompanyResDto(productCompany.getId(),productCompany.getName());
    }
}
