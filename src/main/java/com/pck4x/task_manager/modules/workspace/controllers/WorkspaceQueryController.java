package com.pck4x.task_manager.modules.workspace.controllers;

import com.pck4x.task_manager.modules.workspace.use_cases.query.GetAllMyWorkspacesQuery;
import com.pck4x.task_manager.modules.workspace.use_cases.query.GetWorkspacesByIdQuery;
import com.pck4x.task_manager.modules.workspace.use_cases.query.SearchInvitableUsersQuery;
import com.pck4x.task_manager.shared.helper.ResponseHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/workspace")
@Tag(name = "Workspace")
@SecurityRequirement(name = "bearerAuth")
@AllArgsConstructor
public class WorkspaceQueryController {
    private final GetAllMyWorkspacesQuery getAllMyWorkspacesQuery;
    private final GetWorkspacesByIdQuery getWorkspacesByIdQuery;
    private final SearchInvitableUsersQuery searchInvitableUsersQuery;

    @GetMapping("/all-my-workspaces")
    @Operation(
            summary = "Get all user workspaces",
            description = "Returns a paginated list of all workspaces where the authenticated user is the owner or a member."
    )
    public ResponseEntity<?> GetMyWorkspaces(
            @Parameter(hidden = true) @AuthenticationPrincipal String userId,
            @ParameterObject Pageable pageable
        ){
        var result = getAllMyWorkspacesQuery.execute(UUID.fromString(userId), pageable);
        return ResponseHelper.toResponse(result);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get workspace details",
            description = "Retrieves detailed information about a specific workspace. The authenticated user must be the owner or a member of the workspace."
    )
    public ResponseEntity<?> GetById(
            @Parameter(hidden = true) @AuthenticationPrincipal String userId,
            @PathVariable UUID id
    ) {
        var result = getWorkspacesByIdQuery.execute(id, UUID.fromString(userId));
        return ResponseHelper.toResponse(result);
    }

    @GetMapping("/{id}/search-invitable-users")
    @Operation(
            summary = "Search users eligible for workspace invitation",
            description = "Searches for users that can be invited to the workspace. Excludes users who are already members or have pending invitations."
    )
    public ResponseEntity<?> SearchInvitable(
            @PathVariable UUID id,
            @RequestParam String query,
            @ParameterObject Pageable pageable
    ) {
        var result = searchInvitableUsersQuery.execute(id, query, pageable);
        return ResponseHelper.toResponse(result);
    }

    @GetMapping("/invitations/sent")
    @Operation(
            summary = "Get sent workspace invitations",
            description = "Returns a paginated list of workspace invitations sent by the authenticated user. Includes pending and expired invitations."
    )
    public ResponseEntity<?> InvitationsSent(
            @Parameter(hidden = true) @AuthenticationPrincipal String userId,
            @ParameterObject Pageable pageable
    ) {
        return ResponseEntity.ok().body("");
    }

    @GetMapping("/invitations/received")
    @Operation(
            summary = "Get received workspace invitations",
            description = "Returns a paginated list of workspace invitations received by the authenticated user. Invitations can be accepted or rejected."
    )
    public ResponseEntity<?> InvitationsReceived(
            @Parameter(hidden = true) @AuthenticationPrincipal String userId,
            @ParameterObject Pageable pageable
    ) {
        return ResponseEntity.ok().body("");
    }

}
