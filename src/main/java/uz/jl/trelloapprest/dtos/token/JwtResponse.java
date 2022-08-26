package uz.jl.trelloapprest.dtos.token;

/**
 * @author "Zuhriddin Shamsiddinov"
 * @since 19/08/22/12:08 (Friday)
 */
public record JwtResponse(
        String accessToken,
        String refreshToken,
        String tokenType) {
}
