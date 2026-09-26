package com.pck4x.task_manager.modules.board.use_cases.handlers;

import com.pck4x.task_manager.modules.board.infrastructure.persistence.jpa.JpaBoardRepository;
import com.pck4x.task_manager.modules.board.interfaces.repositories.IBoardRepository;
import com.pck4x.task_manager.modules.board.objects.dtos.query.response.BoardAssignableMemberDto;
import com.pck4x.task_manager.modules.board.use_cases.query.GetBoardAssignableMembersQuery;
import com.pck4x.task_manager.shared.result.OutputPort;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class GetBoardAssignableMembersQueryHandler implements GetBoardAssignableMembersQuery {
    private final IBoardRepository boardRepository;
    private final JpaBoardRepository jpaBoardRepository;

    @Override
    public OutputPort<List<BoardAssignableMemberDto>> execute(UUID boardId, UUID userId) {
        var board = boardRepository.findById(boardId);
        if (board.isEmpty()) {
            return OutputPort.failure(HttpStatus.NOT_FOUND, "Board not found");
        }

        var boardData = board.get();
        var rows = jpaBoardRepository.findWorkspaceMembersWithBoardStatus(
                boardData.getWorkspaceId(), boardId
        );

        var members = rows.stream().map(row -> new BoardAssignableMemberDto(
                (UUID) row[0],
                (String) row[1],
                (Boolean) row[3],
                ((UUID) row[0]).equals(userId),
                (String) row[2]
        )).toList();

        return OutputPort.success(members, HttpStatus.OK, "Assignable members retrieved");
    }
}
