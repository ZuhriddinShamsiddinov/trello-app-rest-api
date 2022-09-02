package uz.jl.trelloapprest.dtos.project;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TaskCreateDTO {
    private String title;
    private String description;
    private Long boardColumnId;
}