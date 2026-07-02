package com.pck4x.task_manager.modules.task.use_cases.handlers;

import com.pck4x.task_manager.modules.task.interfaces.repositories.ICommentRepository;
import com.pck4x.task_manager.modules.task.objects.dtos.commands.UpdateCommentDto;
import com.pck4x.task_manager.modules.task.use_cases.command.UpdateCommentCommand;
import com.pck4x.task_manager.shared.application.adapter.DomainEventPublisher;
import com.pck4x.task_manager.shared.result.OutputPort;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class UpdateCommentCommandHandler implements UpdateCommentCommand {
    private final ICommentRepository commentRepository;
    private final DomainEventPublisher domainEventPublisher;

    @Override
    public OutputPort<Void> execute(UUID userId, UUID commentId, UpdateCommentDto input) {
        var comment = commentRepository.findById(commentId);
        if (comment.isEmpty()) {
            return OutputPort.failure(HttpStatus.NOT_FOUND, "Comment not found");
        }

        var commentData = comment.get();

        if (!commentData.getUserId().equals(userId)) {
            return OutputPort.failure(HttpStatus.FORBIDDEN, "You can only edit your own comments");
        }

        commentData.update(input.content);

        var saved = commentRepository.save(commentData);
        if (saved == null) {
            return OutputPort.failure(HttpStatus.BAD_REQUEST, "Unable to update comment");
        }

        domainEventPublisher.publishFrom(commentData);

        return OutputPort.success(null, HttpStatus.OK, "Comment updated successfully");
    }
}
