package uz.jl.trelloapprest.domains.project;

import lombok.*;
import uz.jl.trelloapprest.domains.auth.AuthUser;
import uz.jl.trelloapprest.domains.base.Auditable;
import uz.jl.trelloapprest.enums.Visibility;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 3:55 PM 8/24/22 on Wednesday in August
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Board extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String background;

    @Enumerated(EnumType.STRING)
    private Visibility visibility;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Workspace workspace;

    @ManyToMany(targetEntity = AuthUser.class,
            cascade = CascadeType.MERGE,
            fetch = FetchType.LAZY)
    @JoinTable(
            name = "board_members",
            joinColumns = @JoinColumn(name = "board_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "auth_user_id", referencedColumnName = "id")
    )
    private List<AuthUser> users;


    @Builder
    public Board(LocalDateTime createdAt, LocalDateTime updatedAt, Long createdBy, Long updatedBy, boolean isDeleted, Long id, String name, String background, Visibility visibility, Workspace workspace, List<AuthUser> users) {
        super(createdAt, updatedAt, createdBy, updatedBy, isDeleted);
        this.id = id;
        this.name = name;
        this.background = background;
        this.visibility = visibility;
        this.workspace = workspace;
        this.users = users;
    }
}
