package com.pck4x.task_manager.modules.task.use_cases.handlers;

import com.pck4x.task_manager.modules.task.domain.TComments;
import com.pck4x.task_manager.modules.task.interfaces.repositories.ICommentRepository;
import com.pck4x.task_manager.modules.task.objects.dtos.commands.AddCommentDto;
import com.pck4x.task_manager.modules.task.use_cases.command.AddCommentCommand;
import com.pck4x.task_manager.shared.application.adapter.DomainEventPublisher;
import com.pck4x.task_manager.shared.result.OutputPort;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class AddCommentCommandHandler implements AddCommentCommand {
    private final ICommentRepository commentRepository;
    private final DomainEventPublisher domainEventPublisher;

    @Override
    public OutputPort<UUID> execute(UUID userId, UUID cardId, AddCommentDto input) {
        var comment = TComments.create(cardId, userId, input.content);

        var saved = commentRepository.save(comment);
        if (saved == null) {
            return OutputPort.failure(HttpStatus.BAD_REQUEST, "Unable to add comment");
        }

        domainEventPublisher.publishFrom(saved);

        return OutputPort.success(saved.getId(), HttpStatus.CREATED, "Comment added successfully");
    }
}
