package com.pck4x.task_manager.modules.board.controllers;

import com.pck4x.task_manager.modules.board.objects.dtos.query.response.BoardAssignableMemberDto;
import com.pck4x.task_manager.modules.board.objects.dtos.query.response.GetBoardResponseDto;
import com.pck4x.task_manager.modules.board.objects.dtos.query.response.GetBoardsResponseDto;
import com.pck4x.task_manager.modules.board.use_cases.query.GetBoardAssignableMembersQuery;
import com.pck4x.task_manager.modules.board.use_cases.query.GetBoardByIdQuery;
import com.pck4x.task_manager.modules.board.use_cases.query.GetBoardsByWorkspaceId;
import com.pck4x.task_manager.shared.helper.ResponseHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/board")
@Tag(name = "Boards")
@SecurityRequirement(name = "bearerAuth")
@AllArgsConstructor
public class BoardQueryController {
    private final GetBoardsByWorkspaceId getBoardsByWorkspaceId;
    private final GetBoardByIdQuery getBoardByIdQuery;
    private final GetBoardAssignableMembersQuery getBoardAssignableMembersQuery;

    @GetMapping("/workspace/{workspaceId}/boards")
    @Operation(
            summary = "Get boards by workspace",
            description = "Retrieves all boards within a workspace."
    )
    @ApiResponse(responseCode = "200", description = "List of boards", content = @Content(schema = @Schema(implementation = GetBoardsResponseDto.class)))
    public ResponseEntity<?> GetBoards(
            @Parameter(hidden = true) @AuthenticationPrincipal String userId,
            @PathVariable UUID workspaceId
    ) {
        var result = getBoardsByWorkspaceId.execute(workspaceId, UUID.fromString(userId));
        return ResponseHelper.toResponse(result);
    }

    @GetMapping("/{boardId}")
    @Operation(
            summary = "Get board details",
            description = "Retrieves detailed information about a specific board including members, lists and labels."
    )
    @ApiResponse(responseCode = "200", description = "Board details", content = @Content(schema = @Schema(implementation = GetBoardResponseDto.class)))
    public ResponseEntity<?> GetById(
            @Parameter(hidden = true) @AuthenticationPrincipal String userId,
            @PathVariable UUID boardId
    ) {
        var result = getBoardByIdQuery.execute(boardId, UUID.fromString(userId));
        return ResponseHelper.toResponse(result);
    }

    @GetMapping("/{boardId}/assignable-members")
    @Operation(
            summary = "Get assignable board members",
            description = "Retrieves workspace members that can be added to the board, with board membership status."
    )
    @ApiResponse(responseCode = "200", description = "Assignable members", content = @Content(schema = @Schema(implementation = BoardAssignableMemberDto.class)))
    public ResponseEntity<?> GetAssignableMembers(
            @Parameter(hidden = true) @AuthenticationPrincipal String userId,
            @PathVariable UUID boardId
    ) {
        var result = getBoardAssignableMembersQuery.execute(boardId, UUID.fromString(userId));
        return ResponseHelper.toResponse(result);
    }
}
