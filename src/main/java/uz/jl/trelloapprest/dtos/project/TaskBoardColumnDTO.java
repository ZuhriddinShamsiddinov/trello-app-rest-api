package uz.jl.trelloapprest.dtos.project;

import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 10:29 AM 9/3/22 on Saturday in September
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TaskBoardColumnDTO {
    @NotNull(message = "TaskId cannot be empty")
    private Long taskId;
    @NotNull(message = "BoardColumnId cannot be empty")
    private Long boardColumnId;
}
