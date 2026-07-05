package com.pck4x.task_manager.modules.board.infrastructure.persistence.jpa;

import com.pck4x.task_manager.modules.board.infrastructure.entities.BoardEntity;
import com.pck4x.task_manager.modules.board.objects.dtos.query.BoardSummaryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface JpaBoardRepository extends JpaRepository<BoardEntity, UUID> {
    List<BoardEntity> findByWorkspaceId(UUID workspaceId);

    @Query("SELECT CONCAT(p.firstName, ' ', p.lastName) FROM UserEntity u JOIN u.person p WHERE u.id = :userId")
    String findOwnerNameByUserId(@Param("userId") UUID userId);

    @Query("SELECT bm.memberId, CONCAT(p.firstName, ' ', p.lastName) FROM BoardMemberEntity bm JOIN UserEntity u ON u.id = bm.memberId JOIN u.person p WHERE bm.board.id = :boardId")
    List<Object[]> findMemberNamesByBoardId(@Param("boardId") UUID boardId);

    @Query("""
        SELECT new com.pck4x.task_manager.modules.board.objects.dtos.query.BoardSummaryDto(
            b.id,
            b.workspaceId,
            b.name,
            b.description,
            CASE WHEN b.ownerId = :userId THEN true ELSE false END,
            b.ownerId,
            CONCAT(p.firstName, ' ', p.lastName),
            (SELECT COUNT(bm.id) FROM BoardMemberEntity bm WHERE bm.board.id = b.id),
            (SELECT COUNT(l.id) FROM ListEntity l WHERE l.board.id = b.id),
            (SELECT COUNT(c.id) FROM CardEntity c WHERE c.listsId IN (SELECT l2.id FROM ListEntity l2 WHERE l2.board.id = b.id))
        )
        FROM BoardEntity b
        JOIN UserEntity u ON u.id = b.ownerId
        JOIN u.person p
        WHERE b.workspaceId = :workspaceId
        ORDER BY b.createdAt DESC
    """)
    List<BoardSummaryDto> findBoardSummariesByWorkspaceId(@Param("workspaceId") UUID workspaceId, @Param("userId") UUID userId);

    @Query("""
        SELECT new com.pck4x.task_manager.modules.board.objects.dtos.query.BoardSummaryDto(
            b.id,
            b.workspaceId,
            b.name,
            b.description,
            CASE WHEN b.ownerId = :userId THEN true ELSE false END,
            b.ownerId,
            CONCAT(p.firstName, ' ', p.lastName),
            (SELECT COUNT(bm.id) FROM BoardMemberEntity bm WHERE bm.board.id = b.id),
            (SELECT COUNT(l.id) FROM ListEntity l WHERE l.board.id = b.id),
            (SELECT COUNT(c.id) FROM CardEntity c WHERE c.listsId IN (SELECT l2.id FROM ListEntity l2 WHERE l2.board.id = b.id))
        )
        FROM BoardEntity b
        JOIN UserEntity u ON u.id = b.ownerId
        JOIN u.person p
        WHERE b.workspaceId = :workspaceId
          AND (b.ownerId = :userId OR EXISTS (
                SELECT 1 FROM BoardMemberEntity bm WHERE bm.board.id = b.id AND bm.memberId = :userId
              ))
        ORDER BY b.createdAt DESC
    """)
    List<BoardSummaryDto> findBoardSummariesByMemberId(@Param("workspaceId") UUID workspaceId, @Param("userId") UUID userId);
}
