package com.pck4x.task_manager.modules.workspace.objects.dtos.query;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(description = "Workspace summary information")
public record WorkspaceDto(
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
        String ownerName
) {}
