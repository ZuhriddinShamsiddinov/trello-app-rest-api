package uz.jl.trelloapprest.dtos.project;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 6:19 PM 9/2/22 on Friday in September
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TaskUpdateDTO {
    @NotNull(message = "Id cannot be empty")
    private Long id;
    @NotBlank(message = "Title cannot be empty")
    private String title;
    @NotBlank(message = "Description cannot be null")
    private String description;
    @NotNull(message = "BoardColumnId cannot be empty")
    private Long boardColumnId;
}
