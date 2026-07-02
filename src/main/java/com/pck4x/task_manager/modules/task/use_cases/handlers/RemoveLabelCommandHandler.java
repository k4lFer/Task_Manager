package com.pck4x.task_manager.modules.task.use_cases.handlers;

import com.pck4x.task_manager.modules.task.interfaces.repositories.ICardLabelRepository;
import com.pck4x.task_manager.modules.task.use_cases.command.RemoveLabelCommand;
import com.pck4x.task_manager.shared.result.OutputPort;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class RemoveLabelCommandHandler implements RemoveLabelCommand {
    private final ICardLabelRepository cardLabelRepository;

    @Override
    public OutputPort<Void> execute(UUID userId, UUID cardId, UUID labelId) {
        var cardLabel = cardLabelRepository.findByCardIdAndLabelId(cardId, labelId);
        if (cardLabel.isEmpty()) {
            return OutputPort.failure(HttpStatus.NOT_FOUND, "Label not found on this card");
        }

        cardLabelRepository.delete(cardLabel.get());

        return OutputPort.success(null, HttpStatus.OK, "Label removed successfully");
    }
}
