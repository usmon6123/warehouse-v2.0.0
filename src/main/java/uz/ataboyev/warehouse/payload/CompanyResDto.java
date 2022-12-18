package uz.ataboyev.warehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.ataboyev.warehouse.entity.Company;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompanyResDto {

    private Long id;
    private String name;

    public CompanyResDto(Company company) {
        this.id = company.getId();
        this.name = company.getName();
    }
}
