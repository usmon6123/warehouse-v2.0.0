package uz.ataboyev.warehouse.entity.template;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


@EqualsAndHashCode(callSuper = true)
@Data
@MappedSuperclass
public abstract class AbsIntegerEntity extends AbsDateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

}
