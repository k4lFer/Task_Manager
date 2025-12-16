package com.pck4x.task_manager.modules.workspace.controllers;

import com.pck4x.task_manager.modules.workspace.use_cases.query.GetAllMyWorkspacesQuery;
import com.pck4x.task_manager.modules.workspace.use_cases.query.GetWorkspacesByIdQuery;
import com.pck4x.task_manager.shared.helper.ResponseHelper;
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

    @GetMapping("/all-my-workspaces")
    public ResponseEntity<?> GetMyWorkspaces(
            @Parameter(hidden = true) @AuthenticationPrincipal String userId,
            @ParameterObject Pageable pageable
        ){
        var result = getAllMyWorkspacesQuery.execute(UUID.fromString(userId), pageable);
        return ResponseHelper.toResponse(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> GetById(
            @Parameter(hidden = true) @AuthenticationPrincipal String userId,
            @PathVariable UUID id
    ) {
        var result = getWorkspacesByIdQuery.execute(id, UUID.fromString(userId));
        return ResponseHelper.toResponse(result);
    }

}
