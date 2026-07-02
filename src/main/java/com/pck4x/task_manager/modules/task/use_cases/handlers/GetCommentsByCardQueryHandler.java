package com.pck4x.task_manager.modules.task.use_cases.handlers;

import com.pck4x.task_manager.modules.task.interfaces.repositories.ICommentRepository;
import com.pck4x.task_manager.modules.task.objects.dtos.query.CommentDto;
import com.pck4x.task_manager.modules.task.use_cases.query.GetCommentsByCardQuery;
import com.pck4x.task_manager.shared.result.OutputPort;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class GetCommentsByCardQueryHandler implements GetCommentsByCardQuery {
    private final ICommentRepository commentRepository;

    @Override
    public OutputPort<List<CommentDto>> execute(UUID cardId, UUID userId) {
        var comments = commentRepository.findCommentDtosByCardId(cardId);

        if (comments.isEmpty()) {
            return OutputPort.failure(HttpStatus.NO_CONTENT, null);
        }

        return OutputPort.success(comments, HttpStatus.OK, "Comments retrieved successfully");
    }
}
