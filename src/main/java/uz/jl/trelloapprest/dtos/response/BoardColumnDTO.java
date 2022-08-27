package uz.jl.trelloapprest.dtos.response;

import lombok.*;
import uz.jl.trelloapprest.domains.project.Board;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 3:19 PM 8/27/22 on Saturday in August
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BoardColumnDTO {
    private Long id;
    private String title;
    private int cardOrder;
    private Long boardId;
}
