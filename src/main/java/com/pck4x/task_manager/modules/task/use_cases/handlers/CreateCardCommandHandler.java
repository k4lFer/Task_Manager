package com.pck4x.task_manager.modules.task.use_cases.handlers;

import com.pck4x.task_manager.modules.task.domain.TCard;
import com.pck4x.task_manager.modules.task.interfaces.repositories.ICardRepository;
import com.pck4x.task_manager.modules.task.objects.dtos.commands.CreateCardDto;
import com.pck4x.task_manager.modules.task.use_cases.command.CreateCardCommand;
import com.pck4x.task_manager.modules.task.use_cases.validators.CreateCardCommandValidator;
import com.pck4x.task_manager.shared.application.adapter.DomainEventPublisher;
import com.pck4x.task_manager.shared.result.OutputPort;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class CreateCardCommandHandler implements CreateCardCommand {
    private final ICardRepository repository;
    private final CreateCardCommandValidator validator;
    private final DomainEventPublisher domainEventPublisher;

    @Override
    public OutputPort<UUID> execute(UUID userId, CreateCardDto input) {
        if (!validator.Validate(input))
            return OutputPort.failures(validator.getHttpStatusCode(), validator.getMessage());

        var nextPosition = repository.getNextPosition(input.listId);

        var card = TCard.create(
                input.listId,
                input.title,
                input.description,
                nextPosition,
                input.startDate,
                input.dueDate
        );

        var saved = repository.save(card);
        if (saved == null)
            return OutputPort.failure(HttpStatus.BAD_REQUEST, "Unable to create card");

        domainEventPublisher.publishFrom(card);

        return OutputPort.success(saved.getId(), HttpStatus.CREATED, "Card created successfully");
    }
}
