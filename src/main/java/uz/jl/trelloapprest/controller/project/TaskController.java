package uz.jl.trelloapprest.controller.project;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import uz.jl.trelloapprest.config.security.UserDetails;
import uz.jl.trelloapprest.controller.base.ApiController;
import uz.jl.trelloapprest.dtos.project.TaskBoardColumnDTO;
import uz.jl.trelloapprest.dtos.project.TaskCreateDTO;
import uz.jl.trelloapprest.dtos.project.TaskUpdateDTO;
import uz.jl.trelloapprest.dtos.response.TaskDTO;
import uz.jl.trelloapprest.response.ApiResponse;
import uz.jl.trelloapprest.services.project.TaskService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TaskController extends ApiController<TaskService> {
    protected TaskController(TaskService service) {
        super(service);
    }

    @GetMapping(value = PATH + "/task/all/{boardColumnId}", produces = "application/json")
    public ApiResponse<List<TaskDTO>> getAll(@PathVariable Long boardColumnId, @AuthenticationPrincipal UserDetails userDetails) {
        return new ApiResponse<>(service.getAll(boardColumnId, userDetails));
    }

    @GetMapping(value = PATH + "/task/{id}", produces = "application/json")
    public ApiResponse<TaskDTO> getOne(@PathVariable(value = "id") Long taskId, @AuthenticationPrincipal UserDetails userDetails) {
        return new ApiResponse<>(service.getOne(taskId, userDetails));
    }

    @PostMapping(value = PATH + "/task/create", produces = "application/json")
    public ApiResponse<TaskDTO> create(@RequestBody @Valid TaskCreateDTO createDTO,
                                       @AuthenticationPrincipal UserDetails userDetails) {
        return new ApiResponse<>(service.save(createDTO, userDetails));
    }

    @PostMapping(value = PATH + "/task/set/boardColumn", produces = "application/json")
    public ApiResponse<?> setBoardColumn(@RequestBody @Valid TaskBoardColumnDTO dto, @AuthenticationPrincipal UserDetails userDetails) {
        service.setBoardColumn(dto, userDetails);
        return new ApiResponse<>(204);
    }

    @PutMapping(value = PATH + "/task", produces = "application/json")
    public ApiResponse<TaskDTO> update(@RequestBody @Valid TaskUpdateDTO updateDTO,
                                       @AuthenticationPrincipal UserDetails userDetails) {
        return new ApiResponse<>(service.update(updateDTO, userDetails));
    }

    @DeleteMapping(value = PATH + "/task/{id}", produces = "application/json")
    public ApiResponse<?> delete(@PathVariable(value = "id") Long taskId, @AuthenticationPrincipal UserDetails userDetails) {
        service.delete(taskId, userDetails);
        return new ApiResponse<>(204);
    }
}
