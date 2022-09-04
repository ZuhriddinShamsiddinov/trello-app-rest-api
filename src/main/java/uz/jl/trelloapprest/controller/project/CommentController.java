package uz.jl.trelloapprest.controller.project;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import uz.jl.trelloapprest.config.security.UserDetails;
import uz.jl.trelloapprest.controller.base.ApiController;
import uz.jl.trelloapprest.dtos.project.CommentCreateDTO;
import uz.jl.trelloapprest.dtos.project.CommentUpdateDTO;
import uz.jl.trelloapprest.dtos.response.CommentDTO;
import uz.jl.trelloapprest.response.ApiResponse;
import uz.jl.trelloapprest.services.project.CommentService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CommentController extends ApiController<CommentService> {
    protected CommentController(CommentService service) {
        super(service);
    }

    @GetMapping(value = PATH + "/comment/all/{taskId}", produces = "application/json")
    public ApiResponse<List<CommentDTO>> getAll(@PathVariable Long taskId, @AuthenticationPrincipal UserDetails userDetails) {
        return new ApiResponse<>(service.getAll(taskId, userDetails));
    }

    @GetMapping(value = PATH + "/comment/{id}", produces = "application/json")
    public ApiResponse<CommentDTO> getOne(@PathVariable(value = "id") Long commentId, @AuthenticationPrincipal UserDetails userDetails) {
        return new ApiResponse<>(service.getOne(commentId, userDetails));
    }

    @PostMapping(value = PATH + "/comment/create", produces = "application/json")
    public ApiResponse<CommentDTO> create(@RequestBody @Valid CommentCreateDTO createDTO,
                                       @AuthenticationPrincipal UserDetails userDetails) {
        return new ApiResponse<>(service.save(createDTO, userDetails));
    }


    @PutMapping(value = PATH + "/comment", produces = "application/json")
    public ApiResponse<CommentDTO> update(@RequestBody @Valid CommentUpdateDTO updateDTO,
                                       @AuthenticationPrincipal UserDetails userDetails) {
        return new ApiResponse<>(service.update(updateDTO, userDetails));
    }

    @DeleteMapping(value = PATH + "/comment/{id}", produces = "application/json")
    public ApiResponse<?> delete(@PathVariable(value = "id") Long commentId, @AuthenticationPrincipal UserDetails userDetails) {
        service.delete(commentId, userDetails);
        return new ApiResponse<>(204);
    }
}
