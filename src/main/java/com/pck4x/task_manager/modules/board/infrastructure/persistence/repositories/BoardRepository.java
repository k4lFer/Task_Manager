package com.pck4x.task_manager.modules.board.infrastructure.persistence.repositories;

import com.pck4x.task_manager.modules.board.domain.models.TBoard;
import com.pck4x.task_manager.modules.board.infrastructure.entities.BoardEntity;
import com.pck4x.task_manager.modules.board.infrastructure.mapper.BoardMapper;
import com.pck4x.task_manager.modules.board.infrastructure.persistence.jpa.JpaBoardRepository;
import com.pck4x.task_manager.modules.board.interfaces.repositories.IBoardRepository;
import com.pck4x.task_manager.modules.board.objects.dtos.query.BoardSummaryDto;
import com.pck4x.task_manager.modules.board.objects.dtos.query.response.BoardLabelResponseDto;
import com.pck4x.task_manager.modules.board.objects.dtos.query.response.BoardListResponseDto;
import com.pck4x.task_manager.modules.board.objects.dtos.query.response.BoardMemberResponseDto;
import com.pck4x.task_manager.modules.board.objects.dtos.query.response.GetBoardResponseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @Override
    @Transactional
    public Optional<GetBoardResponseDto> findBoardDetailById(UUID id, UUID userId) {
        return jpa.findById(id).map(entity -> {
            var ownerName = jpa.findOwnerNameByUserId(entity.getOwnerId());

            var memberNames = jpa.findMemberNamesByBoardId(id).stream()
                    .collect(Collectors.toMap(
                            row -> (UUID) row[0],
                            row -> (String) row[1]
                    ));

            var members = entity.getBoardMembers().stream()
                    .map(m -> new BoardMemberResponseDto(
                            m.getMemberId(),
                            memberNames.getOrDefault(m.getMemberId(), "Unknown"),
                            m.getRole(),
                            m.getMemberId().equals(userId),
                            m.getCreatedAt()
                    ))
                    .toList();

            var lists = entity.getLists().stream()
                    .map(l -> new BoardListResponseDto(l.getId(), l.getName(), l.getPosition()))
                    .toList();

            var labels = entity.getLabel().stream()
                    .map(l -> new BoardLabelResponseDto(l.getId(), l.getName(), l.getColor()))
                    .toList();

            return new GetBoardResponseDto(
                    entity.getId(),
                    entity.getWorkspaceId(),
                    entity.getOwnerId(),
                    ownerName,
                    entity.getName(),
                    entity.getDescription(),
                    members,
                    lists,
                    labels
            );
        });
    }
}
