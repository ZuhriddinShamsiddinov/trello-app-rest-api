package uz.jl.trelloapprest.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.jl.trelloapprest.domains.project.ActivationCode;

import java.util.Optional;

/**
 * @author "Zuhriddin Shamsiddinov"
 * @since 22/08/22/11:23 (Monday)
 */
public interface ActivationCodeRepository extends JpaRepository<ActivationCode, Long> {
    Optional<ActivationCode> findByActivationLink(String link);
}
