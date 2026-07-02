package com.pck4x.task_manager.modules.board.use_cases.handlers;

import com.pck4x.task_manager.modules.board.domain.models.TBoardMembers;
import com.pck4x.task_manager.modules.board.interfaces.repositories.IBoardMemberRepository;
import com.pck4x.task_manager.modules.board.interfaces.repositories.IBoardRepository;
import com.pck4x.task_manager.modules.board.objects.dtos.command.AddBoardMemberDto;
import com.pck4x.task_manager.modules.board.use_cases.command.AddBoardMemberCommand;
import com.pck4x.task_manager.modules.workspace.interfaces.services.IWorkspaceAccessService;
import com.pck4x.task_manager.shared.result.OutputPort;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class AddBoardMemberCommandHandler implements AddBoardMemberCommand {
    private final IBoardRepository boardRepository;
    private final IBoardMemberRepository boardMemberRepository;
    private final IWorkspaceAccessService workspaceAccessService;

    @Override
    public OutputPort<UUID> execute(UUID ownerId, UUID boardId, AddBoardMemberDto input) {
        var board = boardRepository.findById(boardId);
        if (board.isEmpty()) {
            return OutputPort.failure(HttpStatus.NOT_FOUND, "Board not found");
        }

        var workspaceId = board.get().getWorkspaceId();

        if (!workspaceAccessService.isExists(workspaceId, input.getUserId())) {
            return OutputPort.failure(HttpStatus.FORBIDDEN, "User is not a member of this workspace");
        }

        if (!workspaceAccessService.isAdminOrOwner(workspaceId, ownerId)) {
            return OutputPort.failure(HttpStatus.FORBIDDEN, "Only Workspace Owner or Admins can add members");
        }

        if (boardMemberRepository.findByBoardIdAndMemberId(boardId, input.getUserId()).isPresent()) {
            return OutputPort.failure(HttpStatus.FORBIDDEN, "User is already a member of this board");
        }

        var member = TBoardMembers.create(
                boardId,
                input.getUserId(),
                input.getRole()
        );

        var saved = boardMemberRepository.save(member);

        if (saved != null)
            return OutputPort.success(saved.getId(), HttpStatus.CREATED, "Member added successfully");

        return OutputPort.failure(HttpStatus.BAD_REQUEST, "Something went wrong");
    }
}
