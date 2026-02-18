package com.pck4x.task_manager.modules.board.infrastructure.persistence.jpa;

import com.pck4x.task_manager.modules.board.infrastructure.entities.ListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaListRepository extends JpaRepository<ListEntity, UUID> {
}
