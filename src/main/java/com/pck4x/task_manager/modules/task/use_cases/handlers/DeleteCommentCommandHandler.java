package com.pck4x.task_manager.modules.task.use_cases.handlers;

import com.pck4x.task_manager.modules.task.interfaces.repositories.ICommentRepository;
import com.pck4x.task_manager.modules.task.use_cases.command.DeleteCommentCommand;
import com.pck4x.task_manager.shared.application.adapter.DomainEventPublisher;
import com.pck4x.task_manager.shared.result.OutputPort;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class DeleteCommentCommandHandler implements DeleteCommentCommand {
    private final ICommentRepository commentRepository;
    private final DomainEventPublisher domainEventPublisher;

    @Override
    public OutputPort<Void> execute(UUID userId, UUID commentId) {
        var comment = commentRepository.findById(commentId);
        if (comment.isEmpty()) {
            return OutputPort.failure(HttpStatus.NOT_FOUND, "Comment not found");
        }

        var commentData = comment.get();

        if (!commentData.getUserId().equals(userId)) {
            return OutputPort.failure(HttpStatus.FORBIDDEN, "You can only delete your own comments");
        }

        commentData.markAsDeleted();
        commentRepository.delete(commentData);
        domainEventPublisher.publishFrom(commentData);

        return OutputPort.success(null, HttpStatus.OK, "Comment deleted successfully");
    }
}
