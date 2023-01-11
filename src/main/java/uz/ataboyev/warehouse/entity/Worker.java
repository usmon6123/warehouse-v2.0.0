package uz.ataboyev.warehouse.entity;

import lombok.*;
import uz.ataboyev.warehouse.entity.template.AbsLongEntity;
import uz.ataboyev.warehouse.enums.CurrencyTypeEnum;
import uz.ataboyev.warehouse.enums.Type;
import uz.ataboyev.warehouse.payload.clientDtos.ClientReqDto;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "phone_number"})})
public class Worker extends AbsLongEntity {

    //-------------------------------------------
    @ManyToOne
    @JoinColumn(insertable = false,updatable = false, name = "worker_id")
    private Client worker;

    @Column( name = "worker_id")
    private Long workerId;
    //-------------------------------------------

    @Column(nullable = false, name = "name")
    private String description;

    @Column(nullable = false, name = "phone_number")
    private Double sum;

    private CurrencyTypeEnum type; //SUM, DOLLAR


}
