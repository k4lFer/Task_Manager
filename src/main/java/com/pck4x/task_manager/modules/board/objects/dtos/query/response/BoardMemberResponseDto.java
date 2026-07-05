package com.pck4x.task_manager.modules.board.objects.dtos.query.response;

import com.pck4x.task_manager.modules.board.objects.enums.BoardMemberRole;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;
import java.util.UUID;

@Schema(description = "Board member information")
public record BoardMemberResponseDto(
        @Schema(description = "User ID", example = "550e8400-e29b-41d4-a716-446655440000")
        UUID userId,
        @Schema(description = "Member's full name", example = "John Doe")
        String fullName,
        @Schema(description = "Member role in the board")
        BoardMemberRole role,
        @Schema(description = "Whether this member is the authenticated user")
        Boolean itsYou,
        @Schema(description = "When the member joined", example = "2024-01-15T10:30:00Z")
        Instant joinedAt
) {}
