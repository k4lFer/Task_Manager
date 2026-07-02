package com.pck4x.task_manager.modules.task.controllers;

import com.pck4x.task_manager.modules.task.objects.dtos.commands.*;
import com.pck4x.task_manager.modules.task.use_cases.command.*;
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
@RequestMapping("/card")
@Tag(name = "Cards")
@SecurityRequirement(name = "bearerAuth")
@AllArgsConstructor
public class CardCommandController {
    private final CreateCardCommand createCardCommand;
    private final UpdateCardCommand updateCardCommand;
    private final DeleteCardCommand deleteCardCommand;
    private final AddAssignmentCommand addAssignmentCommand;
    private final RemoveAssignmentCommand removeAssignmentCommand;
    private final AddLabelCommand addLabelCommand;
    private final RemoveLabelCommand removeLabelCommand;

    @PostMapping("/list/{listId}/create")
    @Operation(summary = "", description = "")
    public ResponseEntity<?> Create(
            @Parameter(hidden = true) @AuthenticationPrincipal String userId,
            @PathVariable UUID listId,
            @RequestBody CreateCardDto input
    ) {
        input.listId = listId;
        var result = createCardCommand.execute(UUID.fromString(userId), input);
        return ResponseHelper.toResponse(result);
    }

    @PatchMapping("/{cardId}")
    @Operation(summary = "", description = "")
    public ResponseEntity<?> Update(
            @Parameter(hidden = true) @AuthenticationPrincipal String userId,
            @PathVariable UUID cardId,
            @RequestBody UpdateCardDto input
    ) {
        var result = updateCardCommand.execute(UUID.fromString(userId), cardId, input);
        return ResponseHelper.toResponse(result);
    }

    @DeleteMapping("/{cardId}")
    @Operation(summary = "", description = "")
    public ResponseEntity<?> Delete(
            @Parameter(hidden = true) @AuthenticationPrincipal String userId,
            @PathVariable UUID cardId
    ) {
        var result = deleteCardCommand.execute(UUID.fromString(userId), cardId);
        return ResponseHelper.toResponse(result);
    }

    @PostMapping("/{cardId}/assignments")
    @Operation(summary = "", description = "")
    public ResponseEntity<?> AddAssignment(
            @Parameter(hidden = true) @AuthenticationPrincipal String userId,
            @PathVariable UUID cardId,
            @RequestBody AddAssignmentDto input
    ) {
        var result = addAssignmentCommand.execute(UUID.fromString(userId), cardId, input);
        return ResponseHelper.toResponse(result);
    }

    @DeleteMapping("/{cardId}/assignments/{memberId}")
    @Operation(summary = "", description = "")
    public ResponseEntity<?> RemoveAssignment(
            @Parameter(hidden = true) @AuthenticationPrincipal String userId,
            @PathVariable UUID cardId,
            @PathVariable UUID memberId
    ) {
        var result = removeAssignmentCommand.execute(UUID.fromString(userId), cardId, memberId);
        return ResponseHelper.toResponse(result);
    }

    @PostMapping("/{cardId}/labels")
    @Operation(summary = "", description = "")
    public ResponseEntity<?> AddLabel(
            @Parameter(hidden = true) @AuthenticationPrincipal String userId,
            @PathVariable UUID cardId,
            @RequestBody AddLabelDto input
    ) {
        var result = addLabelCommand.execute(UUID.fromString(userId), cardId, input);
        return ResponseHelper.toResponse(result);
    }

    @DeleteMapping("/{cardId}/labels/{labelId}")
    @Operation(summary = "", description = "")
    public ResponseEntity<?> RemoveLabel(
            @Parameter(hidden = true) @AuthenticationPrincipal String userId,
            @PathVariable UUID cardId,
            @PathVariable UUID labelId
    ) {
        var result = removeLabelCommand.execute(UUID.fromString(userId), cardId, labelId);
        return ResponseHelper.toResponse(result);
    }
}
