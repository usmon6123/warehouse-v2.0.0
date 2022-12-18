package uz.ataboyev.warehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.ataboyev.warehouse.entity.Category;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryResDto {

    private Long id;
    private String name;

    public  static CategoryResDto make(Category category){
        return new CategoryResDto(category.getId(),category.getName());
    }

}
