package com.pck4x.task_manager.modules.board.use_cases.handlers;

import com.pck4x.task_manager.modules.board.interfaces.repositories.IBoardRepository;
import com.pck4x.task_manager.modules.board.objects.dtos.query.BoardSummaryDto;
import com.pck4x.task_manager.modules.board.use_cases.query.GetAllMyBoardsQuery;
import com.pck4x.task_manager.shared.result.OutputPort;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class GetAllMyBoardsQueryHandler implements GetAllMyBoardsQuery {
    private final IBoardRepository boardRepository;

    @Override
    public OutputPort<List<BoardSummaryDto>> execute(UUID userId, UUID workspaceId) {
        var boards = boardRepository.findBoardSummariesByUserId(userId, workspaceId);

        if (boards.isEmpty()) {
            return OutputPort.failure(HttpStatus.NO_CONTENT, null);
        }

        return OutputPort.success(boards, HttpStatus.OK, "Boards retrieved successfully");
    }
}
