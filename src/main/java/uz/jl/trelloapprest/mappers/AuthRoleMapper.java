package uz.jl.trelloapprest.mappers;

import org.mapstruct.Mapper;
import uz.jl.trelloapprest.domains.auth.AuthRole;
import uz.jl.trelloapprest.dtos.auth.AuthRoleCreateDTO;
import uz.jl.trelloapprest.dtos.auth.AuthRoleDTO;


import java.util.List;

/**
 * @author "Zuhriddin Shamsiddinov"
 * @since 19/08/22/15:52 (Friday)
 */
@Mapper(componentModel = "spring")
public interface AuthRoleMapper {
    AuthRoleDTO toDTO(AuthRole entity);

    List<AuthRoleDTO> toDTO(List<AuthRole> entities);

    AuthRole fromCreateDTO(AuthRoleCreateDTO dto);
}
