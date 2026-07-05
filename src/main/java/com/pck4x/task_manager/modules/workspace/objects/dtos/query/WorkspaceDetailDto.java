package com.pck4x.task_manager.modules.workspace.objects.dtos.query;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Schema(description = "Detailed workspace information including members, channels and boards")
public record WorkspaceDetailDto(
        @Schema(description = "Workspace unique identifier", example = "550e8400-e29b-41d4-a716-446655440000")
        UUID id,
        @Schema(description = "Workspace name", example = "My Project")
        String name,
        @Schema(description = "Workspace description", example = "Project management workspace")
        String description,
        @Schema(description = "Whether the workspace is private")
        Boolean isPrivate,
        @Schema(description = "Whether the authenticated user is the owner")
        Boolean isOwner,
        @Schema(description = "Owner's user ID", example = "550e8400-e29b-41d4-a716-446655440000")
        UUID ownerId,
        @Schema(description = "Owner's full name", example = "John Doe")
        String ownerName,
        @Schema(description = "List of workspace members")
        List<WorkspaceMemberDto> members,
        @Schema(description = "List of chat channels in the workspace")
        List<WorkspaceChannelDto> channels,
        @Schema(description = "List of boards in the workspace")
        List<WorkspaceBoardDto> boards,
        @Schema(description = "Creation timestamp", example = "2024-01-15T10:30:00Z")
        Instant createdAt
) {}
