package com.pck4x.task_manager.modules.board.use_cases.handlers;

import com.pck4x.task_manager.modules.board.interfaces.repositories.IBoardRepository;
import com.pck4x.task_manager.modules.board.objects.dtos.command.UpdateBoardDto;
import com.pck4x.task_manager.modules.board.use_cases.command.UpdateBoardCommand;
import com.pck4x.task_manager.modules.workspace.interfaces.services.IWorkspaceAccessService;
import com.pck4x.task_manager.shared.application.adapter.DomainEventPublisher;
import com.pck4x.task_manager.shared.result.OutputPort;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class UpdateBoardCommandHandler implements UpdateBoardCommand {
    private final IBoardRepository boardRepository;
    private final IWorkspaceAccessService workspaceAccessService;
    private final DomainEventPublisher domainEventPublisher;

    @Override
    public OutputPort<Void> execute(UUID userId, UUID boardId, UpdateBoardDto input) {
        var board = boardRepository.findById(boardId);
        if (board.isEmpty()) {
            return OutputPort.failure(HttpStatus.NOT_FOUND, "Board not found");
        }

        var boardData = board.get();

        if (!workspaceAccessService.isAdminOrOwner(boardData.getWorkspaceId(), userId)) {
            return OutputPort.failure(HttpStatus.FORBIDDEN, "Only workspace owner or admins can update this board");
        }

        boardData.update(input.getName(), input.getDescription(), input.getIsPrivate());

        var saved = boardRepository.save(boardData);
        if (saved == null) {
            return OutputPort.failure(HttpStatus.BAD_REQUEST, "Unable to update board");
        }

        domainEventPublisher.publishFrom(boardData);

        return OutputPort.success(null, HttpStatus.OK, "Board updated successfully");
    }
}
