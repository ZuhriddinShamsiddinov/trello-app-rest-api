package uz.jl.trelloapprest.repository.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
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


    @Modifying
    @Transactional
    @Query("update BoardColumn c set c.cardOrder = c.cardOrder + 1 where c.cardOrder >= :movingColumn and c.cardOrder < :orderColumn and c.board.id=:boardId and c.isDeleted=false")
    void moveBack(@Param("orderColumn") int orderColumn, @Param("movingColumn") int movingColumn, @Param("boardId") Long boardId);

    @Modifying
    @Transactional
    @Query("update BoardColumn c set c.cardOrder = c.cardOrder - 1 where c.cardOrder <= :movingColumn and c.cardOrder > :orderColumn and c.board.id=:boardId and c.isDeleted=false")
    void moveForward(@Param("orderColumn") int orderColumn, @Param("movingColumn") int movingColumn, @Param("boardId") Long boardId);
}
