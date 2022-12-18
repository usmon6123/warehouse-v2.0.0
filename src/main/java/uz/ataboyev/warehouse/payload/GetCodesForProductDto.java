package uz.ataboyev.warehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor@NoArgsConstructor@Data
public class GetCodesForProductDto {
    private Long id;
    private String code;

    public static GetCodesForProductDto make(GetCodesForProduct getCodesForProduct) {
        return new GetCodesForProductDto(
                Long.parseLong(getCodesForProduct.getId()),
                getCodesForProduct.getCode()
        );
    }
}
