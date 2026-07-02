package com.pck4x.task_manager.modules.board.use_cases.query;

import com.pck4x.task_manager.modules.board.objects.dtos.query.response.GetBoardResponseDto;
import com.pck4x.task_manager.shared.result.OutputPort;

import java.util.UUID;

public interface GetBoardByIdQuery {
    OutputPort<GetBoardResponseDto> execute(UUID id, UUID userId);
}
