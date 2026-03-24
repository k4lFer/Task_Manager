package com.pck4x.task_manager.modules.task.domain;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Getter
@Builder
public class TCard {
    private UUID id;
    private UUID listsId;
    private String title;
    private String description;
    private Integer position;
    private Instant startDate;
    private Instant dueDate;
    private Instant completed_at;
    private BigDecimal progress;
    private Instant createdAt;
    private Instant updatedAt;

    public static TCard create(UUID listsId, String title, String description, Integer position, Instant startDate, Instant dueDate) {
        return TCard.builder()
                .id(UUID.randomUUID())
                .listsId(listsId)
                .title(title)
                .description(description)
                .position(position)
                .startDate(startDate)
                .dueDate(dueDate)
                .progress(new BigDecimal(0))
                .createdAt(Instant.now())
                .updatedAt(null)
                .build();
    }

}
