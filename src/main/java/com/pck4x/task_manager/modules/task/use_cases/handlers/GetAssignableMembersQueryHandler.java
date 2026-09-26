package com.pck4x.task_manager.modules.task.use_cases.handlers;

import com.pck4x.task_manager.modules.task.interfaces.repositories.ICardRepository;
import com.pck4x.task_manager.modules.task.use_cases.query.GetAssignableMembersQuery;
import com.pck4x.task_manager.shared.result.OutputPort;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class GetAssignableMembersQueryHandler implements GetAssignableMembersQuery {
    private final ICardRepository cardRepository;

    @Override
    public OutputPort<java.util.List<com.pck4x.task_manager.modules.task.objects.dtos.query.response.AssignableMemberDto>> execute(UUID cardId, UUID userId) {
        var card = cardRepository.findById(cardId);
        if (card.isEmpty()) {
            return OutputPort.failure(HttpStatus.NOT_FOUND, "Card not found");
        }

        var members = cardRepository.findAssignableMembers(cardId, userId);
        return OutputPort.success(members, HttpStatus.OK, "Assignable members retrieved");
    }
}
