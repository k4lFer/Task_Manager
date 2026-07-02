package com.pck4x.task_manager.modules.task.use_cases.handlers;

import com.pck4x.task_manager.modules.task.domain.TCardAssignments;
import com.pck4x.task_manager.modules.task.interfaces.repositories.ICardAssignmentRepository;
import com.pck4x.task_manager.modules.task.interfaces.repositories.ICardRepository;
import com.pck4x.task_manager.modules.task.objects.dtos.commands.AddAssignmentDto;
import com.pck4x.task_manager.modules.task.use_cases.command.AddAssignmentCommand;
import com.pck4x.task_manager.shared.result.OutputPort;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class AddAssignmentCommandHandler implements AddAssignmentCommand {
    private final ICardRepository cardRepository;
    private final ICardAssignmentRepository cardAssignmentRepository;

    @Override
    public OutputPort<UUID> execute(UUID userId, UUID cardId, AddAssignmentDto input) {
        var card = cardRepository.findById(cardId);
        if (card.isEmpty()) {
            return OutputPort.failure(HttpStatus.NOT_FOUND, "Card not found");
        }

        if (cardAssignmentRepository.findByCardIdAndUserId(cardId, input.memberId).isPresent()) {
            return OutputPort.failure(HttpStatus.CONFLICT, "User is already assigned to this card");
        }

        var assignment = TCardAssignments.create(cardId, input.memberId);

        var saved = cardAssignmentRepository.save(assignment);
        if (saved == null) {
            return OutputPort.failure(HttpStatus.BAD_REQUEST, "Unable to assign user");
        }

        return OutputPort.success(saved.getId(), HttpStatus.CREATED, "User assigned successfully");
    }
}
