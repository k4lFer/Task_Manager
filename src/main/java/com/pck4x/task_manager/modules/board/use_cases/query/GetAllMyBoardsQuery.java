package com.pck4x.task_manager.modules.board.use_cases.query;

import com.pck4x.task_manager.modules.board.objects.dtos.query.response.GetBoardsResponseDto;
import com.pck4x.task_manager.shared.result.OutputPort;

import java.util.List;
import java.util.UUID;

public interface GetAllMyBoardsQuery {
    OutputPort<List<GetBoardsResponseDto>> execute(UUID userId, UUID workspaceId);
}
