package com.pck4x.task_manager.modules.board.use_cases.handlers;

import com.pck4x.task_manager.modules.auth.interfaces.services.IUserService;
import com.pck4x.task_manager.modules.board.interfaces.repositories.IBoardMemberRepository;
import com.pck4x.task_manager.modules.board.interfaces.repositories.IBoardRepository;
import com.pck4x.task_manager.modules.board.objects.dtos.query.response.GetBoardResponseDto;
import com.pck4x.task_manager.modules.board.use_cases.query.GetBoardByIdQuery;
import com.pck4x.task_manager.modules.workspace.interfaces.services.IWorkspaceAccessService;
import com.pck4x.task_manager.shared.result.OutputPort;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class GetBoardByIdQueryHandler implements GetBoardByIdQuery {
    private final IBoardRepository boardRepository;
    private final IBoardMemberRepository boardMemberRepository;
    private final IWorkspaceAccessService workspaceAccessService;
    private final IUserService userService;

    @Override
    public OutputPort<GetBoardResponseDto> execute(UUID id, UUID userId) {
        var board = boardRepository.findById(id);
        if (board.isEmpty()) {
            return OutputPort.failure(HttpStatus.NOT_FOUND, "Board not found");
        }

        var boardData = board.get();

        if (!workspaceAccessService.isExists(boardData.getWorkspaceId(), userId)) {
            return OutputPort.failure(HttpStatus.FORBIDDEN, "You are not a member of this workspace");
        }

        var ownerInfo = userService.getUserById(boardData.getOwnerId());
        var ownerName = ownerInfo.map(u -> u.firstName() + " " + u.lastName()).orElse(null);

        var members = boardMemberRepository.findByBoardId(boardData.getId()).stream()
                .map(m -> {
                    var userInfo = userService.getUserById(m.getMemberId());
                    return (Object) userInfo.map(u -> u.firstName() + " " + u.lastName()).orElse(null);
                })
                .toList();

        var dto = new GetBoardResponseDto(
                boardData.getId(),
                boardData.getWorkspaceId(),
                boardData.getOwnerId(),
                ownerName,
                boardData.getName(),
                boardData.getDescription(),
                members,
                List.of(),
                List.of()
        );

        return OutputPort.success(dto, HttpStatus.OK, "Board retrieved successfully");
    }
}
