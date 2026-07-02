package com.pck4x.task_manager.modules.task.use_cases.handlers;

import com.pck4x.task_manager.modules.task.interfaces.repositories.ICardRepository;
import com.pck4x.task_manager.modules.task.use_cases.command.DeleteCardCommand;
import com.pck4x.task_manager.shared.application.adapter.DomainEventPublisher;
import com.pck4x.task_manager.shared.result.OutputPort;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class DeleteCardCommandHandler implements DeleteCardCommand {
    private final ICardRepository cardRepository;
    private final DomainEventPublisher domainEventPublisher;

    @Override
    public OutputPort<Void> execute(UUID userId, UUID cardId) {
        var card = cardRepository.findById(cardId);
        if (card.isEmpty()) {
            return OutputPort.failure(HttpStatus.NOT_FOUND, "Card not found");
        }

        var cardData = card.get();
        cardData.markAsDeleted();

        cardRepository.delete(cardData);
        domainEventPublisher.publishFrom(cardData);

        return OutputPort.success(null, HttpStatus.OK, "Card deleted successfully");
    }
}
