package uz.jl.trelloapprest.domains.project;

import lombok.*;
import uz.jl.trelloapprest.domains.auth.AuthUser;
import uz.jl.trelloapprest.domains.base.Auditable;
import uz.jl.trelloapprest.enums.WorkspaceStatus;
import uz.jl.trelloapprest.enums.WorkspaceType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 3:30 PM 8/24/22 on Wednesday in August
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Workspace extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "work_space_name")
    private String name;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    private WorkspaceType workspaceType;

    @Enumerated(EnumType.STRING)
    private WorkspaceStatus workspaceStatus;


    @ManyToMany(targetEntity = AuthUser.class,
            cascade = CascadeType.MERGE,
            fetch = FetchType.LAZY)
    @JoinTable(
            name = "work_space_members",
            joinColumns = @JoinColumn(name = "work_space_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "auth_user_id", referencedColumnName = "id")
    )
    private List<AuthUser> users;

    @Builder
    public Workspace(LocalDateTime createdAt, LocalDateTime updatedAt, Long createdBy, Long updatedBy, boolean isDeleted, Long id, String name, String description, WorkspaceType workspaceType, WorkspaceStatus workspaceStatus, List<AuthUser> users) {
        super(createdAt, updatedAt, createdBy, updatedBy, isDeleted);
        this.id = id;
        this.name = name;
        this.description = description;
        this.workspaceType = workspaceType;
        this.workspaceStatus = workspaceStatus;
        this.users = users;
    }
}
