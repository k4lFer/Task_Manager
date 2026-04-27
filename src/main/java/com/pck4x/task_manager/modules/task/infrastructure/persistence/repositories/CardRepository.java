package com.pck4x.task_manager.modules.task.infrastructure.persistence.repositories;

import com.pck4x.task_manager.modules.task.domain.TCard;
import com.pck4x.task_manager.modules.task.infrastructure.mapper.CardMapper;
import com.pck4x.task_manager.modules.task.infrastructure.persistence.jpa.JpaCardRepository;
import com.pck4x.task_manager.modules.task.interfaces.repositories.ICardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CardRepository implements ICardRepository {
    private final JpaCardRepository jpa;
    private final CardMapper mapper;

    @Override
    public TCard create(TCard card) {
        var entity = mapper.toEntity(card);
        var saved = jpa.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Integer getNextPosition(UUID listId) {
        return jpa.getNextPosition(listId);
    }
}
