package com.pck4x.task_manager.modules.task.interfaces.repositories;

import com.pck4x.task_manager.modules.task.domain.TCardAssignments;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ICardAssignmentRepository {
    TCardAssignments save(TCardAssignments assignment);
    List<TCardAssignments> findByCardId(UUID cardsId);
    Optional<TCardAssignments> findByCardIdAndUserId(UUID cardsId, UUID userId);
    void delete(TCardAssignments assignment);
}
