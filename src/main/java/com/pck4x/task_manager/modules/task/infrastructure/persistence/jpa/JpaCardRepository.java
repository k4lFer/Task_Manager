package com.pck4x.task_manager.modules.task.infrastructure.persistence.jpa;

import com.pck4x.task_manager.modules.task.infrastructure.entities.CardEntity;
import com.pck4x.task_manager.modules.task.objects.dtos.query.CardSummaryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface JpaCardRepository extends JpaRepository<CardEntity, UUID> {
    @Query("SELECT ca.userId, CONCAT(p.firstName, ' ', p.lastName) FROM CardAssignmentsEntity ca JOIN UserEntity u ON u.id = ca.userId JOIN u.person p WHERE ca.cards.id = :cardId")
    List<Object[]> findMemberNamesByCardId(@Param("cardId") UUID cardId);

    @Query("SELECT l.id, l.name, l.color FROM CardLabelsEntity cl JOIN LabelEntity l ON l.id = cl.labelsId WHERE cl.cards.id = :cardId")
    List<Object[]> findLabelDetailsByCardId(@Param("cardId") UUID cardId);
    List<CardEntity> findByListsIdOrderByPositionAsc(UUID listsId);

    @Query("""
        SELECT COALESCE(MAX(c.position), 0) + 1
        FROM CardEntity c
        WHERE c.listsId = :listId
    """)
    Integer getNextPosition(@Param("listId") UUID listId);

    @Query("""
        SELECT new com.pck4x.task_manager.modules.task.objects.dtos.query.CardSummaryDto(
            c.id,
            c.listsId,
            c.title,
            c.description,
            c.position,
            c.startDate,
            c.dueDate,
            c.completed_at,
            c.progress,
            (SELECT COUNT(cm.id) FROM CommentsEntity cm WHERE cm.cards.id = c.id),
            0,
            c.createdAt,
            c.updatedAt
        )
        FROM CardEntity c
        WHERE c.listsId = :listId
        ORDER BY c.position ASC
    """)
    List<CardSummaryDto> findCardSummariesByListId(@Param("listId") UUID listId);

    @Modifying
    @Query("DELETE FROM CardEntity c WHERE c.listsId IN :listIds")
    void deleteByListsIdIn(@Param("listIds") List<UUID> listIds);

    @Query(value = """
        SELECT b.id, b.workspace_id, b.owner_id
        FROM task.cards c
        JOIN board.lists l ON l.id = c.lists_id
        JOIN board.boards b ON b.id = l.board_id
        WHERE c.id = :cardId
        """, nativeQuery = true)
    List<Object[]> findBoardInfoByCardId(@Param("cardId") UUID cardId);

    @Query(value = """
        SELECT bm.member_id,
               CONCAT(p.first_name, ' ', p.last_name) AS full_name,
               bm.role AS board_role
        FROM board.board_members bm
        JOIN auth.users u ON u.id = bm.member_id
        JOIN auth.persons p ON p.id = u.person_id
        WHERE bm.board_id = :boardId
        """, nativeQuery = true)
    List<Object[]> findBoardMembersWithNames(@Param("boardId") UUID boardId);

    @Query(value = """
        SELECT ca.user_id
        FROM task.card_assignments ca
        WHERE ca.cards_id = :cardId
        """, nativeQuery = true)
    List<Object[]> findAssignedUserRows(@Param("cardId") UUID cardId);
}
