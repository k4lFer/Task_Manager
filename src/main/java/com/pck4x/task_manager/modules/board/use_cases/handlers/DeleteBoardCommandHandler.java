package com.pck4x.task_manager.modules.board.use_cases.handlers;

import com.pck4x.task_manager.modules.board.interfaces.repositories.IBoardRepository;
import com.pck4x.task_manager.modules.board.interfaces.repositories.IListRepository;
import com.pck4x.task_manager.modules.board.use_cases.command.DeleteBoardCommand;
import com.pck4x.task_manager.modules.workspace.interfaces.services.IWorkspaceAccessService;
import com.pck4x.task_manager.shared.application.adapter.DomainEventPublisher;
import com.pck4x.task_manager.shared.result.OutputPort;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class DeleteBoardCommandHandler implements DeleteBoardCommand {
    private final IBoardRepository boardRepository;
    private final IListRepository listRepository;
    private final IWorkspaceAccessService workspaceAccessService;
    private final DomainEventPublisher domainEventPublisher;

    @Override
    public OutputPort<Void> execute(UUID userId, UUID boardId) {
        var board = boardRepository.findById(boardId);
        if (board.isEmpty()) {
            return OutputPort.failure(HttpStatus.NOT_FOUND, "Board not found");
        }

        var boardData = board.get();

        if (!workspaceAccessService.isAdminOrOwner(boardData.getWorkspaceId(), userId)) {
            return OutputPort.failure(HttpStatus.FORBIDDEN, "Only workspace owner or admins can delete this board");
        }

        var listIds = listRepository.findIdsByBoardId(boardId);
        boardData.markAsDeleted(listIds);

        boardRepository.delete(boardData);

        domainEventPublisher.publishFrom(boardData);

        return OutputPort.success(null, HttpStatus.OK, "Board deleted successfully");
    }
}
