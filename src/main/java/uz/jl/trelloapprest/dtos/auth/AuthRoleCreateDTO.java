package uz.jl.trelloapprest.dtos.auth;

import lombok.Builder;
import lombok.Data;

/**
 * @author "Zuhriddin Shamsiddinov"
 * @since 19/08/22/16:34 (Friday)
 */
@Data
@Builder
public class AuthRoleCreateDTO {
    private final String code;
    private final String name;
}
