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
}
