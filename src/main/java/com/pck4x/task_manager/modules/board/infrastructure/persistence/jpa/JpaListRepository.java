package com.pck4x.task_manager.modules.board.infrastructure.persistence.jpa;

import com.pck4x.task_manager.modules.board.infrastructure.entities.ListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface JpaListRepository extends JpaRepository<ListEntity, UUID> {

    @Query("SELECT l.id FROM ListEntity l WHERE l.board.id = :boardId")
    List<UUID> findIdsByBoardId(@Param("boardId") UUID boardId);
}
