package com.pck4x.task_manager.modules.chat.infrastructure.persistence.jpa;

import com.pck4x.task_manager.modules.chat.infrastructure.entities.ChatMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaChatMessageRepository extends JpaRepository<ChatMessageEntity, UUID> {
}
