package com.pck4x.task_manager.modules.board.controllers;

import com.pck4x.task_manager.modules.board.objects.dtos.command.CreateBoardDto;
import com.pck4x.task_manager.modules.board.use_cases.command.CreateBoardCommand;
import com.pck4x.task_manager.shared.helper.ResponseHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/board")
@Tag(name = "Boards")
@SecurityRequirement(name = "bearerAuth")
@AllArgsConstructor
public class BoardCommandController {
    private final CreateBoardCommand createBoardCommand;

    @PostMapping("/workspace/{workspaceId}/create")
    @Operation(
            summary = "",
            description = ""
    )
    public ResponseEntity<?> Create(
            @Parameter(hidden = true) @AuthenticationPrincipal String userId,
            @PathVariable UUID workspaceId,
            @RequestBody CreateBoardDto input
    ){
        var result = createBoardCommand.execute(UUID.fromString(userId), workspaceId, input);
        return ResponseHelper.toResponse(result);
    }
}
