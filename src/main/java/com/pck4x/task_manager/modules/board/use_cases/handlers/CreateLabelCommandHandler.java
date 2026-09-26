package com.pck4x.task_manager.modules.board.use_cases.handlers;

import com.pck4x.task_manager.modules.board.domain.models.TLabel;
import com.pck4x.task_manager.modules.board.interfaces.repositories.IBoardMemberRepository;
import com.pck4x.task_manager.modules.board.interfaces.repositories.IBoardRepository;
import com.pck4x.task_manager.modules.board.interfaces.repositories.ILabelRepository;
import com.pck4x.task_manager.modules.board.objects.dtos.command.CreateLabelDto;
import com.pck4x.task_manager.modules.board.objects.enums.BoardMemberRole;
import com.pck4x.task_manager.modules.board.use_cases.command.CreateLabelCommand;
import com.pck4x.task_manager.modules.workspace.interfaces.services.IWorkspaceAccessService;
import com.pck4x.task_manager.shared.result.OutputPort;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class CreateLabelCommandHandler implements CreateLabelCommand {
    private final ILabelRepository labelRepository;
    private final IBoardRepository boardRepository;
    private final IBoardMemberRepository boardMemberRepository;
    private final IWorkspaceAccessService workspaceAccessService;

    @Override
    public OutputPort<UUID> execute(UUID userId, UUID boardId, CreateLabelDto input) {
        var board = boardRepository.findById(boardId)
                .orElse(null);
        if (board == null) {
            return OutputPort.failure(HttpStatus.NOT_FOUND, "Board not found");
        }

        boolean isBoardOwner = board.getOwnerId().equals(userId);
        boolean isBoardEditor = boardMemberRepository.findByBoardIdAndMemberId(boardId, userId)
                .map(m -> m.getRole() == BoardMemberRole.OWNER || m.getRole() == BoardMemberRole.EDITOR)
                .orElse(false);
        boolean isWorkspaceAdmin = workspaceAccessService.isAdminOrOwner(board.getWorkspaceId(), userId);

        if (!isBoardOwner && !isBoardEditor && !isWorkspaceAdmin) {
            return OutputPort.failure(HttpStatus.FORBIDDEN, "Only board members, workspace admins or owners can create labels");
        }

        if (input.getName() == null || input.getName().isBlank()) {
            return OutputPort.failure(HttpStatus.BAD_REQUEST, "Label name is required");
        }

        var label = TLabel.create(
                input.getName(),
                input.getColor() != null ? input.getColor() : "#94a3b8",
                boardId
        );

        var saved = labelRepository.save(label);
        return OutputPort.success(saved.getId(), HttpStatus.CREATED, "Label created successfully");
    }
}
