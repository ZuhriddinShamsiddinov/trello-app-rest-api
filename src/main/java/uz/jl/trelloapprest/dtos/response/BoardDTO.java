package uz.jl.trelloapprest.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 10:33 AM 8/27/22 on Saturday in August
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BoardDTO {
    private Long id;
    private String name;
    private String background;
    private String visibility;
}
