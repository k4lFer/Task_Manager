package com.pck4x.task_manager.modules.board.interfaces.repositories;

import com.pck4x.task_manager.modules.board.domain.models.TBoard;
import com.pck4x.task_manager.modules.board.objects.dtos.query.BoardSummaryDto;
import com.pck4x.task_manager.modules.board.objects.dtos.query.response.GetBoardResponseDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IBoardRepository {
    TBoard save(TBoard board);
    Optional<TBoard> findById(UUID id);
    List<TBoard> findByWorkspaceId(UUID workspaceId);
    void delete(TBoard board);
    List<BoardSummaryDto> findBoardSummariesByWorkspaceId(UUID workspaceId, UUID userId);
    List<BoardSummaryDto> findBoardSummariesByUserId(UUID userId, UUID workspaceId);
    Optional<GetBoardResponseDto> findBoardDetailById(UUID id, UUID userId);
}
