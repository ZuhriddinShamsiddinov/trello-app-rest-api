package uz.jl.trelloapprest.mappers;

import org.mapstruct.Mapper;
import uz.jl.trelloapprest.domains.project.Board;
import uz.jl.trelloapprest.dtos.project.BoardCreateDTO;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 3:18 PM 8/25/22 on Thursday in August
 */
@Mapper(componentModel = "spring")
public interface BoardMapper {

    Board fromCreateDTO(BoardCreateDTO createDTO);
}
