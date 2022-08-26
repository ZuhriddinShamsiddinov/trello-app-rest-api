package uz.jl.trelloapprest.services.project;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.jl.trelloapprest.config.security.UserDetails;
import uz.jl.trelloapprest.domains.auth.AuthUser;
import uz.jl.trelloapprest.domains.project.Board;
import uz.jl.trelloapprest.domains.project.Workspace;
import uz.jl.trelloapprest.dtos.project.BoardCreateDTO;
import uz.jl.trelloapprest.dtos.project.BoardUpdateDTO;
import uz.jl.trelloapprest.enums.Visibility;
import uz.jl.trelloapprest.enums.WorkspaceStatus;
import uz.jl.trelloapprest.enums.WorkspaceType;
import uz.jl.trelloapprest.exceptions.GenericNotFoundException;
import uz.jl.trelloapprest.mappers.BoardMapper;
import uz.jl.trelloapprest.projection.BoardViewProjection;
import uz.jl.trelloapprest.repository.project.BoardRepository;
import uz.jl.trelloapprest.repository.project.WorkspaceRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 3:04 PM 8/25/22 on Thursday in August
 */
@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardMapper boardMapper;
    private final WorkspaceRepository workspaceRepository;

    public List<Board> getAll(UserDetails userDetails) {
        return boardRepository.findAllByDeletedFalse(userDetails.authUser().getId());
    }

    public Board save(BoardCreateDTO createDTO, UserDetails userDetails) {
        Board board = boardMapper.fromCreateDTO(createDTO);
        board.setVisibility(Visibility.findByName(createDTO.getVisibility()));
        board.setCreatedBy(userDetails.authUser().getId());
        board.setCreatedAt(LocalDateTime.now());
        List<AuthUser> userList = new ArrayList<>();
        userList.add(userDetails.authUser());
        board.setUsers(userList);
        Workspace workspace = getWorkspaceById(createDTO.getWorkspaceId());
        board.setWorkspace(workspace);
        return boardRepository.save(board);
    }

    private Workspace getWorkspaceById(Long id) {
        Workspace workspace = workspaceRepository.findById(id).orElseThrow(() -> {
            throw new GenericNotFoundException("Workspace Not Found", 404);
        });
        if (workspace.isDeleted()) {
            throw new GenericNotFoundException("Workspace Not Found", 404);
        }
        return workspace;
    }

    private Board getBoardById(Long id) {
        return boardRepository.findById(id).orElseThrow(() -> {
            throw new GenericNotFoundException("Board Not Found", 404);
        });
    }

    public void delete(Long boardId) {
        Board board = getBoardById(boardId);
        board.setDeleted(true);
        boardRepository.save(board);
    }

    public Board update(BoardUpdateDTO updateDTO, UserDetails userDetails) {
        Board board = getBoardById(updateDTO.getId());
        board.setName(updateDTO.getName());
        board.setBackground(updateDTO.getBackground());
        board.setUpdatedBy(userDetails.authUser().getId());
        board.setUpdatedAt(LocalDateTime.now());
        board.setWorkspace(getWorkspaceById(updateDTO.getWorkspaceId()));
        return boardRepository.save(board);
    }

    public Board getOne(Long boardId) {
        Board board = getBoardById(boardId);
        if (board.isDeleted()) {
            throw new GenericNotFoundException("Board not found", 404);
        }
        return board;
    }
}
