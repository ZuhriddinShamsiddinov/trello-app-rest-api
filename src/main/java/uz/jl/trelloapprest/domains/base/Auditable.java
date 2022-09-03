package uz.jl.trelloapprest.domains.base;

import lombok.*;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 4:40 PM 8/24/22 on Wednesday in August
 */
@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Auditable {
    protected Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());
    protected Timestamp updatedAt;
    protected Long createdBy;
    protected Long updatedBy;
    protected boolean isDeleted = false;

    public Auditable(Timestamp createdAt, Timestamp updatedAt, Long createdBy, Long updatedBy, boolean isDeleted) {
        this.createdAt = Timestamp.valueOf(LocalDateTime.now());
        this.updatedAt = updatedAt;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.isDeleted = isDeleted;
    }
}
