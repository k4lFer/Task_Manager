package com.pck4x.task_manager.modules.board.use_cases.command;

import com.pck4x.task_manager.modules.board.objects.dtos.command.UpdateBoardDto;
import com.pck4x.task_manager.shared.result.OutputPort;

import java.util.UUID;

public interface UpdateBoardCommand {
    OutputPort<Void> execute(UUID userId, UUID boardId, UpdateBoardDto input);
}
