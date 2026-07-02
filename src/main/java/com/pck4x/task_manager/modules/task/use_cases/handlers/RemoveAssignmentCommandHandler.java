package com.pck4x.task_manager.modules.task.use_cases.handlers;

import com.pck4x.task_manager.modules.task.interfaces.repositories.ICardAssignmentRepository;
import com.pck4x.task_manager.modules.task.use_cases.command.RemoveAssignmentCommand;
import com.pck4x.task_manager.shared.result.OutputPort;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class RemoveAssignmentCommandHandler implements RemoveAssignmentCommand {
    private final ICardAssignmentRepository cardAssignmentRepository;

    @Override
    public OutputPort<Void> execute(UUID userId, UUID cardId, UUID memberId) {
        var assignment = cardAssignmentRepository.findByCardIdAndUserId(cardId, memberId);
        if (assignment.isEmpty()) {
            return OutputPort.failure(HttpStatus.NOT_FOUND, "Assignment not found");
        }

        cardAssignmentRepository.delete(assignment.get());

        return OutputPort.success(null, HttpStatus.OK, "User unassigned successfully");
    }
}
