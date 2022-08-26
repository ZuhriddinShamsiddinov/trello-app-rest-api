package uz.jl.trelloapprest.services.auth;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import uz.jl.trelloapprest.domains.auth.AuthRole;
import uz.jl.trelloapprest.dtos.auth.AuthRoleCreateDTO;
import uz.jl.trelloapprest.dtos.auth.AuthRoleDTO;
import uz.jl.trelloapprest.exceptions.GenericNotFoundException;
import uz.jl.trelloapprest.mappers.AuthRoleMapper;
import uz.jl.trelloapprest.repository.auth.AuthRoleRepository;

import java.util.List;
import java.util.function.Supplier;

/**
 * @author "Zuhriddin Shamsiddinov"
 * @since 19/08/22/15:48 (Friday)
 */

@Service
@RequiredArgsConstructor
public class AuthRoleService {

    private final AuthRoleRepository authRoleRepository;
    private final AuthRoleMapper authRoleMapper;


    @PreAuthorize("hasAuthority(T(uz.jl.springbootfeatures.enums.Permissions).ROLE_READ)")
    public List<AuthRoleDTO> getAll() {
        List<AuthRole> authRoles = authRoleRepository.findAll();
        return authRoleMapper.toDTO(authRoles);
    }

    @PreAuthorize("hasAuthority(T(uz.jl.springbootfeatures.enums.Permissions).ROLE_READ)")
    public AuthRoleDTO get(@NonNull Long id) {
        // TODO: 19/08/22 standardize status codes
        Supplier<GenericNotFoundException> notFoundException = () -> new GenericNotFoundException("Role not found", 404);
        AuthRole authRole = authRoleRepository.findById(id).orElseThrow(notFoundException);
        return authRoleMapper.toDTO(authRole);
    }

    @PreAuthorize("hasAuthority(T(uz.jl.springbootfeatures.enums.Permissions).ROLE_CREATE)")
    public void create(AuthRoleCreateDTO dto) {
        AuthRole authRole = authRoleMapper.fromCreateDTO(dto);
        authRoleRepository.save(authRole);
    }

    @PreAuthorize("hasAuthority(T(uz.jl.springbootfeatures.enums.Permissions).ROLE_DELETE)")
    public void delete(@NonNull Long id) {
        authRoleRepository.deleteById(id);
    }
}
