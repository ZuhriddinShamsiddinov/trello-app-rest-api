package uz.jl.trelloapprest.dtos.project;

import lombok.*;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 6:19 PM 9/2/22 on Friday in September
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TaskUpdateDTO {
    private Long id;
    private String title;
    private String description;
    private Long boardColumnId;
}
