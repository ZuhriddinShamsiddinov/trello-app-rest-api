package uz.jl.trelloapprest.domains.project;

import lombok.*;
import uz.jl.trelloapprest.domains.base.Auditable;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 4:02 PM 8/24/22 on Wednesday in August
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BoardColumn extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private int cardOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    @Builder
    public BoardColumn(Timestamp createdAt, Timestamp updatedAt, Long createdBy, Long updatedBy, boolean isDeleted, Long id, String title, int cardOrder) {
        super(createdAt, updatedAt, createdBy, updatedBy, isDeleted);
        this.id = id;
        this.title = title;
        this.cardOrder = cardOrder;
    }
}
