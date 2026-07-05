package com.pck4x.task_manager.modules.task.use_cases.handlers;

import com.pck4x.task_manager.modules.task.interfaces.repositories.ICardRepository;
import com.pck4x.task_manager.modules.task.objects.dtos.query.CardDetailDto;
import com.pck4x.task_manager.modules.task.use_cases.query.GetCardByIdQuery;
import com.pck4x.task_manager.shared.result.OutputPort;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class GetCardByIdQueryHandler implements GetCardByIdQuery {
    private final ICardRepository cardRepository;

    @Override
    public OutputPort<CardDetailDto> execute(UUID id, UUID userId) {
        return cardRepository.findCardDetailById(id)
                .map(dto -> OutputPort.success(dto, HttpStatus.OK, "Card retrieved successfully"))
                .orElse(OutputPort.failure(HttpStatus.NOT_FOUND, "Card not found"));
    }
}
