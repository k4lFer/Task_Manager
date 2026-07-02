package com.pck4x.task_manager.modules.task.infrastructure.persistence.repositories;

import com.pck4x.task_manager.modules.task.domain.TCardLabels;
import com.pck4x.task_manager.modules.task.infrastructure.mapper.CardLabelMapper;
import com.pck4x.task_manager.modules.task.infrastructure.persistence.jpa.JpaCardLabelsRepository;
import com.pck4x.task_manager.modules.task.interfaces.repositories.ICardLabelRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CardLabelRepository implements ICardLabelRepository {
    private final JpaCardLabelsRepository jpa;
    private final CardLabelMapper mapper;

    @Override
    @Transactional
    public TCardLabels save(TCardLabels cardLabel) {
        var entity = mapper.toEntity(cardLabel);
        var saved = jpa.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public List<TCardLabels> findByCardId(UUID cardsId) {
        return jpa.findByCardsId(cardsId).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<TCardLabels> findByCardIdAndLabelId(UUID cardsId, UUID labelsId) {
        return jpa.findByCardsIdAndLabelsId(cardsId, labelsId)
                .map(mapper::toDomain);
    }

    @Override
    @Transactional
    public void delete(TCardLabels cardLabel) {
        jpa.deleteById(cardLabel.getId());
    }
}
