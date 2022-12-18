package uz.ataboyev.warehouse.entity;

import uz.ataboyev.warehouse.entity.template.AbsLongEntity;
import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "warehouse_id"})})
public class Category extends AbsLongEntity {

    @Column(nullable = false)
    private String name;

    //----------------------------------------------------------------------------
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(insertable = false, updatable = false, name = "warehouse_id")
    private Warehouse warehouse;

    @Column(name = "warehouse_id")
    private Long warehouseId;
    //----------------------------------------------------------------------------


    public Category(String name, Long warehouseId) {
        this.name = name;
        this.warehouseId = warehouseId;
    }
}
