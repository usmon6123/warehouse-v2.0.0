package uz.ataboyev.warehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomPage<T> {
    private List<T> content; // Elementlar
    private int numberOfElements; // Current page dagi elementlar soni
    private int number; // Current page number
    private long totalElements; // Barcha elementlar soni
    private int totalPages; //Barcha page lar soni
    private int size; // Nechta so'ragani

}
