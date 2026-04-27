package com.pck4x.task_manager.modules.workspace.domain.models;

import com.pck4x.task_manager.modules.workspace.objects.enums.WorkspaceMemberRole;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Builder(toBuilder = true)
public class TWorkspaceMembers {

    private final UUID id;
    private final UUID memberId;
    private final WorkspaceMemberRole role;
    private final Instant createdAt;

    public static TWorkspaceMembers create(UUID memberId, WorkspaceMemberRole role) {
        return TWorkspaceMembers.builder()
                .id(UUID.randomUUID())
                .memberId(memberId)
                .role(role)
                .createdAt(Instant.now())
                .build();
    }
}