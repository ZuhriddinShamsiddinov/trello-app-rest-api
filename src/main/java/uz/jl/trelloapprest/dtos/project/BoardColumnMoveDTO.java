package uz.jl.trelloapprest.dtos.project;

import lombok.*;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 9:32 AM 9/5/22 on Monday in September
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardColumnMoveDTO {
    private Long boardColumnId;
    private Long boardId;
    private int movingOrder;
}
