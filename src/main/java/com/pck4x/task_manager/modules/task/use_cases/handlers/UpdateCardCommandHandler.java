package com.pck4x.task_manager.modules.task.use_cases.handlers;

import com.pck4x.task_manager.modules.task.interfaces.repositories.ICardRepository;
import com.pck4x.task_manager.modules.task.objects.dtos.commands.UpdateCardDto;
import com.pck4x.task_manager.modules.task.use_cases.command.UpdateCardCommand;
import com.pck4x.task_manager.shared.application.adapter.DomainEventPublisher;
import com.pck4x.task_manager.shared.result.OutputPort;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class UpdateCardCommandHandler implements UpdateCardCommand {
    private final ICardRepository cardRepository;
    private final DomainEventPublisher domainEventPublisher;

    @Override
    public OutputPort<Void> execute(UUID userId, UUID cardId, UpdateCardDto input) {
        var card = cardRepository.findById(cardId);
        if (card.isEmpty()) {
            return OutputPort.failure(HttpStatus.NOT_FOUND, "Card not found");
        }

        var cardData = card.get();
        cardData.update(input.title, input.description, input.startDate, input.dueDate, input.progress);

        var saved = cardRepository.save(cardData);
        if (saved == null) {
            return OutputPort.failure(HttpStatus.BAD_REQUEST, "Unable to update card");
        }

        domainEventPublisher.publishFrom(cardData);

        return OutputPort.success(null, HttpStatus.OK, "Card updated successfully");
    }
}
