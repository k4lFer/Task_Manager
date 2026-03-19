package com.pck4x.task_manager.modules.task.infrastructure.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "comments", schema = "task")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CommentsEntity {
    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cards_id")
    private CardEntity cards;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "content", columnDefinition = "text")
    private String content;

    @Column(name = "created_at", columnDefinition = "timestamptz")
    private Instant createdAt;

    @Column(name = "updated_at", columnDefinition = "timestamptz", nullable = true)
    private Instant updatedAt;

}
