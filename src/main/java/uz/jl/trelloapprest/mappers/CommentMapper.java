package uz.jl.trelloapprest.mappers;

import org.mapstruct.Mapper;
import uz.jl.trelloapprest.domains.project.Comment;
import uz.jl.trelloapprest.dtos.project.CommentCreateDTO;
import uz.jl.trelloapprest.dtos.response.CommentDTO;

import java.util.List;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 3:33 PM 9/4/22 on Sunday in September
 */
@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment fromCreateDTO(CommentCreateDTO createDTO);

    CommentDTO toDTO(Comment comment);

    List<CommentDTO> toDTOList(List<Comment> commentList);
}
