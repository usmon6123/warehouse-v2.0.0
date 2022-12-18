package uz.ataboyev.warehouse.entity.template;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.sql.Timestamp;


@Getter
@Setter
@ToString
@MappedSuperclass
public abstract class AbsDateAudit implements Serializable {

    @CreationTimestamp
    @Column(updatable = false,name = "created_at")
    private Timestamp createdAt;//OBJECT YANGI OCHILGANDA ISHLAYDI

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;//OBJECTga TEGILGANDA ISHLAYDI

    private boolean deleted = false;
}
