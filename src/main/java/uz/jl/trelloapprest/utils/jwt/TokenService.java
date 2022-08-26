package uz.jl.trelloapprest.utils.jwt;

import org.springframework.security.core.userdetails.UserDetails;


/**
 * @author "Zuhriddin Shamsiddinov"
 * @since 19/08/22/12:26 (Friday)
 */
public interface TokenService {
    String generateToken(UserDetails userDetails);

    boolean isValid(String token);

    default String getSubject(String token) {
        return null;
    }
}
