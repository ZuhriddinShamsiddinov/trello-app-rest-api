package uz.jl.trelloapprest.services.project;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.jl.trelloapprest.config.security.UserDetails;
import uz.jl.trelloapprest.domains.auth.AuthUser;
import uz.jl.trelloapprest.domains.project.Board;
import uz.jl.trelloapprest.domains.project.Workspace;
import uz.jl.trelloapprest.dtos.project.BoardCreateDTO;
import uz.jl.trelloapprest.dtos.project.BoardUpdateDTO;
import uz.jl.trelloapprest.dtos.response.BoardDTO;
import uz.jl.trelloapprest.dtos.response.WorkspaceDTO;
import uz.jl.trelloapprest.enums.Visibility;
import uz.jl.trelloapprest.exceptions.GenericNotFoundException;
import uz.jl.trelloapprest.exceptions.GenericRuntimeException;
import uz.jl.trelloapprest.mappers.BoardMapper;
import uz.jl.trelloapprest.repository.project.BoardRepository;
import uz.jl.trelloapprest.repository.project.WorkspaceRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 3:04 PM 8/25/22 on Thursday in August
 */
@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardMapper boardMapper;
    private final WorkspaceRepository workspaceRepository;

    public List<BoardDTO> getAll(Long workspaceId, UserDetails userDetails) {
        return boardMapper.toDTOList(boardRepository.findAllByDeletedFalse(workspaceId, userDetails.authUser().getId()));
    }

    public BoardDTO save(BoardCreateDTO createDTO, UserDetails userDetails) {
        Board board = boardMapper.fromCreateDTO(createDTO);
        board.setVisibility(Visibility.findByName(createDTO.getVisibility()));
        board.setCreatedBy(userDetails.authUser().getId());
        List<AuthUser> userList = new ArrayList<>();
        userList.add(userDetails.authUser());
        board.setUsers(userList);
        Workspace workspace = getWorkspaceById(createDTO.getWorkspaceId());
        board.setWorkspace(workspace);
        Board save = boardRepository.save(board);
        return boardMapper.toDTO(save);
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


    public void delete(Long boardId, UserDetails userDetails) {
        Board board = getBoardById(boardId);
        if (!Objects.equals(board.getCreatedBy(), userDetails.authUser().getId())) {
            throw new GenericRuntimeException("You have not permission for delete", 403);
        }
        board.setDeleted(true);
        boardRepository.save(board);
    }

    public Board update(BoardUpdateDTO updateDTO, UserDetails userDetails) {
        Board board = getBoardById(updateDTO.getId());
        board.setName(updateDTO.getName());
        board.setBackground(updateDTO.getBackground());
        board.setUpdatedBy(userDetails.authUser().getId());
        board.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        board.setWorkspace(getWorkspaceById(updateDTO.getWorkspaceId()));
        return boardRepository.save(board);
    }

    private void isBoardDeleted(Board board) {
        if (board.isDeleted()) {
            throw new GenericNotFoundException("Board not found", 404);
        }
    }

    public BoardDTO getOne(Long boardId, UserDetails userDetails) {
        Board brd = boardRepository
                .findBoardByDeletedFalse(boardId, userDetails.authUser().getId())
                .stream()
                .filter(board -> board.getId().equals(boardId))
                .findFirst().orElseThrow(() -> {
                    throw new GenericNotFoundException("Board not found", 404);
                });
        isBoardDeleted(brd);
        return boardMapper.toDTO(brd);
    }
}
