package uz.jl.trelloapprest.services.project;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.jl.trelloapprest.config.security.UserDetails;
import uz.jl.trelloapprest.domains.project.Board;
import uz.jl.trelloapprest.domains.project.BoardColumn;
import uz.jl.trelloapprest.dtos.project.BoardColumnCreateDTO;
import uz.jl.trelloapprest.dtos.project.BoardColumnUpdateDTO;
import uz.jl.trelloapprest.dtos.response.BoardColumnDTO;
import uz.jl.trelloapprest.exceptions.GenericNotFoundException;
import uz.jl.trelloapprest.exceptions.GenericRuntimeException;
import uz.jl.trelloapprest.mappers.BoardColumnMapper;
import uz.jl.trelloapprest.repository.project.BoardColumnRepository;
import uz.jl.trelloapprest.repository.project.BoardRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 3:12 PM 8/27/22 on Saturday in August
 */
@Service
@RequiredArgsConstructor
public class BoardColumnService {
    private final BoardColumnRepository boardColumnRepository;
    private final BoardColumnMapper boardColumnMapper;
    private final BoardRepository boardRepository;

    public List<BoardColumnDTO> getAll(Long boardId, UserDetails userDetails) {
        List<BoardColumn> boardList = boardColumnRepository.findAllByDeletedFalse(boardId, userDetails.authUser().getId());
        return boardColumnMapper.toDTOList(boardList);
    }

    public BoardColumnDTO save(BoardColumnCreateDTO createDTO, UserDetails userDetails) {
        BoardColumn boardColumn = boardColumnMapper.fromCreateDTO(createDTO);
        boardColumn.setCreatedBy(userDetails.authUser().getId());
        Board board = getBoardId(createDTO.getBoardId());
        boardColumn.setBoard(board);
        BoardColumn save = boardColumnRepository.save(boardColumn);
        return boardColumnMapper.toDTO(save);
    }

    private Board getBoardId(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> {
            throw new GenericNotFoundException("BoardColumn Not Found", 404);
        });
        if (board.isDeleted()) {
            throw new GenericNotFoundException("BoardColumn Not Found", 404);
        }
        return board;
    }

    private BoardColumn getBoardColumnById(Long id) {
        return boardColumnRepository.findById(id).orElseThrow(() -> {
            throw new GenericNotFoundException("BoardColumn Not Found", 404);
        });
    }

    public void delete(Long boardColumnId, UserDetails userDetails) {
        BoardColumn boardColumn = getBoardColumnById(boardColumnId);
        if (!Objects.equals(boardColumn.getCreatedBy(), userDetails.authUser().getId())) {
            throw new GenericRuntimeException("You have not permission for delete", 403);
        }
        boardColumn.setDeleted(true);
        boardColumnRepository.save(boardColumn);
    }

    public BoardColumnDTO update(BoardColumnUpdateDTO updateDTO, UserDetails userDetails) {
        BoardColumn boardColumn = getBoardColumnById(updateDTO.getId());
        boardColumn.setTitle(updateDTO.getTitle());
        boardColumn.setCardOrder(updateDTO.getCardOrder());
        boardColumn.setUpdatedBy(userDetails.authUser().getId());
        boardColumn.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        BoardColumn save = boardColumnRepository.save(boardColumn);
        return boardColumnMapper.toDTO(save);
    }

    private void isBoardDeleted(BoardColumn boardColumn) {
        if (boardColumn.isDeleted()) {
            throw new GenericNotFoundException("BoardColumn not found", 404);
        }
    }

    public BoardColumnDTO getOne(Long boardColumnId, UserDetails userDetails) {
        BoardColumn bc = boardColumnRepository
                .findBoardColumnByDeletedFalse(boardColumnId, userDetails.authUser().getId())
                .stream()
                .filter(boardColumn -> boardColumn.getId().equals(boardColumnId))
                .findFirst().orElseThrow(() -> {
                    throw new GenericNotFoundException("BoardColumn not found", 404);
                });
        isBoardDeleted(bc);
        return boardColumnMapper.toDTO(bc);
    }
}
