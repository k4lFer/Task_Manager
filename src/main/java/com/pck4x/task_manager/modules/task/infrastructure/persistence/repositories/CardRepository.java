package com.pck4x.task_manager.modules.task.infrastructure.persistence.repositories;

import com.pck4x.task_manager.modules.task.domain.TCard;
import com.pck4x.task_manager.modules.task.infrastructure.mapper.CardMapper;
import com.pck4x.task_manager.modules.task.infrastructure.persistence.jpa.JpaCardRepository;
import com.pck4x.task_manager.modules.task.interfaces.repositories.ICardRepository;
import com.pck4x.task_manager.modules.task.objects.dtos.query.CardSummaryDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CardRepository implements ICardRepository {
    private final JpaCardRepository jpa;
    private final CardMapper mapper;

    @Override
    @Transactional
    public TCard save(TCard card) {
        var entity = mapper.toEntity(card);
        var saved = jpa.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<TCard> findById(UUID id) {
        return jpa.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<TCard> findAllByListId(UUID listId) {
        return jpa.findByListsIdOrderByPositionAsc(listId).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    @Transactional
    public void delete(TCard card) {
        jpa.deleteById(card.getId());
    }

    @Override
    public Integer getNextPosition(UUID listId) {
        return jpa.getNextPosition(listId);
    }

    @Override
    public List<CardSummaryDto> findCardSummariesByListId(UUID listId) {
        return jpa.findCardSummariesByListId(listId);
    }

    @Override
    @Transactional
    public void deleteByListsIdIn(List<UUID> listIds) {
        jpa.deleteByListsIdIn(listIds);
    }
}
