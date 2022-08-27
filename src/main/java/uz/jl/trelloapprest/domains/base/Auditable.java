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
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Auditable {
    protected Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());
    protected Timestamp updatedAt;
    protected Long createdBy;
    protected Long updatedBy;
    protected boolean isDeleted = false;
}
