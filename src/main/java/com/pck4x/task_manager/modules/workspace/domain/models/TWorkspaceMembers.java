package com.pck4x.task_manager.modules.workspace.domain.models;

import com.pck4x.task_manager.modules.workspace.objects.enums.WorkspaceMemberRole;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Builder
public class TWorkspaceMembers {
    private UUID id;
    private UUID workspaceId;
    private UUID memberId;
    private WorkspaceMemberRole role;
    private Instant createdAt;

    public static TWorkspaceMembers create(UUID workspaceId, UUID memberId)
    {
        return TWorkspaceMembers.builder()
                .id(UUID.randomUUID())
                .workspaceId(workspaceId)
                .memberId(memberId)
                .role(WorkspaceMemberRole.OWNER)
                .createdAt(Instant.now())
                .build();
    }

    void addMember(UUID memberId, WorkspaceMemberRole role){
        this.memberId = memberId;
        this.role = role;
        this.createdAt = Instant.now();
    }

    void changeRole(UUID memberId, WorkspaceMemberRole role){
        this.memberId = memberId;
        this.role = role;
    }
}
