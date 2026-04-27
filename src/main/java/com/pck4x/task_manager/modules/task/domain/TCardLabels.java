package com.pck4x.task_manager.modules.task.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class TCardLabels {
    private UUID id;
    private UUID cardsId;
    private UUID labelsId;

    private TCardLabels(UUID id, UUID cardsId, UUID labelsId)
    {
        this.id = id;
        this.cardsId = cardsId;
        this.labelsId = labelsId;
    }

    public static TCardLabels create(UUID cardsId, UUID labelsId) {
        return new TCardLabels(UUID.randomUUID(), cardsId, labelsId);
    }
}
