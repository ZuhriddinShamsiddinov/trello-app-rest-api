package uz.jl.trelloapprest.controller.project;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import uz.jl.trelloapprest.config.security.UserDetails;
import uz.jl.trelloapprest.controller.base.ApiController;
import uz.jl.trelloapprest.dtos.project.BoardColumnCreateDTO;
import uz.jl.trelloapprest.dtos.project.BoardColumnMoveDTO;
import uz.jl.trelloapprest.dtos.project.BoardColumnUpdateDTO;
import uz.jl.trelloapprest.dtos.response.BoardColumnDTO;
import uz.jl.trelloapprest.response.ApiResponse;
import uz.jl.trelloapprest.services.project.BoardColumnService;

import javax.validation.Valid;
import java.util.List;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 3:13 PM 8/27/22 on Saturday in August
 */
@RestController
public class BoardColumnController extends ApiController<BoardColumnService> {
    protected BoardColumnController(BoardColumnService service) {
        super(service);
    }

    @PostMapping(value = PATH + "/board_column/create", produces = "application/json")
    public ApiResponse<BoardColumnDTO> create(@RequestBody @Valid BoardColumnCreateDTO createDTO,
                                              @AuthenticationPrincipal UserDetails userDetails) {
        return new ApiResponse<>(service.save(createDTO, userDetails));
    }

    @GetMapping(value = PATH + "/board_column/all/{boardId}", produces = "application/json")
    public ApiResponse<List<BoardColumnDTO>> getAll(@PathVariable("boardId") Long boardId,
                                                    @AuthenticationPrincipal UserDetails userDetails) {
        return new ApiResponse<>(service.getAll(boardId, userDetails));
    }

    @GetMapping(value = PATH + "/board_column/{id}", produces = "application/json")
    public ApiResponse<BoardColumnDTO> getOne(@PathVariable(value = "id") Long board_columnId,
                                              @AuthenticationPrincipal UserDetails userDetails) {
        return new ApiResponse<>(service.getOne(board_columnId, userDetails));
    }

    @DeleteMapping(value = PATH + "/board_column/{id}", produces = "application/json")
    public ApiResponse<Boolean> delete(@PathVariable(value = "id") Long board_columnId,
                                       @AuthenticationPrincipal UserDetails userDetails) {
        service.delete(board_columnId, userDetails);
        return new ApiResponse<>(204);
    }

    @PutMapping(value = PATH + "/board_column", produces = "application/json")
    public ApiResponse<BoardColumnDTO> update(@RequestBody @Valid BoardColumnUpdateDTO updateDTO,
                                              @AuthenticationPrincipal UserDetails userDetails) {
        return new ApiResponse<>(service.update(updateDTO, userDetails));
    }

    @PutMapping(value = "/board_column/change_order", produces = "application/json")
    public ApiResponse<?> changeBoardColumnOrder(@RequestBody @Valid BoardColumnMoveDTO dto) {
        service.move(dto);
        return new ApiResponse<>(209);
    }


}
