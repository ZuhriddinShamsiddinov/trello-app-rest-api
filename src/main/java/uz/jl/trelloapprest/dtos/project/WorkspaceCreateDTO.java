package uz.jl.trelloapprest.dtos.project;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 9:10 AM 8/25/22 on Thursday in August
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkspaceCreateDTO {
    @NotBlank(message = "Name cannot be null")
    private String name;
    @NotBlank(message = "Description cannot be null")
    private String description;
    @NotBlank(message = "Type cannot be null")
    private String type;
    @NotBlank(message = "Status cannot be null")
    private String status;
}
