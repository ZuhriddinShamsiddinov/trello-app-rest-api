package uz.jl.trelloapprest.dtos.project;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 3:12 PM 8/25/22 on Thursday in August
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardCreateDTO {
    @NotBlank(message = "Name cannot be null")
    private String name;
    @NotBlank(message = "Background cannot be null")
    private String background;
    @NotNull(message = "Workspace id cannot be null")
    private Long workspaceId;
    @NotBlank(message = "Visibility cannot be null")
    private String visibility;
}
