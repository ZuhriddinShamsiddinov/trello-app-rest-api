package uz.jl.trelloapprest.dtos.project;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 3:30 PM 9/4/22 on Sunday in September
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentCreateDTO {
    @NotBlank(message = "Body cannot be empty")
    private String body;
    @NotNull(message = "TaskId cannot be empty")
    private Long taskId;
}
