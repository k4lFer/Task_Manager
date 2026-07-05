package com.pck4x.task_manager.modules.task.interfaces.repositories;

import com.pck4x.task_manager.modules.task.domain.TCard;
import com.pck4x.task_manager.modules.task.objects.dtos.query.CardDetailDto;
import com.pck4x.task_manager.modules.task.objects.dtos.query.CardSummaryDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ICardRepository {
    TCard save(TCard card);
    Optional<TCard> findById(UUID id);
    List<TCard> findAllByListId(UUID listId);
    void delete(TCard card);
    Integer getNextPosition(UUID listId);
    List<CardSummaryDto> findCardSummariesByListId(UUID listId);
    void deleteByListsIdIn(List<UUID> listIds);
    Optional<CardDetailDto> findCardDetailById(UUID id);
}
