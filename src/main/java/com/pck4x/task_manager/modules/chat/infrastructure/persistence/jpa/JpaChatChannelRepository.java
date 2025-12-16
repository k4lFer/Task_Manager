package com.pck4x.task_manager.modules.chat.infrastructure.persistence.jpa;

import com.pck4x.task_manager.modules.chat.infrastructure.entities.ChatChannelEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaChatChannelRepository extends JpaRepository<ChatChannelEntity, UUID> {
    Page<ChatChannelEntity> findByWorkspaceId(UUID workspaceId, Pageable pageable);
}
