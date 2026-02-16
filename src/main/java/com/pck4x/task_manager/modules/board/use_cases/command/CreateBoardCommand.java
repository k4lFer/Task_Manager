package com.pck4x.task_manager.modules.board.use_cases.command;


import com.pck4x.task_manager.modules.board.objects.dtos.command.CreateBoardDto;
import com.pck4x.task_manager.shared.result.OutputPort;

import java.util.UUID;

public interface CreateBoardCommand {
    OutputPort<UUID> execute(UUID userId, UUID workspaceId, CreateBoardDto input);
}
