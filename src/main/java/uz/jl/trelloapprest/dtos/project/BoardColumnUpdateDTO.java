package uz.jl.trelloapprest.dtos.project;

import lombok.*;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 3:31 PM 8/27/22 on Saturday in August
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BoardColumnUpdateDTO {
    private Long id;
    private String title;
    private int cardOrder;
}
