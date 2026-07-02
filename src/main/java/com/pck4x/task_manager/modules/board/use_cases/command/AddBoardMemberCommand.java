package com.pck4x.task_manager.modules.board.use_cases.command;

import com.pck4x.task_manager.modules.board.objects.dtos.command.AddBoardMemberDto;
import com.pck4x.task_manager.shared.result.OutputPort;

import java.util.UUID;

public interface AddBoardMemberCommand {
    OutputPort<UUID> execute(UUID ownerId, UUID boardId, AddBoardMemberDto input);
}
