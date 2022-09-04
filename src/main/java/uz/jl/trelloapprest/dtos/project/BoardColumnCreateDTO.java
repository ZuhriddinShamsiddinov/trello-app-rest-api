package uz.jl.trelloapprest.dtos.project;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 3:17 PM 8/27/22 on Saturday in August
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BoardColumnCreateDTO {
    @NotBlank(message = "Title cannot be null")
    private String title;
    @NotNull(message = "CardOrder cannot be null")
    private int cardOrder;
    @NotNull(message = "BoardId cannot be null")
    private Long boardId;
}
