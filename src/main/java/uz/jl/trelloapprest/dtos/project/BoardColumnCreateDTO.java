package uz.jl.trelloapprest.dtos.project;

import lombok.*;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 3:17 PM 8/27/22 on Saturday in August
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BoardColumnCreateDTO {
    private String title;
    private int cardOrder;
    private Long boardId;
}
