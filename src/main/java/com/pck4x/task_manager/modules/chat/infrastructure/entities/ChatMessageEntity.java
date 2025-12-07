package com.pck4x.task_manager.modules.chat.infrastructure.entities;

import com.fasterxml.jackson.databind.JsonNode;
import com.pck4x.task_manager.modules.auth.infrastructure.entities.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "chat_messages", schema = "chat")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ChatMessageEntity {
    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_channel_id", nullable = false)
    private ChatChannelEntity chatChannel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(name = "message", columnDefinition = "text")
    private String message;

    @Column(name = "content", columnDefinition = "jsonb")
    private JsonNode content;

    @Column(name = "sent_at", columnDefinition = "timestamptz")
    private Instant sentAt;

    @Column(name = "edited_at", columnDefinition = "timestamptz")
    private Instant editedAt;
}
