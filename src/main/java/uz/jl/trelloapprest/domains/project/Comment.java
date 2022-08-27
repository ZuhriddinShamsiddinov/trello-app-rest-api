package uz.jl.trelloapprest.domains.project;

import lombok.*;
import uz.jl.trelloapprest.domains.auth.AuthUser;
import uz.jl.trelloapprest.domains.base.Auditable;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 4:20 PM 8/24/22 on Wednesday in August
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    private AuthUser authUser;

    @ManyToOne(fetch = FetchType.LAZY)
    private Task task;

    @Builder
    public Comment(Timestamp createdAt, Timestamp updatedAt, Long createdBy, Long updatedBy, boolean isDeleted, Long id, String body, AuthUser authUser, Task task) {
        super(createdAt, updatedAt, createdBy, updatedBy, isDeleted);
        this.id = id;
        this.body = body;
        this.authUser = authUser;
        this.task = task;
    }
}
