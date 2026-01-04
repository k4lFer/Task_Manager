package com.pck4x.task_manager.modules.workspace.infrastructure.persistence.jpa;

import com.pck4x.task_manager.modules.workspace.infrastructure.entities.WorkspaceInvitationEntity;
import com.pck4x.task_manager.modules.workspace.objects.dtos.query.Response.GetReceivedInvitationsResponse;
import com.pck4x.task_manager.modules.workspace.objects.dtos.query.Response.GetSentInvitationsResponse;
import com.pck4x.task_manager.modules.workspace.objects.enums.WorkspaceInvitationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface JpaWorkspaceInvitationRepository extends JpaRepository<WorkspaceInvitationEntity, UUID> {
    boolean existsByWorkspaceIdAndInvitedUserIdAndStatus(
            UUID workspaceId,
            UUID invitedUserId,
            WorkspaceInvitationStatus status
    );

    boolean existsByWorkspaceIdAndInvitedEmailAndStatus(
            UUID workspaceId,
            String invitedEmail,
            WorkspaceInvitationStatus status
    );

    @Query(
            value = """
        SELECT new com.pck4x.task_manager.modules.workspace.objects.dtos.query.Response.GetReceivedInvitationsResponse(
            wi.id,
            CONCAT(p.firstName, ' ', p.lastName),
            w.id,
            w.name,
            wi.status
        )
        FROM WorkspaceInvitationEntity wi
        JOIN wi.workspace w
        JOIN UserEntity u ON u.id = wi.invitedBy
        JOIN u.person p
        WHERE wi.invitedUserId = :userId
          AND wi.status = :status
        ORDER BY wi.createdAt DESC
    """,
            countQuery = """
        SELECT COUNT(wi.id)
        FROM WorkspaceInvitationEntity wi
        WHERE wi.invitedUserId = :userId
          AND wi.status = :status
    """
    )
    Page<GetReceivedInvitationsResponse> getReceivedInvitations(
            @Param("userId") UUID userId,
            @Param("status") WorkspaceInvitationStatus status,
            Pageable pageable
    );

    @Query(
            value = """
        SELECT new com.pck4x.task_manager.modules.workspace.objects.dtos.query.Response.GetSentInvitationsResponse(
            wi.id,
            CASE
                WHEN wi.invitedUserId IS NOT NULL
                    THEN CONCAT(p.firstName, ' ', p.lastName)
                ELSE wi.invitedEmail
            END,
            w.id,
            w.name,
            wi.status
        )
        FROM WorkspaceInvitationEntity wi
        JOIN wi.workspace w
        LEFT JOIN UserEntity u ON u.id = wi.invitedUserId
        LEFT JOIN u.person p
        WHERE wi.invitedBy = :userId
          AND wi.status = :status
        ORDER BY wi.createdAt DESC
    """,
            countQuery = """
        SELECT COUNT(wi.id)
        FROM WorkspaceInvitationEntity wi
        WHERE wi.invitedBy = :userId
          AND wi.status = :status
    """
    )
    Page<GetSentInvitationsResponse> getSentInvitations(
            @Param("userId") UUID userId,
            @Param("status") WorkspaceInvitationStatus status,
            Pageable pageable
    );

}
