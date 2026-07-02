package com.pck4x.task_manager.modules.task.infrastructure.persistence.repositories;

import com.pck4x.task_manager.modules.task.domain.TCardAssignments;
import com.pck4x.task_manager.modules.task.infrastructure.mapper.CardAssignmentMapper;
import com.pck4x.task_manager.modules.task.infrastructure.persistence.jpa.JpaCardAssignmentsRepository;
import com.pck4x.task_manager.modules.task.interfaces.repositories.ICardAssignmentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CardAssignmentRepository implements ICardAssignmentRepository {
    private final JpaCardAssignmentsRepository jpa;
    private final CardAssignmentMapper mapper;

    @Override
    @Transactional
    public TCardAssignments save(TCardAssignments assignment) {
        var entity = mapper.toEntity(assignment);
        var saved = jpa.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public List<TCardAssignments> findByCardId(UUID cardsId) {
        return jpa.findByCardsId(cardsId).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<TCardAssignments> findByCardIdAndUserId(UUID cardsId, UUID userId) {
        return jpa.findByCardsIdAndUserId(cardsId, userId)
                .map(mapper::toDomain);
    }

    @Override
    @Transactional
    public void delete(TCardAssignments assignment) {
        jpa.deleteById(assignment.getId());
    }
}
