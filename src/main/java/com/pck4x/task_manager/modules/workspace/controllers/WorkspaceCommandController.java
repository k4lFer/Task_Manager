package com.pck4x.task_manager.modules.workspace.controllers;

import com.pck4x.task_manager.modules.workspace.objects.dtos.command.AddMemberDto;
import com.pck4x.task_manager.modules.workspace.use_cases.command.AddMemberCommand;
import com.pck4x.task_manager.shared.helper.ResponseHelper;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/workspace")
@Tag(name = "Workspace")
@SecurityRequirement(name = "bearerAuth")
@AllArgsConstructor
public class WorkspaceCommandController {
    private final AddMemberCommand addMemberCommand;

    @PostMapping("/add-member")
    public ResponseEntity<?> AddMember(
            @Parameter(hidden = true) @AuthenticationPrincipal String userId,
            @RequestBody AddMemberDto input
        ){
        var result = addMemberCommand.execute(UUID.fromString(userId), input);
        return ResponseHelper.toResponse(result);
    }

}
