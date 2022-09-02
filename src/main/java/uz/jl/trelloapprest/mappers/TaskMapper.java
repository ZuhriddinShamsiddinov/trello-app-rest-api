package uz.jl.trelloapprest.mappers;

import org.mapstruct.Mapper;
import uz.jl.trelloapprest.domains.project.Task;
import uz.jl.trelloapprest.dtos.project.TaskCreateDTO;
import uz.jl.trelloapprest.dtos.response.TaskDTO;

import java.util.List;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 6:35 PM 9/2/22 on Friday in September
 */
@Mapper(componentModel = "spring")
public interface TaskMapper {
    Task fromCreateDTO(TaskCreateDTO createDTO);

    TaskDTO toDTO(Task task);

    List<TaskDTO> toDTOList(List<Task> taskList);
}
