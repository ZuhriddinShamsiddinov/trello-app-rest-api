package uz.jl.trelloapprest.dtos.project;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 12:10 PM 8/25/22 on Thursday in August
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkspaceUpdateDTO {
    @NotNull(message = "Id cannot be null")
    private Long id;
    @NotBlank(message = "Name cannot be null")
    private String name;
    @NotBlank(message = "Description cannot be null")
    private String description;
    @NotBlank(message = "Type cannot be null")
    private String type;
    @NotBlank(message = "Status cannot be null")
    private String status;
}
