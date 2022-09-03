package uz.jl.trelloapprest.services.project;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.jl.trelloapprest.config.security.UserDetails;
import uz.jl.trelloapprest.domains.auth.AuthUser;
import uz.jl.trelloapprest.domains.project.BoardColumn;
import uz.jl.trelloapprest.domains.project.Task;
import uz.jl.trelloapprest.dtos.project.TaskBoardColumnDTO;
import uz.jl.trelloapprest.dtos.project.TaskCreateDTO;
import uz.jl.trelloapprest.dtos.project.TaskUpdateDTO;
import uz.jl.trelloapprest.dtos.response.TaskDTO;
import uz.jl.trelloapprest.exceptions.GenericNotFoundException;
import uz.jl.trelloapprest.exceptions.GenericRuntimeException;
import uz.jl.trelloapprest.mappers.TaskMapper;
import uz.jl.trelloapprest.repository.project.BoardColumnRepository;
import uz.jl.trelloapprest.repository.project.TaskRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final BoardColumnRepository boardColumnRepository;
    private final BoardColumnService boardColumnService;
    private final TaskMapper taskMapper;

    public List<TaskDTO> getAll(Long boardColumnId, UserDetails userDetails) {
        List<Task> taskList = taskRepository.findAllByDeletedFalse(boardColumnId, userDetails.authUser().getId());
        return taskMapper.toDTOList(taskList);
    }

    public TaskDTO save(TaskCreateDTO createDTO, UserDetails userDetails) {
        Task task = taskMapper.fromCreateDTO(createDTO);
        task.setCreatedBy(userDetails.authUser().getId());
        List<AuthUser> userList = new ArrayList<>();
        userList.add(userDetails.authUser());
        task.setUsers(userList);
        BoardColumn boardColumn = getBoardColumnById(createDTO.getBoardColumnId());
        task.setBoardColumn(boardColumn);
        Task save = taskRepository.save(task);
        return taskMapper.toDTO(save);
    }

    private BoardColumn getBoardColumnById(Long id) {
        BoardColumn boardColumn = boardColumnRepository.findById(id).orElseThrow(() -> {
            throw new GenericNotFoundException("BoardColumn Not Found", 404);
        });
        if (boardColumn.isDeleted()) {
            throw new GenericNotFoundException("BoardColumn Not Found", 404);
        }
        return boardColumn;
    }

    private Task getBoardById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> {
            throw new GenericNotFoundException("Task Not Found", 404);
        });
    }


    public void delete(Long boardId, UserDetails userDetails) {
        Task task = getBoardById(boardId);
        if (!Objects.equals(task.getCreatedBy(), userDetails.authUser().getId())) {
            throw new GenericRuntimeException("You have not permission for delete", 403);
        }
        task.setDeleted(true);
        taskRepository.save(task);
    }

    public TaskDTO update(TaskUpdateDTO updateDTO, UserDetails userDetails) {
        Task task = getBoardById(updateDTO.getId());
        task.setTitle(updateDTO.getTitle());
        task.setDescription(updateDTO.getDescription());
        task.setUpdatedBy(userDetails.authUser().getId());
        task.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        task.setBoardColumn(getBoardColumnById(updateDTO.getBoardColumnId()));
        Task save = taskRepository.save(task);
        return taskMapper.toDTO(save);
    }

    private void isTaskDeleted(Task task) {
        if (task.isDeleted()) {
            throw new GenericNotFoundException("Task not found", 404);
        }
    }

    public TaskDTO getOne(Long taskId, UserDetails userDetails) {
        Task task = taskRepository
                .findBoardByDeletedFalse(taskId, userDetails.authUser().getId())
                .stream()
                .filter(board -> board.getId().equals(taskId))
                .findFirst().orElseThrow(() -> {
                    throw new GenericNotFoundException("Task not found", 404);
                });
        isTaskDeleted(task);
        return taskMapper.toDTO(task);
    }

    public void setBoardColumn(TaskBoardColumnDTO dto, UserDetails userDetails) {
        Task task = taskRepository
                .findById(getOne(dto.getTaskId(), userDetails).getId()).get();
        BoardColumn boardColumn = boardColumnRepository
                .findById(boardColumnService.getOne(dto.getBoardColumnId(), userDetails).getId()).get();
        task.setBoardColumn(boardColumn);
        taskRepository.save(task);
    }
}
