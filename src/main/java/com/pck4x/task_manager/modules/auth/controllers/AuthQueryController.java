package com.pck4x.task_manager.modules.auth.controllers;

import com.pck4x.task_manager.modules.auth.use_cases.query.GetMyProfileQuery;
import com.pck4x.task_manager.shared.helper.ResponseHelper;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/user")
@Tag(name = "User")
@AllArgsConstructor
public class AuthQueryController {
    private final GetMyProfileQuery getMyProfileQuery;

    @GetMapping("/me")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> MyProfile(
            @Parameter(hidden = true) @AuthenticationPrincipal String userId
    ) {
        var result = getMyProfileQuery.execute(UUID.fromString(userId));
        return ResponseHelper.toResponse(result);
    }
}
