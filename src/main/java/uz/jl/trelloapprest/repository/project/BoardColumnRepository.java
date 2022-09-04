package uz.jl.trelloapprest.repository.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.jl.trelloapprest.domains.project.BoardColumn;

import java.util.List;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 3:10 PM 8/27/22 on Saturday in August
 */
public interface BoardColumnRepository extends JpaRepository<BoardColumn, Long> {

    @Query(value = "select bc from BoardColumn bc join bc.board b join b.users u where b.id=:boardId and u.id=:userId and bc.isDeleted=false")
    List<BoardColumn> findAllByDeletedFalse(@Param("boardId") Long boardId, @Param("userId") Long userId);

    @Query(value = "select bc from BoardColumn bc join bc.board b join b.users u where u.id=:userId and bc.id=:boardColumnId")
    List<BoardColumn> findBoardColumnByDeletedFalse(@Param("boardColumnId") Long boardColumnId, @Param("userId") Long userId);
}
