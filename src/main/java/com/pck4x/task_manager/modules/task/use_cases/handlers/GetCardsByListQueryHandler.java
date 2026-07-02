package com.pck4x.task_manager.modules.task.use_cases.handlers;

import com.pck4x.task_manager.modules.task.interfaces.repositories.ICardRepository;
import com.pck4x.task_manager.modules.task.objects.dtos.query.CardSummaryDto;
import com.pck4x.task_manager.modules.task.use_cases.query.GetCardsByListQuery;
import com.pck4x.task_manager.shared.result.OutputPort;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class GetCardsByListQueryHandler implements GetCardsByListQuery {
    private final ICardRepository cardRepository;

    @Override
    public OutputPort<List<CardSummaryDto>> execute(UUID listId, UUID userId) {
        var cards = cardRepository.findCardSummariesByListId(listId);

        if (cards.isEmpty()) {
            return OutputPort.failure(HttpStatus.NO_CONTENT, null);
        }

        return OutputPort.success(cards, HttpStatus.OK, "Cards retrieved successfully");
    }
}
