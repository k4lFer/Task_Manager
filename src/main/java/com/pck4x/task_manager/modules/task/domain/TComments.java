package com.pck4x.task_manager.modules.task.domain;

import com.pck4x.task_manager.modules.task.domain.events.CommentDeletedEvent;
import com.pck4x.task_manager.modules.task.domain.events.CommentUpdatedEvent;
import com.pck4x.task_manager.shared.domain.repository.TGenericDomain;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Builder
public class TComments extends TGenericDomain {
    private UUID id;
    private UUID cardsId;
    private UUID userId;
    private String content;
    private Instant createdAt;
    private Instant updatedAt;

    public static TComments create(UUID cardsId, UUID userId, String content) {
        return TComments.builder()
                .id(UUID.randomUUID())
                .cardsId(cardsId)
                .userId(userId)
                .content(content)
                .createdAt(Instant.now())
                .build();
    }

    public void update(String content) {
        if (content != null) this.content = content;
        this.updatedAt = Instant.now();
        domainEvents.add(new CommentUpdatedEvent(this.id));
    }

    public void markAsDeleted() {
        domainEvents.add(new CommentDeletedEvent(this.id));
    }
}
