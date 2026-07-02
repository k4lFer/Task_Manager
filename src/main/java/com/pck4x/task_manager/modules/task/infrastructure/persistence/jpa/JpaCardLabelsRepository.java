package com.pck4x.task_manager.modules.task.infrastructure.persistence.jpa;

import com.pck4x.task_manager.modules.task.infrastructure.entities.CardLabelsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JpaCardLabelsRepository extends JpaRepository<CardLabelsEntity, UUID> {
    List<CardLabelsEntity> findByCardsId(UUID cardsId);
    Optional<CardLabelsEntity> findByCardsIdAndLabelsId(UUID cardsId, UUID labelsId);
}
