package com.pck4x.task_manager.modules.task.controllers;

import com.pck4x.task_manager.modules.task.objects.dtos.commands.AddCommentDto;
import com.pck4x.task_manager.modules.task.objects.dtos.commands.UpdateCommentDto;
import com.pck4x.task_manager.modules.task.objects.dtos.query.CommentDto;
import com.pck4x.task_manager.modules.task.use_cases.command.AddCommentCommand;
import com.pck4x.task_manager.modules.task.use_cases.command.DeleteCommentCommand;
import com.pck4x.task_manager.modules.task.use_cases.command.UpdateCommentCommand;
import com.pck4x.task_manager.modules.task.use_cases.query.GetCommentsByCardQuery;
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
@RequestMapping("/comments")
@Tag(name = "Comments")
@SecurityRequirement(name = "bearerAuth")
@AllArgsConstructor
public class CommentController {
    private final AddCommentCommand addCommentCommand;
    private final UpdateCommentCommand updateCommentCommand;
    private final DeleteCommentCommand deleteCommentCommand;
    private final GetCommentsByCardQuery getCommentsByCardQuery;

    @PostMapping("/card/{cardId}")
    @Operation(summary = "Add comment to card", description = "Adds a new comment to a card.")
    @ApiResponse(responseCode = "200", description = "Comment added successfully", content = @Content(schema = @Schema(implementation = UUID.class)))
    public ResponseEntity<?> AddComment(
            @Parameter(hidden = true) @AuthenticationPrincipal String userId,
            @PathVariable UUID cardId,
            @RequestBody AddCommentDto input
    ) {
        var result = addCommentCommand.execute(UUID.fromString(userId), cardId, input);
        return ResponseHelper.toResponse(result);
    }

    @PatchMapping("/{commentId}")
    @Operation(summary = "Update a comment", description = "Updates the content of an existing comment.")
    @ApiResponse(responseCode = "200", description = "Comment updated successfully")
    public ResponseEntity<?> UpdateComment(
            @Parameter(hidden = true) @AuthenticationPrincipal String userId,
            @PathVariable UUID commentId,
            @RequestBody UpdateCommentDto input
    ) {
        var result = updateCommentCommand.execute(UUID.fromString(userId), commentId, input);
        return ResponseHelper.toResponse(result);
    }

    @DeleteMapping("/{commentId}")
    @Operation(summary = "Delete a comment", description = "Deletes a comment from a card.")
    @ApiResponse(responseCode = "200", description = "Comment deleted successfully")
    public ResponseEntity<?> DeleteComment(
            @Parameter(hidden = true) @AuthenticationPrincipal String userId,
            @PathVariable UUID commentId
    ) {
        var result = deleteCommentCommand.execute(UUID.fromString(userId), commentId);
        return ResponseHelper.toResponse(result);
    }

    @GetMapping("/card/{cardId}")
    @Operation(summary = "Get comments by card", description = "Retrieves all comments for a specific card.")
    @ApiResponse(responseCode = "200", description = "List of comments", content = @Content(schema = @Schema(implementation = CommentDto.class)))
    public ResponseEntity<?> GetCommentsByCard(
            @Parameter(hidden = true) @AuthenticationPrincipal String userId,
            @PathVariable UUID cardId
    ) {
        var result = getCommentsByCardQuery.execute(cardId, UUID.fromString(userId));
        return ResponseHelper.toResponse(result);
    }
}
