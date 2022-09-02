package uz.jl.trelloapprest.dtos.response;

import lombok.*;

import javax.persistence.Column;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 6:21 PM 9/2/22 on Friday in September
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TaskDTO {
    private Long id;
    private String title;
    private String description;
}
