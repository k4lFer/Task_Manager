package com.pck4x.task_manager.modules.task.use_cases.handlers;

import com.pck4x.task_manager.modules.task.interfaces.repositories.ICardAssignmentRepository;
import com.pck4x.task_manager.modules.task.interfaces.repositories.ICardLabelRepository;
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
    private final ICardAssignmentRepository cardAssignmentRepository;
    private final ICardLabelRepository cardLabelRepository;

    @Override
    public OutputPort<CardDetailDto> execute(UUID id, UUID userId) {
        var card = cardRepository.findById(id);
        if (card.isEmpty()) {
            return OutputPort.failure(HttpStatus.NOT_FOUND, "Card not found");
        }

        var cardData = card.get();

        var members = cardAssignmentRepository.findByCardId(cardData.getId()).stream()
                .map(a -> (Object) a.getUserId())
                .toList();

        var labels = cardLabelRepository.findByCardId(cardData.getId()).stream()
                .map(l -> (Object) l.getLabelsId())
                .toList();

        var commentsCount = 0; // could be fetched from the entity if needed

        var dto = new CardDetailDto(
                cardData.getId(),
                cardData.getListsId(),
                cardData.getTitle(),
                cardData.getDescription(),
                cardData.getPosition(),
                cardData.getStartDate(),
                cardData.getDueDate(),
                cardData.getCompletedAt(),
                cardData.getProgress(),
                members,
                labels,
                commentsCount,
                0,
                cardData.getCreatedAt(),
                cardData.getUpdatedAt()
        );

        return OutputPort.success(dto, HttpStatus.OK, "Card retrieved successfully");
    }
}
