package com.pck4x.task_manager.modules.task.use_cases.handlers;

import com.pck4x.task_manager.modules.task.domain.TCardLabels;
import com.pck4x.task_manager.modules.task.interfaces.repositories.ICardLabelRepository;
import com.pck4x.task_manager.modules.task.interfaces.repositories.ICardRepository;
import com.pck4x.task_manager.modules.task.objects.dtos.commands.AddLabelDto;
import com.pck4x.task_manager.modules.task.use_cases.command.AddLabelCommand;
import com.pck4x.task_manager.shared.result.OutputPort;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class AddLabelCommandHandler implements AddLabelCommand {
    private final ICardRepository cardRepository;
    private final ICardLabelRepository cardLabelRepository;

    @Override
    public OutputPort<UUID> execute(UUID userId, UUID cardId, AddLabelDto input) {
        var card = cardRepository.findById(cardId);
        if (card.isEmpty()) {
            return OutputPort.failure(HttpStatus.NOT_FOUND, "Card not found");
        }

        if (cardLabelRepository.findByCardIdAndLabelId(cardId, input.labelId).isPresent()) {
            return OutputPort.failure(HttpStatus.CONFLICT, "Label already added to this card");
        }

        var cardLabel = TCardLabels.create(cardId, input.labelId);

        var saved = cardLabelRepository.save(cardLabel);
        if (saved == null) {
            return OutputPort.failure(HttpStatus.BAD_REQUEST, "Unable to add label");
        }

        return OutputPort.success(saved.getId(), HttpStatus.CREATED, "Label added successfully");
    }
}
