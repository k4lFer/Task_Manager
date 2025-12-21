package com.pck4x.task_manager.modules.chat.infrastructure.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "chat_channels", schema = "chat")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ChatChannelEntity {
    @Id
    private UUID id;

    @Column(name = "workspace_id", nullable = false)
    private UUID workspaceId;

    @Column(name = "name")
    private String name;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "created_at", columnDefinition = "timestamptz")
    private Instant createdAt;

    @OneToMany(mappedBy = "chatChannel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChatMessageEntity> chatMessage = new ArrayList<>();
}
