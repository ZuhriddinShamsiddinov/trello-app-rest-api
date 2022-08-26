package uz.jl.trelloapprest.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.jl.trelloapprest.domains.auth.AuthUser;

import java.util.Optional;

/**
 * @author "Zuhriddin Shamsiddinov"
 * @since 19/08/22/12:09 (Friday)
 */
public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {
    Optional<AuthUser> findByUsername(String username);
}
