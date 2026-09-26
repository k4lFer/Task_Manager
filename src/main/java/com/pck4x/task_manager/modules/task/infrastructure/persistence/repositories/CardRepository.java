package com.pck4x.task_manager.modules.task.infrastructure.persistence.repositories;

import com.pck4x.task_manager.modules.task.domain.TCard;
import com.pck4x.task_manager.modules.task.infrastructure.mapper.CardMapper;
import com.pck4x.task_manager.modules.task.infrastructure.persistence.jpa.JpaCardRepository;
import com.pck4x.task_manager.modules.task.interfaces.repositories.ICardRepository;
import com.pck4x.task_manager.modules.task.objects.dtos.query.CardDetailDto;
import com.pck4x.task_manager.modules.task.objects.dtos.query.CardSummaryDto;
import com.pck4x.task_manager.modules.task.objects.dtos.query.response.AssignableMemberDto;
import com.pck4x.task_manager.modules.task.objects.dtos.query.response.CardLabelResponseDto;
import com.pck4x.task_manager.modules.task.objects.dtos.query.response.CardMemberResponseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CardRepository implements ICardRepository {
    private final JpaCardRepository jpa;
    private final CardMapper mapper;

    @Override
    @Transactional
    public TCard save(TCard card) {
        var entity = mapper.toEntity(card);
        var saved = jpa.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<TCard> findById(UUID id) {
        return jpa.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<TCard> findAllByListId(UUID listId) {
        return jpa.findByListsIdOrderByPositionAsc(listId).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    @Transactional
    public void delete(TCard card) {
        jpa.deleteById(card.getId());
    }

    @Override
    public Integer getNextPosition(UUID listId) {
        return jpa.getNextPosition(listId);
    }

    @Override
    public List<CardSummaryDto> findCardSummariesByListId(UUID listId) {
        return jpa.findCardSummariesByListId(listId);
    }

    @Override
    @Transactional
    public void deleteByListsIdIn(List<UUID> listIds) {
        jpa.deleteByListsIdIn(listIds);
    }

    @Override
    @Transactional
    public Optional<CardDetailDto> findCardDetailById(UUID id) {
        return jpa.findById(id).map(entity -> {
            var memberNames = jpa.findMemberNamesByCardId(id).stream()
                    .collect(Collectors.toMap(
                            row -> (UUID) row[0],
                            row -> (String) row[1]
                    ));

            var members = entity.getCardAssignments().stream()
                    .map(a -> new CardMemberResponseDto(
                            a.getUserId(),
                            memberNames.getOrDefault(a.getUserId(), "Unknown")
                    ))
                    .toList();

            var labelDetails = jpa.findLabelDetailsByCardId(id).stream()
                    .collect(Collectors.toMap(
                            row -> (UUID) row[0],
                            row -> new CardLabelResponseDto(
                                    (UUID) row[0],
                                    (String) row[1],
                                    (String) row[2]
                            )
                    ));

            var labels = entity.getCardLabels().stream()
                    .map(cl -> labelDetails.getOrDefault(cl.getLabelsId(),
                            new CardLabelResponseDto(cl.getLabelsId(), "Unknown", "#000000")))
                    .toList();

            var commentsCount = entity.getComments().size();

            return new CardDetailDto(
                    entity.getId(),
                    entity.getListsId(),
                    entity.getTitle(),
                    entity.getDescription(),
                    entity.getPosition(),
                    entity.getStartDate(),
                    entity.getDueDate(),
                    entity.getCompleted_at(),
                    entity.getProgress(),
                    members,
                    labels,
                    commentsCount,
                    0,
                    entity.getCreatedAt(),
                    entity.getUpdatedAt()
            );
        });
    }

    @Override
    public List<AssignableMemberDto> findAssignableMembers(UUID cardId, UUID currentUserId) {
        var boardRows = jpa.findBoardInfoByCardId(cardId);
        if (boardRows == null || boardRows.isEmpty()) return List.of();

        var boardInfo = boardRows.get(0);
        UUID boardId = (UUID) boardInfo[0];
        UUID boardOwnerId = (UUID) boardInfo[2];

        var boardMembers = jpa.findBoardMembersWithNames(boardId);
        var assignedUserIds = jpa.findAssignedUserRows(cardId).stream()
                .map(row -> (UUID) row[0])
                .collect(Collectors.toSet());

        return boardMembers.stream().map(row -> {
            UUID userId = (UUID) row[0];
            String fullName = (String) row[1];
            String role = (String) row[2];
            return new AssignableMemberDto(
                    userId,
                    fullName,
                    assignedUserIds.contains(userId),
                    userId.equals(currentUserId),
                    role,
                    userId.equals(boardOwnerId) || "OWNER".equals(role) || "EDITOR".equals(role)
            );
        }).toList();
    }
}
