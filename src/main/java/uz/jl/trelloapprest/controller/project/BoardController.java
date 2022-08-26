package uz.jl.trelloapprest.controller.project;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import uz.jl.trelloapprest.config.security.UserDetails;
import uz.jl.trelloapprest.controller.base.ApiController;
import uz.jl.trelloapprest.domains.project.Board;
import uz.jl.trelloapprest.domains.project.Workspace;
import uz.jl.trelloapprest.dtos.project.BoardCreateDTO;
import uz.jl.trelloapprest.dtos.project.BoardUpdateDTO;
import uz.jl.trelloapprest.projection.BoardViewProjection;
import uz.jl.trelloapprest.response.ApiResponse;
import uz.jl.trelloapprest.services.project.BoardService;

import javax.validation.Valid;
import java.util.List;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 3:03 PM 8/25/22 on Thursday in August
 */
@RestController
public class BoardController extends ApiController<BoardService> {
    protected BoardController(BoardService service) {
        super(service);
    }

    @GetMapping(value = PATH + "/board", produces = "application/json")
    public ApiResponse<List<Board>> getAll(@AuthenticationPrincipal UserDetails userDetails) {
        return new ApiResponse<>(service.getAll(userDetails));
    }

    @GetMapping(value = PATH + "/board/{id}", produces = "application/json")
    public ApiResponse<Board> getOne(@PathVariable(value = "id") Long workspaceId) {
        return new ApiResponse<>(service.getOne(workspaceId));
    }

    @PostMapping(value = PATH + "/board/create", produces = "application/json")
    public ApiResponse<Board> create(@RequestBody @Valid BoardCreateDTO createDTO,
                                     @AuthenticationPrincipal UserDetails userDetails) {
        return new ApiResponse<>(service.save(createDTO, userDetails));
    }

    @PutMapping(value = PATH + "/board", produces = "application/json")
    public ApiResponse<Board> create(@RequestBody @Valid BoardUpdateDTO updateDTO,
                                     @AuthenticationPrincipal UserDetails userDetails) {
        return new ApiResponse<>(service.update(updateDTO, userDetails));
    }

    @DeleteMapping(value = PATH + "/board/{id}", produces = "application/json")
    public ApiResponse<?> delete(@PathVariable(value = "id") Long boardId) {
        service.delete(boardId);
        return new ApiResponse<>(204);
    }
}
