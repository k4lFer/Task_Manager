package com.pck4x.task_manager.modules.board.controllers;

import com.pck4x.task_manager.modules.board.objects.dtos.command.AddBoardMemberDto;
import com.pck4x.task_manager.modules.board.objects.dtos.command.CreateBoardDto;
import com.pck4x.task_manager.modules.board.objects.dtos.command.CreateLabelDto;
import com.pck4x.task_manager.modules.board.objects.dtos.command.CreateListDto;
import com.pck4x.task_manager.modules.board.objects.dtos.command.UpdateBoardDto;
import com.pck4x.task_manager.modules.board.objects.dtos.command.UpdateBoardMemberDto;
import com.pck4x.task_manager.modules.board.use_cases.command.AddBoardMemberCommand;
import com.pck4x.task_manager.modules.board.use_cases.command.CreateBoardCommand;
import com.pck4x.task_manager.modules.board.use_cases.command.CreateLabelCommand;
import com.pck4x.task_manager.modules.board.use_cases.command.CreateListCommand;
import com.pck4x.task_manager.modules.board.use_cases.command.DeleteBoardCommand;
import com.pck4x.task_manager.modules.board.use_cases.command.RemoveBoardMemberCommand;
import com.pck4x.task_manager.modules.board.use_cases.command.UpdateBoardCommand;
import com.pck4x.task_manager.modules.board.use_cases.command.UpdateBoardMemberCommand;
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
@RequestMapping("/board")
@Tag(name = "Boards")
@SecurityRequirement(name = "bearerAuth")
@AllArgsConstructor
public class BoardCommandController {
    private final CreateBoardCommand createBoardCommand;
    private final UpdateBoardCommand updateBoardCommand;
    private final DeleteBoardCommand deleteBoardCommand;
    private final AddBoardMemberCommand addBoardMemberCommand;
    private final RemoveBoardMemberCommand removeBoardMemberCommand;
    private final UpdateBoardMemberCommand updateBoardMemberCommand;
    private final CreateLabelCommand createLabelCommand;
    private final CreateListCommand createListCommand;

    @PostMapping("/workspace/{workspaceId}/create")
    @Operation(
            summary = "Create a new board",
            description = "Creates a new board within a workspace. The authenticated user becomes the owner."
    )
    @ApiResponse(responseCode = "200", description = "Board created successfully", content = @Content(schema = @Schema(implementation = UUID.class)))
    public ResponseEntity<?> Create(
            @Parameter(hidden = true) @AuthenticationPrincipal String userId,
            @PathVariable UUID workspaceId,
            @RequestBody CreateBoardDto input
    ){
        var result = createBoardCommand.execute(UUID.fromString(userId), workspaceId, input);
        return ResponseHelper.toResponse(result);
    }

    @PatchMapping("/{boardId}")
    @Operation(
            summary = "Update a board",
            description = "Updates the properties of an existing board."
    )
    @ApiResponse(responseCode = "200", description = "Board updated successfully")
    public ResponseEntity<?> Update(
            @Parameter(hidden = true) @AuthenticationPrincipal String userId,
            @PathVariable UUID boardId,
            @RequestBody UpdateBoardDto input
    ){
        var result = updateBoardCommand.execute(UUID.fromString(userId), boardId, input);
        return ResponseHelper.toResponse(result);
    }

    @DeleteMapping("/{boardId}")
    @Operation(
            summary = "Delete a board",
            description = "Deletes a board and all its contents."
    )
    @ApiResponse(responseCode = "200", description = "Board deleted successfully")
    public ResponseEntity<?> Delete(
            @Parameter(hidden = true) @AuthenticationPrincipal String userId,
            @PathVariable UUID boardId
    ){
        var result = deleteBoardCommand.execute(UUID.fromString(userId), boardId);
        return ResponseHelper.toResponse(result);
    }

    @PostMapping("/{boardId}/members")
    @Operation(
            summary = "Add board member",
            description = "Adds a member to the board with a specified role."
    )
    @ApiResponse(responseCode = "200", description = "Member added successfully", content = @Content(schema = @Schema(implementation = UUID.class)))
    public ResponseEntity<?> AddMember(
            @Parameter(hidden = true) @AuthenticationPrincipal String userId,
            @PathVariable UUID boardId,
            @RequestBody AddBoardMemberDto input
    ){
        var result = addBoardMemberCommand.execute(UUID.fromString(userId), boardId, input);
        return ResponseHelper.toResponse(result);
    }

    @PatchMapping("/{boardId}/members/{memberId}/role")
    @Operation(
            summary = "Update board member role",
            description = "Updates the role of a board member."
    )
    @ApiResponse(responseCode = "200", description = "Role updated successfully")
    public ResponseEntity<?> UpdateMemberRole(
            @Parameter(hidden = true) @AuthenticationPrincipal String userId,
            @PathVariable UUID boardId,
            @PathVariable UUID memberId,
            @RequestBody UpdateBoardMemberDto input
    ){
        var result = updateBoardMemberCommand.execute(UUID.fromString(userId), boardId, memberId, input);
        return ResponseHelper.toResponse(result);
    }

    @DeleteMapping("/{boardId}/members/{memberId}")
    @Operation(
            summary = "Remove board member",
            description = "Removes a member from the board."
    )
    @ApiResponse(responseCode = "200", description = "Member removed successfully")
    public ResponseEntity<?> RemoveMember(
            @Parameter(hidden = true) @AuthenticationPrincipal String userId,
            @PathVariable UUID boardId,
            @PathVariable UUID memberId
    ){
        var result = removeBoardMemberCommand.execute(UUID.fromString(userId), boardId, memberId);
        return ResponseHelper.toResponse(result);
    }

    @PostMapping("/{boardId}/labels")
    @Operation(
            summary = "Create a label for a board",
            description = "Creates a new label within a board."
    )
    @ApiResponse(responseCode = "201", description = "Label created successfully", content = @Content(schema = @Schema(implementation = UUID.class)))
    public ResponseEntity<?> CreateLabel(
            @Parameter(hidden = true) @AuthenticationPrincipal String userId,
            @PathVariable UUID boardId,
            @RequestBody CreateLabelDto input
    ){
        var result = createLabelCommand.execute(UUID.fromString(userId), boardId, input);
        return ResponseHelper.toResponse(result);
    }

    @PostMapping("/{boardId}/lists")
    @Operation(
            summary = "Create a list on a board",
            description = "Creates a new list (column) on the board."
    )
    @ApiResponse(responseCode = "201", description = "List created successfully", content = @Content(schema = @Schema(implementation = UUID.class)))
    public ResponseEntity<?> CreateList(
            @Parameter(hidden = true) @AuthenticationPrincipal String userId,
            @PathVariable UUID boardId,
            @RequestBody CreateListDto input
    ){
        var result = createListCommand.execute(UUID.fromString(userId), boardId, input);
        return ResponseHelper.toResponse(result);
    }
}
