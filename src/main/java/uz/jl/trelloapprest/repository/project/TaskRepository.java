package uz.jl.trelloapprest.repository.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.jl.trelloapprest.domains.project.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query(value = "select t from Task t join t.boardColumn bc join bc.board b join b.users u where bc.id=:boardColumnId and u.id=:userId and t.isDeleted=false")
    List<Task> findAllByDeletedFalse(@Param("boardColumnId") Long boardColumnId, @Param("userId") Long userId);

    @Query(value = "select t from Task t  join t.users u where t.id=:taskId and u.id=:userId and t.isDeleted=false")
    List<Task> findBoardByDeletedFalse(@Param("taskId") Long taskId, @Param("userId") Long userId);
}
