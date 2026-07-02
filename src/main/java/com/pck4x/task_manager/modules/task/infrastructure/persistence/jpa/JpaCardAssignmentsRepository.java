package com.pck4x.task_manager.modules.task.infrastructure.persistence.jpa;

import com.pck4x.task_manager.modules.task.infrastructure.entities.CardAssignmentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JpaCardAssignmentsRepository extends JpaRepository<CardAssignmentsEntity, UUID> {
    List<CardAssignmentsEntity> findByCardsId(UUID cardsId);
    Optional<CardAssignmentsEntity> findByCardsIdAndUserId(UUID cardsId, UUID userId);
}
