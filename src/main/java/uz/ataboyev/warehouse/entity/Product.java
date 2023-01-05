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
        @UniqueConstraint(columnNames = {"name", "product_company_id"})
})
public class Product extends AbsLongEntity {

    @Column(nullable = false)
    private String name;

    private Double minCount;//miqdor shundan kamaysa sms jo'natadi

    private Double count = 0D;//bazada qancha borligini bildiradi


    //----------------------------------------------------------------------------
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(insertable = false, updatable = false, name = "product_company_id")
    private ProductCompany productCompany;

    @Column(name = "product_company_id")
    private Long productCompanyId;
    //----------------------------------------------------------------------------


    public Product(String name, Long productCompanyId, Double minCount) {
        this.name = name;
        this.productCompanyId = productCompanyId;
        this.minCount = minCount;
    }

    public static Product make(ProductReqDto productReqDto) {
        return new Product(productReqDto.getName(),productReqDto.getProductCompanyId(), productReqDto.getMinCount());
    }
}
