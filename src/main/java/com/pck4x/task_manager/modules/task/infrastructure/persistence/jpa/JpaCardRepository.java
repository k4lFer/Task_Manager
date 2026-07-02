package com.pck4x.task_manager.modules.task.infrastructure.persistence.jpa;

import com.pck4x.task_manager.modules.task.infrastructure.entities.CardEntity;
import com.pck4x.task_manager.modules.task.objects.dtos.query.CardSummaryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface JpaCardRepository extends JpaRepository<CardEntity, UUID> {
    List<CardEntity> findByListsIdOrderByPositionAsc(UUID listsId);

    @Query("""
        SELECT COALESCE(MAX(c.position), 0) + 1
        FROM CardEntity c
        WHERE c.listsId = :listId
    """)
    Integer getNextPosition(@Param("listId") UUID listId);

    @Query("""
        SELECT new com.pck4x.task_manager.modules.task.objects.dtos.query.CardSummaryDto(
            c.id,
            c.listsId,
            c.title,
            c.description,
            c.position,
            c.startDate,
            c.dueDate,
            c.completed_at,
            c.progress,
            (SELECT COUNT(cm.id) FROM CommentsEntity cm WHERE cm.cards.id = c.id),
            0,
            c.createdAt,
            c.updatedAt
        )
        FROM CardEntity c
        WHERE c.listsId = :listId
        ORDER BY c.position ASC
    """)
    List<CardSummaryDto> findCardSummariesByListId(@Param("listId") UUID listId);

    @Modifying
    @Query("DELETE FROM CardEntity c WHERE c.listsId IN :listIds")
    void deleteByListsIdIn(@Param("listIds") List<UUID> listIds);
}
