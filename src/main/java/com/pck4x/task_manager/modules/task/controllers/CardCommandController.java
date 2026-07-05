package com.pck4x.task_manager.modules.task.controllers;

import com.pck4x.task_manager.modules.task.objects.dtos.commands.*;
import com.pck4x.task_manager.modules.task.use_cases.command.*;
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
    @Operation(summary = "Create a card", description = "Creates a new card in the specified list.")
    @ApiResponse(responseCode = "200", description = "Card created successfully", content = @Content(schema = @Schema(implementation = UUID.class)))
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
    @Operation(summary = "Update a card", description = "Updates the properties of an existing card.")
    @ApiResponse(responseCode = "200", description = "Card updated successfully")
    public ResponseEntity<?> Update(
            @Parameter(hidden = true) @AuthenticationPrincipal String userId,
            @PathVariable UUID cardId,
            @RequestBody UpdateCardDto input
    ) {
        var result = updateCardCommand.execute(UUID.fromString(userId), cardId, input);
        return ResponseHelper.toResponse(result);
    }

    @DeleteMapping("/{cardId}")
    @Operation(summary = "Delete a card", description = "Deletes a card and its associated data.")
    @ApiResponse(responseCode = "200", description = "Card deleted successfully")
    public ResponseEntity<?> Delete(
            @Parameter(hidden = true) @AuthenticationPrincipal String userId,
            @PathVariable UUID cardId
    ) {
        var result = deleteCardCommand.execute(UUID.fromString(userId), cardId);
        return ResponseHelper.toResponse(result);
    }

    @PostMapping("/{cardId}/assignments")
    @Operation(summary = "Assign a member to card", description = "Assigns a workspace member to a card.")
    @ApiResponse(responseCode = "200", description = "Member assigned successfully", content = @Content(schema = @Schema(implementation = UUID.class)))
    public ResponseEntity<?> AddAssignment(
            @Parameter(hidden = true) @AuthenticationPrincipal String userId,
            @PathVariable UUID cardId,
            @RequestBody AddAssignmentDto input
    ) {
        var result = addAssignmentCommand.execute(UUID.fromString(userId), cardId, input);
        return ResponseHelper.toResponse(result);
    }

    @DeleteMapping("/{cardId}/assignments/{memberId}")
    @Operation(summary = "Remove member assignment", description = "Removes a member assignment from a card.")
    @ApiResponse(responseCode = "200", description = "Assignment removed successfully")
    public ResponseEntity<?> RemoveAssignment(
            @Parameter(hidden = true) @AuthenticationPrincipal String userId,
            @PathVariable UUID cardId,
            @PathVariable UUID memberId
    ) {
        var result = removeAssignmentCommand.execute(UUID.fromString(userId), cardId, memberId);
        return ResponseHelper.toResponse(result);
    }

    @PostMapping("/{cardId}/labels")
    @Operation(summary = "Add label to card", description = "Attaches a label to a card.")
    @ApiResponse(responseCode = "200", description = "Label added successfully", content = @Content(schema = @Schema(implementation = UUID.class)))
    public ResponseEntity<?> AddLabel(
            @Parameter(hidden = true) @AuthenticationPrincipal String userId,
            @PathVariable UUID cardId,
            @RequestBody AddLabelDto input
    ) {
        var result = addLabelCommand.execute(UUID.fromString(userId), cardId, input);
        return ResponseHelper.toResponse(result);
    }

    @DeleteMapping("/{cardId}/labels/{labelId}")
    @Operation(summary = "Remove label from card", description = "Removes a label from a card.")
    @ApiResponse(responseCode = "200", description = "Label removed successfully")
    public ResponseEntity<?> RemoveLabel(
            @Parameter(hidden = true) @AuthenticationPrincipal String userId,
            @PathVariable UUID cardId,
            @PathVariable UUID labelId
    ) {
        var result = removeLabelCommand.execute(UUID.fromString(userId), cardId, labelId);
        return ResponseHelper.toResponse(result);
    }
}
