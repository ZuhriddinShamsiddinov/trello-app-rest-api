package uz.jl.trelloapprest.dtos.auth;

import lombok.*;

import java.time.LocalDateTime;

/**
 * @author "Zuhriddin Shamsiddinov"
 * @since 22/08/22/10:48 (Monday)
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthUserDTO {
    private Long id;
    private String username;
    private int loginTryCount;
    private String email;
    private String status;
    private LocalDateTime lastLoginTime;
}
