package com.pck4x.task_manager.modules.auth.controllers;

import com.pck4x.task_manager.modules.auth.interfaces.services.ICookieService;
import com.pck4x.task_manager.modules.auth.objects.dtos.command.RegisterUserDto;
import com.pck4x.task_manager.modules.auth.objects.dtos.command.SignInDto;
import com.pck4x.task_manager.modules.auth.use_cases.command.RefreshTokenCommand;
import com.pck4x.task_manager.modules.auth.use_cases.command.RegisterUserCommand;
import com.pck4x.task_manager.modules.auth.use_cases.command.SignInCommand;
import com.pck4x.task_manager.shared.helper.ResponseHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth")
@AllArgsConstructor
public class AuthCommandController {
    private final RegisterUserCommand registerUserCommand;
    private final SignInCommand signInCommand;
    private final ICookieService cookieService;
    private final RefreshTokenCommand refreshTokenCommand;

    @PostMapping("/register")
    @Operation(summary = "", description = "")
    public ResponseEntity<?> Register(@RequestBody RegisterUserDto input){
        var result = registerUserCommand.execute(input);
        return ResponseHelper.toResponse(result);
    }

    @PostMapping("/login")
    @Operation(summary = "", description = "")
    public ResponseEntity<?> Login(@RequestBody SignInDto input, HttpServletResponse response){
        var result = signInCommand.execute(input);
        cookieService.setRefreshTokenCookie(response, result.getData().getRefreshToken());
        return ResponseHelper.toResponse(result);
    }

    @PostMapping("/refresh-token")
    @Operation(summary = "", description = "")
    public ResponseEntity<?> RefreshToken(
            @CookieValue(name = "refresh_token", required = false) String refreshToken
    ) {
        var result = refreshTokenCommand.execute(refreshToken);
        return ResponseHelper.toResponse(result);
    }

}
