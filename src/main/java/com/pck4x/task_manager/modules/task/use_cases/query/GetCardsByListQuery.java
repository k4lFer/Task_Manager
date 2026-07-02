package com.pck4x.task_manager.modules.task.use_cases.query;

import com.pck4x.task_manager.modules.task.objects.dtos.query.CardSummaryDto;
import com.pck4x.task_manager.shared.result.OutputPort;

import java.util.List;
import java.util.UUID;

public interface GetCardsByListQuery {
    OutputPort<List<CardSummaryDto>> execute(UUID listId, UUID userId);
}
