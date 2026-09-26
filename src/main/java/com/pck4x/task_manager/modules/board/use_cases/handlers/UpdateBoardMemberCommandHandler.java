package com.pck4x.task_manager.modules.board.use_cases.handlers;

import com.pck4x.task_manager.modules.board.domain.models.TBoardMembers;
import com.pck4x.task_manager.modules.board.interfaces.repositories.IBoardMemberRepository;
import com.pck4x.task_manager.modules.board.interfaces.repositories.IBoardRepository;
import com.pck4x.task_manager.modules.board.objects.dtos.command.UpdateBoardMemberDto;
import com.pck4x.task_manager.modules.board.use_cases.command.UpdateBoardMemberCommand;
import com.pck4x.task_manager.modules.workspace.interfaces.services.IWorkspaceAccessService;
import com.pck4x.task_manager.shared.result.OutputPort;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class UpdateBoardMemberCommandHandler implements UpdateBoardMemberCommand {
    private final IBoardRepository boardRepository;
    private final IBoardMemberRepository boardMemberRepository;
    private final IWorkspaceAccessService workspaceAccessService;

    @Override
    public OutputPort<Void> execute(UUID userId, UUID boardId, UUID memberId, UpdateBoardMemberDto input) {
        var board = boardRepository.findById(boardId);
        if (board.isEmpty()) {
            return OutputPort.failure(HttpStatus.NOT_FOUND, "Board not found");
        }

        var boardData = board.get();

        if (!boardData.getOwnerId().equals(userId) &&
            !workspaceAccessService.isAdminOrOwner(boardData.getWorkspaceId(), userId)) {
            return OutputPort.failure(HttpStatus.FORBIDDEN, "Only board owner or workspace admins can update member roles");
        }

        var membership = boardMemberRepository.findByBoardIdAndMemberId(boardId, memberId);
        if (membership.isEmpty()) {
            return OutputPort.failure(HttpStatus.NOT_FOUND, "Member not found in this board");
        }

        var existing = membership.get();
        var updated = TBoardMembers.builder()
                .id(existing.getId())
                .boardId(existing.getBoardId())
                .memberId(existing.getMemberId())
                .role(input.getRole())
                .createdAt(existing.getCreatedAt())
                .build();

        boardMemberRepository.save(updated);
        return OutputPort.success(null, HttpStatus.OK, "Member role updated successfully");
    }
}
