package com.pck4x.task_manager.modules.task.domain;

import com.pck4x.task_manager.modules.task.domain.events.CardCreatedEvent;
import com.pck4x.task_manager.modules.task.domain.events.CardDeletedEvent;
import com.pck4x.task_manager.modules.task.domain.events.CardUpdatedEvent;
import com.pck4x.task_manager.shared.domain.repository.TGenericDomain;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Getter
@Builder
public class TCard extends TGenericDomain {
    private UUID id;
    private UUID listsId;
    private String title;
    private String description;
    private Integer position;
    private Instant startDate;
    private Instant dueDate;
    private Instant completedAt;
    private BigDecimal progress;
    private Instant createdAt;
    private Instant updatedAt;

    public static TCard create(UUID listsId, String title, String description, Integer position, Instant startDate, Instant dueDate) {
        var card = TCard.builder()
                .id(UUID.randomUUID())
                .listsId(listsId)
                .title(title)
                .description(description)
                .position(position)
                .startDate(startDate)
                .dueDate(dueDate)
                .progress(BigDecimal.ZERO)
                .createdAt(Instant.now())
                .build();

        card.domainEvents.add(new CardCreatedEvent(card.id));

        return card;
    }

    public void update(String title, String description, Instant startDate, Instant dueDate, BigDecimal progress) {
        if (title != null) this.title = title;
        if (description != null) this.description = description;
        if (startDate != null) this.startDate = startDate;
        if (dueDate != null) this.dueDate = dueDate;
        if (progress != null) this.progress = progress;
        this.updatedAt = Instant.now();
        domainEvents.add(new CardUpdatedEvent(this.id));
    }

    public void markAsDeleted() {
        domainEvents.add(new CardDeletedEvent(this.id));
    }
}
