package com.pck4x.task_manager.modules.task.infrastructure.persistence.jpa;

import com.pck4x.task_manager.modules.task.infrastructure.entities.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface JpaCardRepository extends JpaRepository<CardEntity, UUID> {
    @Query("""
        SELECT COALESCE(MAX(c.position), 0) + 1
        FROM CardEntity c
        WHERE c.listsId = :listId
    """)
    Integer getNextPosition(@Param("listId") UUID listId);
}
