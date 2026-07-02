package com.pck4x.task_manager.modules.board.use_cases.handlers;

import com.pck4x.task_manager.modules.board.interfaces.repositories.IBoardRepository;
import com.pck4x.task_manager.modules.board.objects.dtos.query.BoardSummaryDto;
import com.pck4x.task_manager.modules.board.use_cases.query.GetBoardsByWorkspaceId;
import com.pck4x.task_manager.modules.workspace.interfaces.services.IWorkspaceAccessService;
import com.pck4x.task_manager.shared.result.OutputPort;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class GetBoardsByWorkspaceIdHandler implements GetBoardsByWorkspaceId {
    private final IBoardRepository boardRepository;
    private final IWorkspaceAccessService workspaceAccessService;

    @Override
    public OutputPort<List<BoardSummaryDto>> execute(UUID workspaceId, UUID userId) {
        if (!workspaceAccessService.isExists(workspaceId, userId)) {
            return OutputPort.failure(HttpStatus.FORBIDDEN, "You are not a member of this workspace");
        }

        var boards = boardRepository.findBoardSummariesByWorkspaceId(workspaceId, userId);

        if (boards.isEmpty()) {
            return OutputPort.failure(HttpStatus.NO_CONTENT, null);
        }

        return OutputPort.success(boards, HttpStatus.OK, "Boards retrieved successfully");
    }
}
