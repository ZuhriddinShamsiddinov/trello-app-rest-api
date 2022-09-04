package uz.jl.trelloapprest.dtos.response;

import lombok.*;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 3:34 PM 9/4/22 on Sunday in September
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDTO {
    private Long id;
    private String body;
    private Long createdBy;
}
