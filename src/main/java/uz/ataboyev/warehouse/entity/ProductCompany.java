package uz.ataboyev.warehouse.entity;

import lombok.*;
import uz.ataboyev.warehouse.entity.template.AbsLongEntity;
import uz.ataboyev.warehouse.exception.RestException;
import uz.ataboyev.warehouse.payload.ApiResult;
import uz.ataboyev.warehouse.payload.ProductCompanyReqDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProductCompany extends AbsLongEntity {

//--------------------------------------------------------------------------

    @JoinColumn(insertable = false, updatable = false, name = "whId")
    @ManyToOne
    private Warehouse warehouse;

    @Column(name = "whId")
    private Long whId;

//--------------------------------------------------------------------------

    @Column(nullable = false,unique = true)
    private String name;


    public ProductCompany(Long whId, String name) {
        this.whId = whId;
        this.name = name;
    }

    public static ProductCompany make(ProductCompanyReqDto productCompanyReqDto) {
        try {
            return new ProductCompany(productCompanyReqDto.getWhId(), productCompanyReqDto.getName());
        } catch (Exception e) {
            RestException.restThrow("Maxsulot firmasi qo'shishda hatolik yuz berdi");
            e.printStackTrace();
        }
        return null;
    }

    public static void edit(ProductCompany prodCompany, ProductCompanyReqDto productCompanyReqDto) {
        prodCompany.setName(productCompanyReqDto.getName());
    }
}
