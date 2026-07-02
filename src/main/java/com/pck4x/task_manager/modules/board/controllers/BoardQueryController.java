package com.pck4x.task_manager.modules.board.controllers;

import com.pck4x.task_manager.modules.board.use_cases.query.GetBoardByIdQuery;
import com.pck4x.task_manager.modules.board.use_cases.query.GetBoardsByWorkspaceId;
import com.pck4x.task_manager.shared.helper.ResponseHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

    @GetMapping("/workspace/{workspaceId}/boards")
    @Operation(
            summary = "",
            description = ""
    )
    public ResponseEntity<?> GetBoards(
            @Parameter(hidden = true) @AuthenticationPrincipal String userId,
            @PathVariable UUID workspaceId
    ) {
        var result = getBoardsByWorkspaceId.execute(workspaceId, UUID.fromString(userId));
        return ResponseHelper.toResponse(result);
    }

    @GetMapping("/{boardId}")
    @Operation(
            summary = "",
            description = ""
    )
    public ResponseEntity<?> GetById(
            @Parameter(hidden = true) @AuthenticationPrincipal String userId,
            @PathVariable UUID boardId
    ) {
        var result = getBoardByIdQuery.execute(boardId, UUID.fromString(userId));
        return ResponseHelper.toResponse(result);
    }
}
