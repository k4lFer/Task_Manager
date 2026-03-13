package com.pck4x.task_manager.modules.board.use_cases.command;

import com.pck4x.task_manager.modules.board.objects.enums.BoardMemberRole;
import com.pck4x.task_manager.shared.result.OutputPort;

import java.util.UUID;

public interface AddBoardMemberCommand {
    OutputPort<UUID> execute(UUID workspaceId, UUID boardId, UUID ownerId, UUID userId, BoardMemberRole role);
}
