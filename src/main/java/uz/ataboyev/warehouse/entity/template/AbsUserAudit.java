package uz.ataboyev.warehouse.entity.template;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.UUID;


/**
 * @author Muhammad Mo'minov
 * 06.11.2021
 * @apiNote OBJECTNI OCHGAN YOKI UNI O'ZGARTIRGAN USERNI OLIB BERISH UCHUN XIZMAT QILADI
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@ToString
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbsUserAudit extends AbsDateAudit {
//
//    @CreatedBy
//    @Column(name = "created_by_id", updatable = false)
//    private UUID createdById; // KIM CREATE QILGANI
//
//    @LastModifiedBy
//    @Column(name = "updated_by_id")
//    private UUID updatedById; // KIM O'ZGARTIRGANI
}
