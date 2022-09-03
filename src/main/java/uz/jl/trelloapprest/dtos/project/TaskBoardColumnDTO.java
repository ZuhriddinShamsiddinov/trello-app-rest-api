package uz.jl.trelloapprest.dtos.project;

import lombok.*;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 10:29 AM 9/3/22 on Saturday in September
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TaskBoardColumnDTO {
    private Long taskId;
    private Long boardColumnId;
}
