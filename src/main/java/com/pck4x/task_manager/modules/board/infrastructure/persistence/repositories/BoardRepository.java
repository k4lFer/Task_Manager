package com.pck4x.task_manager.modules.board.infrastructure.persistence.repositories;

import com.pck4x.task_manager.modules.board.domain.models.TBoard;
import com.pck4x.task_manager.modules.board.infrastructure.entities.BoardEntity;
import com.pck4x.task_manager.modules.board.infrastructure.mapper.BoardMapper;
import com.pck4x.task_manager.modules.board.infrastructure.persistence.jpa.JpaBoardRepository;
import com.pck4x.task_manager.modules.board.interfaces.repositories.IBoardRepository;
import com.pck4x.task_manager.modules.board.objects.dtos.query.BoardSummaryDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class BoardRepository implements IBoardRepository {
    private final JpaBoardRepository jpa;
    private final BoardMapper mapper;

    @Override
    @Transactional
    public TBoard save(TBoard board) {
        BoardEntity entity = mapper.toEntity(board);

        BoardEntity saved = jpa.save(entity);

        return mapper.toDomain(saved);
    }

    @Override
    public Optional<TBoard> findById(UUID id) {
        return jpa.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<TBoard> findByWorkspaceId(UUID workspaceId) {
        return jpa.findByWorkspaceId(workspaceId).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    @Transactional
    public void delete(TBoard board) {
        jpa.deleteById(board.getId());
    }

    @Override
    public List<BoardSummaryDto> findBoardSummariesByWorkspaceId(UUID workspaceId, UUID userId) {
        return jpa.findBoardSummariesByWorkspaceId(workspaceId, userId);
    }

    @Override
    public List<BoardSummaryDto> findBoardSummariesByUserId(UUID userId, UUID workspaceId) {
        return jpa.findBoardSummariesByMemberId(workspaceId, userId);
    }
}
