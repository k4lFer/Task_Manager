package com.pck4x.task_manager.modules.board.use_cases.handlers;

import com.pck4x.task_manager.modules.board.interfaces.repositories.IBoardRepository;
import com.pck4x.task_manager.modules.board.objects.dtos.query.response.GetBoardResponseDto;
import com.pck4x.task_manager.modules.board.use_cases.query.GetBoardByIdQuery;
import com.pck4x.task_manager.modules.workspace.interfaces.services.IWorkspaceAccessService;
import com.pck4x.task_manager.shared.result.OutputPort;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class GetBoardByIdQueryHandler implements GetBoardByIdQuery {
    private final IBoardRepository boardRepository;
    private final IWorkspaceAccessService workspaceAccessService;

    @Override
    public OutputPort<GetBoardResponseDto> execute(UUID id, UUID userId) {
        var board = boardRepository.findBoardDetailById(id, userId);
        if (board.isEmpty()) {
            return OutputPort.failure(HttpStatus.NOT_FOUND, "Board not found");
        }

        var boardData = board.get();

        if (!workspaceAccessService.isExists(boardData.workspaceId(), userId)) {
            return OutputPort.failure(HttpStatus.FORBIDDEN, "You are not a member of this workspace");
        }

        return OutputPort.success(boardData, HttpStatus.OK, "Board retrieved successfully");
    }
}
