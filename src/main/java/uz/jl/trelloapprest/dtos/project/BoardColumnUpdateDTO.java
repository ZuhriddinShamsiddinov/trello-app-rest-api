package uz.jl.trelloapprest.dtos.project;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 3:31 PM 8/27/22 on Saturday in August
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BoardColumnUpdateDTO {
    @NotNull(message = "Id cannot be empty")
    private Long id;
    @NotBlank(message = "Title cannot be null")
    private String title;
    @NotNull(message = "CardOrder cannot be empty")
    private int cardOrder;
}
