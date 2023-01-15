package uz.ataboyev.warehouse.entity;

import lombok.*;
import uz.ataboyev.warehouse.entity.template.AbsLongEntity;
import uz.ataboyev.warehouse.enums.CurrencyTypeEnum;
import uz.ataboyev.warehouse.payload.WorkerSalaryReqDto;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class WorkerSalary extends AbsLongEntity {

    //-------------------------------------------
    @ManyToOne
    @JoinColumn(insertable = false, updatable = false, name = "worker_id")
    private Client worker;

    @Column(name = "worker_id")
    private Long workerId;
    //-------------------------------------------

    @Column(nullable = false, name = "description")
    private String description;

    @Column(nullable = false, name = "sum")
    private Double sum;

    @Enumerated(EnumType.STRING)
    private CurrencyTypeEnum type; //SUM, DOLLAR


    public WorkerSalary(Long workerId, String description, Double sum, CurrencyTypeEnum type) {
        this.workerId = workerId;
        this.description = description;
        this.sum = sum;
        this.type = type;
    }

    public static WorkerSalary make(WorkerSalaryReqDto workerSalaryReqDto) {
        return new WorkerSalary(
                workerSalaryReqDto.getWorkerId(),
                workerSalaryReqDto.getDescription(),
                workerSalaryReqDto.getSum(),
                workerSalaryReqDto.getType()
        );
    }
}
