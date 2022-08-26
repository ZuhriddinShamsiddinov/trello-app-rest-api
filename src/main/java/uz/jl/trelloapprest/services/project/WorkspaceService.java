package uz.jl.trelloapprest.services.project;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.jl.trelloapprest.config.security.UserDetails;
import uz.jl.trelloapprest.domains.auth.AuthUser;
import uz.jl.trelloapprest.domains.project.Workspace;
import uz.jl.trelloapprest.dtos.project.WorkspaceCreateDTO;
import uz.jl.trelloapprest.dtos.project.WorkspaceUpdateDTO;
import uz.jl.trelloapprest.enums.WorkspaceStatus;
import uz.jl.trelloapprest.enums.WorkspaceType;
import uz.jl.trelloapprest.exceptions.GenericNotFoundException;
import uz.jl.trelloapprest.mappers.WorkspaceMapper;
import uz.jl.trelloapprest.repository.project.WorkspaceRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 8:56 AM 8/25/22 on Thursday in August
 */
@Service
@RequiredArgsConstructor
public class WorkspaceService {
    private final WorkspaceRepository workspaceRepository;
    private final WorkspaceMapper workspaceMapper;


    public Workspace save(WorkspaceCreateDTO createDTO, UserDetails userDetails) {
        Workspace workspace = workspaceMapper.fromCreateDTO(createDTO);
        workspace.setWorkspaceType(WorkspaceType.findByName(createDTO.getType()));
        workspace.setWorkspaceStatus(WorkspaceStatus.findByName(createDTO.getStatus()));
        workspace.setCreatedBy(userDetails.authUser().getId());
        workspace.setCreatedAt(LocalDateTime.now());
        List<AuthUser> userList = new ArrayList<>();
        userList.add(userDetails.authUser());
        workspace.setUsers(userList);
        return workspaceRepository.save(workspace);
    }

    public List<Workspace> getAll(UserDetails userDetails) {
        return workspaceRepository.findAllByDeletedIsFalse(userDetails.authUser().getId());
    }

    public Workspace getOne(Long workspaceId) {
        Workspace workspace = getById(workspaceId);
        if (workspace.isDeleted()) {
            throw new GenericNotFoundException("Workspace not found", 404);
        }
        return workspace;
    }

    private Workspace getById(Long workspaceId) {
        return workspaceRepository.findById(workspaceId).orElseThrow(() -> {
            throw new GenericNotFoundException("Workspace not found", 404);
        });
    }

    public void delete(Long workspaceId) {
        Workspace workspace = getById(workspaceId);
        workspace.setDeleted(true);
        workspaceRepository.save(workspace);
    }

    public Workspace update(WorkspaceUpdateDTO updateDTO, UserDetails userDetails) {
        Workspace workspace = getById(updateDTO.getId());
        workspace.setName(updateDTO.getName());
        workspace.setDescription(updateDTO.getDescription());
        workspace.setWorkspaceType(WorkspaceType.findByName(updateDTO.getType()));
        workspace.setWorkspaceStatus(WorkspaceStatus.findByName(updateDTO.getStatus()));
        workspace.setUpdatedBy(userDetails.authUser().getId());
        workspace.setUpdatedAt(LocalDateTime.now());
        return workspaceRepository.save(workspace);
    }
}
