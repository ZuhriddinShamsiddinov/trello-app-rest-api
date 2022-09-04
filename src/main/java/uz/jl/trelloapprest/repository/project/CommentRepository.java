package uz.jl.trelloapprest.repository.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.jl.trelloapprest.domains.project.Comment;

import java.util.List;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 3:37 PM 9/4/22 on Sunday in September
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(value = "select c from Comment c join c.task t join t.users u where u.id=:userId and t.id=:taskId and c.isDeleted=false")
    List<Comment> findAllByDeletedFalse(@Param("taskId") Long taskId, @Param("userId") Long userId);

    @Query(value = "select c from Comment c  join c.task t join t.users u  where c.id=:commentId and u.id=:userId and c.isDeleted=false")
    List<Comment> findBoardByDeletedFalse(@Param("commentId") Long commentId, @Param("userId") Long userId);
}
