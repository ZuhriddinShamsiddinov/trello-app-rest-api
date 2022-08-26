package uz.jl.trelloapprest.mappers;

import org.mapstruct.Mapper;
import uz.jl.trelloapprest.domains.project.Workspace;
import uz.jl.trelloapprest.dtos.project.WorkspaceCreateDTO;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 9:18 AM 8/25/22 on Thursday in August
 */
@Mapper(componentModel = "spring")
public interface WorkspaceMapper {

    Workspace fromCreateDTO(WorkspaceCreateDTO createDTO);
}
