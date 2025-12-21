package com.pck4x.task_manager.modules.workspace.infrastructure.persistence.jpa;

import com.pck4x.task_manager.modules.workspace.infrastructure.entities.WorkspaceEntity;
import com.pck4x.task_manager.modules.workspace.objects.dtos.query.*;
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
        FROM ChatChannelEntity c
        WHERE c.workspaceId = :workspaceId
    """)
    List<WorkspaceChannelDto> findChannels(UUID workspaceId);

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
}
