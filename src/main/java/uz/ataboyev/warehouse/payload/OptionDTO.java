package uz.ataboyev.warehouse.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;


@Data
//@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OptionDTO<T> {

    private Collection<T> options;

    private Collection<?> values = new ArrayList<>();

    private OptionDTO(Collection<T> options, Collection<?> values) {
        this.options = options;
        this.values = values;
    }

    private OptionDTO(Collection<T> options) {
        this.options = options;
    }


    public static <T> OptionDTO<T> makeOptionDTO(Collection<T> options, Collection<?> values) {
        return new OptionDTO<>(options, values);
    }

    public static <T> OptionDTO<T> makeOptionDTO(Collection<T> options) {
        return new OptionDTO<>(options);
    }


}
