package uz.ataboyev.warehouse.entity;

import lombok.*;
import uz.ataboyev.warehouse.entity.template.AbsLongEntity;
import uz.ataboyev.warehouse.payload.ProductReqDto;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name", "code"})
})
public class Product extends AbsLongEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String code;

    private Double minCount;//miqdor shundan kamysa sms jo'natadi

    private Double count = 0D;//bazada qancha borligini bildiradi


    //----------------------------------------------------------------------------
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(insertable = false, updatable = false, name = "category_id")
    private Category category;

    @Column(name = "category_id")
    private Long categoryId;
    //----------------------------------------------------------------------------


    public Product(String name, String code, Long categoryId, Double minCount) {
        this.name = name;
        this.code = code;
        this.categoryId = categoryId;
        this.minCount = minCount;
    }

    public static Product make(ProductReqDto productReqDto) {
        return new Product(productReqDto.getName(), productReqDto.getCode(), productReqDto.getCategoryId(), productReqDto.getMinCount());
    }
}
