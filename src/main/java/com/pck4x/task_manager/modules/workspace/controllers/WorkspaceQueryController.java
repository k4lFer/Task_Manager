package com.pck4x.task_manager.modules.workspace.controllers;

import com.pck4x.task_manager.modules.workspace.use_cases.query.GetAllMyWorkspacesQuery;
import com.pck4x.task_manager.shared.helper.ResponseHelper;
import com.pck4x.task_manager.shared.objects.QueryDto;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/workspace")
@Tag(name = "Workspace")
@AllArgsConstructor
public class WorkspaceQueryController {
    private final GetAllMyWorkspacesQuery getAllMyWorkspacesQuery;

    @GetMapping("/my-workspaces")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> GetMyWorkspaces(
            @Parameter(hidden = true) @AuthenticationPrincipal String userId,
            @Valid @ModelAttribute QueryDto options

            ){
        var result = getAllMyWorkspacesQuery.execute(UUID.fromString(userId), options);
        return ResponseHelper.toResponse(result);
    }
}
