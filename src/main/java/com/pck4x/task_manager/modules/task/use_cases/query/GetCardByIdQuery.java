package com.pck4x.task_manager.modules.task.use_cases.query;

import com.pck4x.task_manager.modules.task.objects.dtos.query.CardDetailDto;
import com.pck4x.task_manager.shared.result.OutputPort;

import java.util.UUID;

public interface GetCardByIdQuery {
    OutputPort<CardDetailDto> execute(UUID id, UUID userId);
}
