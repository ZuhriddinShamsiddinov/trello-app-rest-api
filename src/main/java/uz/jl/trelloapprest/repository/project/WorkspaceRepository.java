package uz.jl.trelloapprest.repository.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.jl.trelloapprest.domains.project.Workspace;

import java.util.List;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 8:54 AM 8/25/22 on Thursday in August
 */
public interface WorkspaceRepository extends JpaRepository<Workspace, Long> {
    @Query(value = "select b from Workspace b where b.id in (select t.id from Workspace t join t.users u where u.id = :userId ) and b.isDeleted=false")
    List<Workspace> findAllByDeletedIsFalse(@Param("userId") Long userId);
}
