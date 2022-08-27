package uz.jl.trelloapprest.mappers;

import org.mapstruct.Mapper;
import uz.jl.trelloapprest.domains.project.BoardColumn;
import uz.jl.trelloapprest.dtos.project.BoardColumnCreateDTO;
import uz.jl.trelloapprest.dtos.response.BoardColumnDTO;

import java.util.List;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 3:12 PM 8/27/22 on Saturday in August
 */
@Mapper(componentModel = "spring")
public interface BoardColumnMapper {
    BoardColumn fromCreateDTO(BoardColumnCreateDTO createDTO);

    List<BoardColumnDTO> toDTOList(List<BoardColumn> boardColumnList);

    BoardColumnDTO toDTO(BoardColumn boardColumn);
}
