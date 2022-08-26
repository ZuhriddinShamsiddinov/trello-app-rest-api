package uz.jl.trelloapprest.domains.project;

import lombok.*;
import uz.jl.trelloapprest.domains.auth.AuthUser;
import uz.jl.trelloapprest.domains.base.Auditable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 4:12 PM 8/24/22 on Wednesday in August
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Task extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    private BoardColumn boardColumn;

    @ManyToMany(targetEntity = AuthUser.class,
            cascade = CascadeType.MERGE,
            fetch = FetchType.LAZY)
    @JoinTable(
            name = "task_members",
            joinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "auth_user_id", referencedColumnName = "id")
    )
    private List<AuthUser> users;

    @Builder
    public Task(LocalDateTime createdAt, LocalDateTime updatedAt, Long createdBy, Long updatedBy, boolean isDeleted, Long id, String title, String description, BoardColumn boardColumn, List<AuthUser> users) {
        super(createdAt, updatedAt, createdBy, updatedBy, isDeleted);
        this.id = id;
        this.title = title;
        this.description = description;
        this.boardColumn = boardColumn;
        this.users = users;
    }
}
