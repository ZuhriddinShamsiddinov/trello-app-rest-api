package uz.jl.trelloapprest.services.project;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.jl.trelloapprest.config.security.UserDetails;
import uz.jl.trelloapprest.domains.project.Comment;
import uz.jl.trelloapprest.domains.project.Task;
import uz.jl.trelloapprest.dtos.project.CommentCreateDTO;
import uz.jl.trelloapprest.dtos.project.CommentUpdateDTO;
import uz.jl.trelloapprest.dtos.response.CommentDTO;
import uz.jl.trelloapprest.exceptions.GenericNotFoundException;
import uz.jl.trelloapprest.exceptions.GenericRuntimeException;
import uz.jl.trelloapprest.mappers.CommentMapper;
import uz.jl.trelloapprest.repository.project.CommentRepository;
import uz.jl.trelloapprest.repository.project.TaskRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;
    private final CommentMapper commentMapper;

    public List<CommentDTO> getAll(Long taskId, UserDetails userDetails) {
        List<Comment> commentList = commentRepository.findAllByDeletedFalse(taskId, userDetails.authUser().getId());
        return commentMapper.toDTOList(commentList);
    }

    public CommentDTO save(CommentCreateDTO createDTO, UserDetails userDetails) {
        Comment comment = commentMapper.fromCreateDTO(createDTO);
        comment.setCreatedBy(userDetails.authUser().getId());
        comment.setTask(getTaskById(createDTO.getTaskId()));
        comment.setAuthUser(userDetails.authUser());
        Comment save = commentRepository.save(comment);
        return commentMapper.toDTO(save);
    }

    private Task getTaskById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> {
            throw new GenericNotFoundException("Task Not Found", 404);
        });
        if (task.isDeleted()) {
            throw new GenericNotFoundException("Task Not Found", 404);
        }
        return task;
    }

    private Comment getCommentById(Long id) {
        return commentRepository.findById(id).orElseThrow(() -> {
            throw new GenericNotFoundException("Comment Not Found", 404);
        });
    }


    public void delete(Long boardId, UserDetails userDetails) {
        Comment comment = getCommentById(boardId);
        if (!Objects.equals(comment.getCreatedBy(), userDetails.authUser().getId())) {
            throw new GenericRuntimeException("You have not permission for delete", 403);
        }
        comment.setDeleted(true);
        commentRepository.save(comment);
    }

    public CommentDTO update(CommentUpdateDTO updateDTO, UserDetails userDetails) {
        Comment comment = getCommentById(updateDTO.getId());
        comment.setBody(updateDTO.getBody());
        comment.setUpdatedBy(userDetails.authUser().getId());
        comment.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        comment.setTask(getTaskById(updateDTO.getTaskId()));
        comment.setAuthUser(userDetails.authUser());
        Comment save = commentRepository.save(comment);
        return commentMapper.toDTO(save);
    }

    private void isCommentDeleted(Comment comment) {
        if (comment.isDeleted()) {
            throw new GenericNotFoundException("Comment not found", 404);
        }
    }

    public CommentDTO getOne(Long commentId, UserDetails userDetails) {
        Comment comment = commentRepository
                .findBoardByDeletedFalse(commentId, userDetails.authUser().getId())
                .stream()
                .filter(board -> board.getId().equals(commentId))
                .findFirst().orElseThrow(() -> {
                    throw new GenericNotFoundException("CommentId not found", 404);
                });
        isCommentDeleted(comment);
        return commentMapper.toDTO(comment);
    }

}
