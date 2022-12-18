package uz.ataboyev.warehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.ataboyev.warehouse.entity.Category;
import uz.ataboyev.warehouse.entity.Client;
import uz.ataboyev.warehouse.entity.Product;

@Data@AllArgsConstructor@NoArgsConstructor
public class OptionResDto {

    private Long id;

    private String name;

    public OptionResDto(String name) {
        this.name = name;
    }

    public static OptionResDto make(Product product) {
        return new OptionResDto(product.getId(),product.getName());
    }

    public static OptionResDto make(Category category) {
        return new OptionResDto(category.getId(),category.getName());
    }

    public static OptionResDto make(Client client) {
        return new OptionResDto(client.getId(),client.getName());
    }

    public static OptionResDto make(OptionResIn optionResIn) {
        return new OptionResDto(optionResIn.getName());
    }
}
