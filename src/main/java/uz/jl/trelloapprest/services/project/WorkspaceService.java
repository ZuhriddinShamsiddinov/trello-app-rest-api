package uz.jl.trelloapprest.services.project;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.jl.trelloapprest.config.security.UserDetails;
import uz.jl.trelloapprest.domains.auth.AuthUser;
import uz.jl.trelloapprest.domains.project.Workspace;
import uz.jl.trelloapprest.dtos.project.WorkspaceCreateDTO;
import uz.jl.trelloapprest.dtos.project.WorkspaceUpdateDTO;
import uz.jl.trelloapprest.dtos.response.WorkspaceDTO;
import uz.jl.trelloapprest.enums.WorkspaceStatus;
import uz.jl.trelloapprest.enums.WorkspaceType;
import uz.jl.trelloapprest.exceptions.GenericNotFoundException;
import uz.jl.trelloapprest.exceptions.GenericRuntimeException;
import uz.jl.trelloapprest.mappers.WorkspaceMapper;
import uz.jl.trelloapprest.repository.project.WorkspaceRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 8:56 AM 8/25/22 on Thursday in August
 */
@Service
@RequiredArgsConstructor
public class WorkspaceService {
    private final WorkspaceRepository workspaceRepository;
    private final WorkspaceMapper workspaceMapper;


    public WorkspaceDTO save(WorkspaceCreateDTO createDTO, UserDetails userDetails) {
        Workspace workspace = workspaceMapper.fromCreateDTO(createDTO);
        workspace.setWorkspaceType(WorkspaceType.findByName(createDTO.getType()));
        workspace.setWorkspaceStatus(WorkspaceStatus.findByName(createDTO.getStatus()));
        workspace.setCreatedBy(userDetails.authUser().getId());
        List<AuthUser> userList = new ArrayList<>();
        userList.add(userDetails.authUser());
        workspace.setUsers(userList);
        Workspace save = workspaceRepository.save(workspace);
        return workspaceMapper.toDTO(save);
    }

    public List<WorkspaceDTO> getAll(UserDetails userDetails) {
        List<Workspace> workspaceList = workspaceRepository.findAllByDeletedIsFalse(userDetails.authUser().getId());
        return workspaceMapper.toDTOList(workspaceList);
    }

    public WorkspaceDTO getOne(Long workspaceId, UserDetails userDetails) {
        Workspace ww = workspaceRepository
                .findAllByDeletedIsFalse(userDetails.authUser().getId())
                .stream()
                .filter(workspace -> workspace.getId().equals(workspaceId))
                .findFirst().orElseThrow(() -> {
            throw new GenericNotFoundException("Workspace not found", 404);
        });
        isWorkspaceDeleted(ww);
        return workspaceMapper.toDTO(ww);
    }

    private void isWorkspaceDeleted(Workspace workspace) {
        if (workspace.isDeleted()) {
            throw new GenericNotFoundException("Workspace not found", 404);
        }
    }

    private Workspace getById(Long workspaceId) {
        return workspaceRepository.findById(workspaceId).orElseThrow(() -> {
            throw new GenericNotFoundException("Workspace not found", 404);
        });
    }

    public void delete(Long workspaceId, UserDetails userDetails) {
        Workspace workspace = getById(workspaceId);
        if (!Objects.equals(workspace.getCreatedBy(), userDetails.authUser().getId())) {
            throw new GenericRuntimeException("You have not permission for delete", 403);
        }
        workspace.setDeleted(true);
        workspaceRepository.save(workspace);
    }

    public WorkspaceDTO update(WorkspaceUpdateDTO updateDTO, UserDetails userDetails) {
        Workspace workspace = getById(updateDTO.getId());
        isWorkspaceDeleted(workspace);
        workspace.setName(updateDTO.getName());
        workspace.setDescription(updateDTO.getDescription());
        workspace.setWorkspaceType(WorkspaceType.findByName(updateDTO.getType()));
        workspace.setWorkspaceStatus(WorkspaceStatus.findByName(updateDTO.getStatus()));
        workspace.setUpdatedBy(userDetails.authUser().getId());
        workspace.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        Workspace save = workspaceRepository.save(workspace);
        return workspaceMapper.toDTO(save);
    }
}
