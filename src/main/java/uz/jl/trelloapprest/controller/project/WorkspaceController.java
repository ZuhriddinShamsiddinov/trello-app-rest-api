package uz.jl.trelloapprest.controller.project;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import uz.jl.trelloapprest.config.security.UserDetails;
import uz.jl.trelloapprest.controller.base.ApiController;
import uz.jl.trelloapprest.domains.project.Workspace;
import uz.jl.trelloapprest.dtos.project.WorkspaceCreateDTO;
import uz.jl.trelloapprest.dtos.project.WorkspaceUpdateDTO;
import uz.jl.trelloapprest.response.ApiResponse;
import uz.jl.trelloapprest.services.project.WorkspaceService;

import javax.validation.Valid;
import java.util.List;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 9:25 AM 8/25/22 on Thursday in August
 */
@RestController
public class WorkspaceController extends ApiController<WorkspaceService> {
    protected WorkspaceController(WorkspaceService service) {
        super(service);
    }

    @PostMapping(value = PATH + "/workspace/create", produces = "application/json")
    public ApiResponse<Workspace> create(@RequestBody @Valid WorkspaceCreateDTO createDTO,
                                         @AuthenticationPrincipal UserDetails userDetails) {
        return new ApiResponse<>(service.save(createDTO, userDetails));
    }

    @GetMapping(value = PATH + "/workspace", produces = "application/json")
    public ApiResponse<List<Workspace>> getAll(@AuthenticationPrincipal UserDetails userDetails) {
        return new ApiResponse<>(service.getAll(userDetails));
    }

    @GetMapping(value = PATH + "/workspace/{id}", produces = "application/json")
    public ApiResponse<Workspace> getOne(@PathVariable(value = "id") Long workspaceId) {
        return new ApiResponse<>(service.getOne(workspaceId));
    }

    @DeleteMapping(value = PATH + "/workspace/{id}", produces = "application/json")
    public ApiResponse<Boolean> delete(@PathVariable(value = "id") Long workspaceId) {
        service.delete(workspaceId);
        return new ApiResponse<>(204);
    }

    @PutMapping(value = PATH + "/workspace", produces = "application/json")
    public ApiResponse<Workspace> update(@RequestBody @Valid WorkspaceUpdateDTO updateDTO,
                                         @AuthenticationPrincipal UserDetails userDetails) {
        return new ApiResponse<>(service.update(updateDTO, userDetails));
    }
}
