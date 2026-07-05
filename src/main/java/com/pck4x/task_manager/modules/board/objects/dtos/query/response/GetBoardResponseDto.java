package com.pck4x.task_manager.modules.board.objects.dtos.query.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.UUID;

@Schema(description = "Detailed board information with members, lists and labels")
public record GetBoardResponseDto(
        @Schema(description = "Board unique identifier", example = "550e8400-e29b-41d4-a716-446655440000")
        UUID id,
        @Schema(description = "Workspace ID that contains the board", example = "550e8400-e29b-41d4-a716-446655440000")
        UUID workspaceId,
        @Schema(description = "Owner's user ID", example = "550e8400-e29b-41d4-a716-446655440000")
        UUID ownerId,
        @Schema(description = "Owner's full name", example = "John Doe")
        String ownerName,
        @Schema(description = "Board name", example = "Sprint Board")
        String name,
        @Schema(description = "Board description", example = "Kanban board for the current sprint")
        String description,
        @Schema(description = "List of board members")
        List<BoardMemberResponseDto> members,
        @Schema(description = "List of columns/lists in the board")
        List<BoardListResponseDto> lists,
        @Schema(description = "List of labels available in the board")
        List<BoardLabelResponseDto> labels
) {
}
