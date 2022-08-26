package uz.jl.trelloapprest.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.jl.trelloapprest.domains.auth.AuthRole;

/**
 * @author "Zuhriddin Shamsiddinov"
 * @since 19/08/22/15:51 (Friday)
 */
public interface AuthRoleRepository extends JpaRepository<AuthRole, Long> {
}
