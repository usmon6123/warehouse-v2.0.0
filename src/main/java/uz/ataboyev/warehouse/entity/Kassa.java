package uz.ataboyev.warehouse.entity;

import lombok.*;
import uz.ataboyev.warehouse.entity.template.AbsLongEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Kassa extends AbsLongEntity {

    private Double sum = 0d;
    private Double dollar = 0d;

    @Column(columnDefinition = "text")
    private String description;
}
