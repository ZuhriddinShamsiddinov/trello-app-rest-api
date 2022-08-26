package uz.jl.trelloapprest.mappers;

import org.mapstruct.Mapper;
import uz.jl.trelloapprest.domains.auth.AuthUser;
import uz.jl.trelloapprest.dtos.UserRegisterDTO;
import uz.jl.trelloapprest.dtos.auth.AuthUserDTO;


/**
 * @author "Zuhriddin Shamsiddinov"
 * @since 19/08/22/15:28 (Friday)
 */

@Mapper(componentModel = "spring")
public interface AuthUserMapper {
    AuthUser fromRegisterDTO(UserRegisterDTO dto);

    AuthUserDTO toDTO(AuthUser domain);
}
