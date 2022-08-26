package uz.jl.trelloapprest.repository.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.jl.trelloapprest.domains.project.Board;
import uz.jl.trelloapprest.projection.BoardViewProjection;

import java.util.List;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 3:05 PM 8/25/22 on Thursday in August
 */
public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query(value = "select b from Board b where b.id in (select t.id from Board t join t.users u where u.id = :userId)")
    List<Board> findAllByDeletedFalse(@Param("userId") Long id);

}
