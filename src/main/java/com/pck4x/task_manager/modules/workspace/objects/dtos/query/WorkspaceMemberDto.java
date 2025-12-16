package com.pck4x.task_manager.modules.workspace.objects.dtos.query;

import com.pck4x.task_manager.modules.workspace.objects.enums.WorkspaceMemberRole;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class WorkspaceMemberDto {
    private UUID userId;
    private String fullName;
    private WorkspaceMemberRole role;
    private Boolean itsYou;
    private Instant joinedAt;
}