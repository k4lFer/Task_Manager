package com.pck4x.task_manager.modules.workspace.infrastructure.persistence.jpa;

import com.pck4x.task_manager.modules.workspace.infrastructure.entities.WorkspaceEntity;
import com.pck4x.task_manager.modules.workspace.objects.dtos.query.*;
import com.pck4x.task_manager.modules.workspace.objects.dtos.query.Response.CheckWorkspaceInvitationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JpaWorkspaceRepository extends JpaRepository<WorkspaceEntity, UUID> {
    @Query("""
        SELECT new com.pck4x.task_manager.modules.workspace.objects.dtos.query.WorkspaceDetailBaseDto(
            w.id,
            w.name,
            w.description,
            w.isPrivate,
            CASE WHEN w.ownerId = :userId THEN true ELSE false END,
            w.ownerId,
            CONCAT(p.firstName, ' ', p.lastName),
            w.createdAt
        )
        FROM WorkspaceEntity w
        JOIN UserEntity u ON u.id = w.ownerId
        JOIN u.person p
        WHERE w.id = :workspaceId
    """)
    Optional<WorkspaceDetailBaseDto> findWorkspaceDetailBase(
            UUID workspaceId,
            UUID userId
    );

    @Query("""
        SELECT new com.pck4x.task_manager.modules.workspace.objects.dtos.query.WorkspaceMemberDto(
            wm.memberId,
            CONCAT(p.firstName, ' ', p.lastName),
            wm.role,
            CASE WHEN wm.memberId = :userId THEN true ELSE false END,
            wm.createdAt
        )
        FROM WorkspaceMemberEntity wm
        JOIN UserEntity u ON u.id = wm.memberId
        JOIN u.person p
        WHERE wm.workspace.id = :workspaceId
    """)
    List<WorkspaceMemberDto> findMembers(
            UUID workspaceId,
            UUID userId
    );

    @Query("""
        SELECT new com.pck4x.task_manager.modules.workspace.objects.dtos.query.WorkspaceChannelDto(
            c.id,
            c.name,
            c.description
        )
        FROM ChannelEntity c
        WHERE c.workspaceId = :workspaceId
    """)
    List<WorkspaceChannelDto> findChannels(UUID workspaceId);


    @Query("""
        SELECT new com.pck4x.task_manager.modules.workspace.objects.dtos.query.WorkspaceBoardDto(
            b.id,
            b.name,
            b.description,
            b.ownerId,
            CONCAT(p.firstName, ' ', p.lastName),
            (SELECT COUNT(bm.id) FROM BoardMemberEntity bm WHERE bm.board.id = b.id),
            (SELECT COUNT(l.id) FROM ListEntity l WHERE l.board.id = b.id),
            (SELECT COUNT(c.id) FROM CardEntity c WHERE c.listsId IN (SELECT l2.id FROM ListEntity l2 WHERE l2.board.id = b.id)),
            b.createdAt
        )
        FROM BoardEntity b
        JOIN UserEntity u ON u.id = b.ownerId
        JOIN u.person p
        WHERE b.workspaceId = :workspaceId
        ORDER BY b.createdAt DESC
    """)
    List<WorkspaceBoardDto> findBoards(UUID workspaceId);

    @Query(
        value = """
            SELECT new com.pck4x.task_manager.modules.workspace.objects.dtos.query.WorkspaceDto(
                w.id,
                w.name,
                w.description,
                w.isPrivate,
                CASE WHEN w.ownerId = :userId THEN true ELSE false END,
                w.ownerId,
                CONCAT(p.firstName, ' ', p.lastName)
            )
            FROM WorkspaceEntity w
            JOIN UserEntity u ON u.id = w.ownerId
            JOIN u.person p
            WHERE w.ownerId = :userId
               OR EXISTS (
                    SELECT 1
                    FROM WorkspaceMemberEntity wm
                    WHERE wm.workspace.id = w.id
                      AND wm.memberId = :userId
               )
        """,
        countQuery = """
        SELECT COUNT(w.id)
        FROM WorkspaceEntity w
        WHERE w.ownerId = :userId
           OR EXISTS (
                SELECT 1
                FROM WorkspaceMemberEntity wm
                WHERE wm.workspace.id = w.id
                  AND wm.memberId = :userId
           )
    """
    )
    Page<WorkspaceDto> findAllByOwnerOrMember(@Param("userId") UUID userId, Pageable pageable);

    @Query(
        value = """
            SELECT new com.pck4x.task_manager.modules.workspace.objects.dtos.query.Response.CheckWorkspaceInvitationResponse(
                CASE WHEN wm.id IS NULL AND wi.id IS NULL THEN true ELSE false END,
                CASE WHEN wm.id IS NOT NULL THEN true ELSE false END,
                CASE WHEN wi.id IS NOT NULL THEN true ELSE false END,
                u.id,
                u.email,
                CONCAT(p.firstName, ' ', p.lastName),
                CASE
                    WHEN wm.id IS NOT NULL THEN 'Already a workspace member'
                    WHEN wi.id IS NOT NULL THEN 'Pending invitation'
                    ELSE 'User can be invited'
                END
            )
            FROM UserEntity u
            JOIN u.person p
            LEFT JOIN WorkspaceMemberEntity wm ON wm.memberId = u.id AND wm.workspace.id = :workspaceId
            LEFT JOIN WorkspaceInvitationEntity wi ON wi.workspace.id = :workspaceId AND (wi.invitedUserId = u.id OR wi.invitedEmail = u.email) AND wi.status = :pendingStatus
            WHERE u.email LIKE CONCAT(:query, '%')
            ORDER BY u.email ASC
        """,
        countQuery = """
            SELECT COUNT(u.id)
            FROM UserEntity u
            WHERE u.email LIKE CONCAT(:query, '%')
        """
    )
    Page<CheckWorkspaceInvitationResponse> findInvitableUsers(
            @Param("workspaceId") UUID workspaceId,
            @Param("query") String query,
            @Param("pendingStatus") com.pck4x.task_manager.modules.workspace.objects.enums.WorkspaceInvitationStatus pendingStatus,
            Pageable pageable
    );
}
