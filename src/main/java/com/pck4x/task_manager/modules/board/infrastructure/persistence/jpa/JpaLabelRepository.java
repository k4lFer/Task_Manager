package com.pck4x.task_manager.modules.board.infrastructure.persistence.jpa;

import com.pck4x.task_manager.modules.board.infrastructure.entities.LabelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JpaLabelRepository extends JpaRepository<LabelEntity, UUID> {
    List<LabelEntity> findByBoardId(UUID boardId);
}
