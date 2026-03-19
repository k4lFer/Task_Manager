package com.pck4x.task_manager.modules.chat.infrastructure.persistence.jpa;

import com.pck4x.task_manager.modules.chat.infrastructure.entities.ChatMessageEntity;
import com.pck4x.task_manager.modules.chat.objects.dtos.query.ChatMessageResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface JpaChatMessageRepository extends JpaRepository<ChatMessageEntity, UUID> {
    @Query("""
        SELECT new com.pck4x.task_manager.modules.chat.objects.dtos.query.ChatMessageResponseDto(
            c.id,
            c.channels.id,
            c.message,
            c.userId,
            CONCAT(p.firstName, ' ', p.lastName),
            c.sentAt,
            c.editedAt,
            (c.editedAt IS NOT NULL)
        )
        FROM ChatMessageEntity c
        JOIN UserEntity u ON u.id = c.userId
        JOIN u.person p
        WHERE c.channels.id = :channelId
        ORDER BY c.sentAt DESC
    """)
    Page<ChatMessageResponseDto> findBychannelId(UUID channelId, Pageable pageable);

}
