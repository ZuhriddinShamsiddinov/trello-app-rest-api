package uz.jl.trelloapprest.dtos.auth;

import lombok.Builder;
import lombok.Data;

/**
 * @author "Zuhriddin Shamsiddinov"
 * @since 19/08/22/15:49 (Friday)
 */
@Data
@Builder
public class AuthRoleDTO {
    private Long id;
    private final String code;
    private final String name;
}

