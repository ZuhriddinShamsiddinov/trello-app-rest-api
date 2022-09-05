package uz.jl.trelloapprest.events;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.jl.trelloapprest.domains.project.Board;
import uz.jl.trelloapprest.domains.project.BoardColumn;
import uz.jl.trelloapprest.dtos.project.BoardColumnMoveDTO;
import uz.jl.trelloapprest.exceptions.GenericNotFoundException;
import uz.jl.trelloapprest.repository.project.BoardColumnRepository;
import uz.jl.trelloapprest.repository.project.BoardRepository;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 9:30 AM 9/5/22 on Monday in September
 */
@RequiredArgsConstructor
@Component
public class EventListener {
    private final BoardColumnRepository boardColumnRepository;
    private final BoardRepository boardRepository;

    @org.springframework.context.event.EventListener
    public void moveBoardColumn(BoardColumnMoveDTO dto) {
        BoardColumn boardColumn = boardColumnRepository.findById(dto.getBoardColumnId()).orElseThrow(() -> {
            throw new GenericNotFoundException("BoardColumn Not found", 404);
        });

        Board board = boardRepository.findById(dto.getBoardId()).orElseThrow(() -> {
            throw new GenericNotFoundException("Board Not found", 404);
        });


        if (boardColumn.getCardOrder() > dto.getMovingOrder()) {
            boardColumnRepository.moveBack(boardColumn.getCardOrder(), dto.getMovingOrder(), board.getId());
        } else {
            boardColumnRepository.moveForward(boardColumn.getCardOrder(), dto.getMovingOrder(), board.getId());
        }

        boardColumn.setCardOrder(dto.getMovingOrder());
        boardColumnRepository.save(boardColumn);
    }
}
