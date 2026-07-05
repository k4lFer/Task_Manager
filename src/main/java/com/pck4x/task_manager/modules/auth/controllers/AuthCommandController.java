package com.pck4x.task_manager.modules.auth.controllers;

import com.pck4x.task_manager.modules.auth.interfaces.services.ICookieService;
import com.pck4x.task_manager.modules.auth.objects.dtos.command.RegisterUserDto;
import com.pck4x.task_manager.modules.auth.objects.dtos.command.SignInDto;
import com.pck4x.task_manager.modules.auth.objects.dtos.output.SignInOutDto;
import com.pck4x.task_manager.modules.auth.use_cases.command.RefreshTokenCommand;
import com.pck4x.task_manager.modules.auth.use_cases.command.RegisterUserCommand;
import com.pck4x.task_manager.modules.auth.use_cases.command.SignInCommand;
import com.pck4x.task_manager.shared.helper.ResponseHelper;
import com.pck4x.task_manager.shared.result.OutputPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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
    @Operation(summary = "Register a new user", description = "Creates a new user account and returns JWT tokens")
    @ApiResponse(responseCode = "200", description = "User registered successfully", content = @Content(schema = @Schema(implementation = SignInOutDto.class)))
    public ResponseEntity<?> Register(@RequestBody RegisterUserDto input){
        var result = registerUserCommand.execute(input);
        return ResponseHelper.toResponse(result);
    }

    @PostMapping("/login")
    @Operation(summary = "Sign in", description = "Authenticates a user and returns JWT tokens")
    @ApiResponse(responseCode = "200", description = "Login successful", content = @Content(schema = @Schema(implementation = SignInOutDto.class)))
    public ResponseEntity<?> Login(@RequestBody SignInDto input, HttpServletResponse response){
        var result = signInCommand.execute(input);
        if (result.isSuccess() && result.getData() != null) {
            cookieService.setRefreshTokenCookie(
                    response,
                    result.getData().refreshToken()
            );
        }
        return ResponseHelper.toResponse(result);
    }

    @PostMapping("/logout")
    @Operation(summary = "Logout", description = "Clears the refresh token cookie")
    @ApiResponse(responseCode = "200", description = "Logout successful")
    public ResponseEntity<?> Logout(HttpServletResponse response){
        cookieService.removeRefreshTokenCookie(response);
        return ResponseHelper.toResponse(
                OutputPort.success(null, HttpStatus.OK, "Logout successful")
        );
    }


    @PostMapping("/refresh-token")
    @Operation(summary = "Refresh access token", description = "Uses the refresh token cookie to issue a new access token")
    @ApiResponse(responseCode = "200", description = "Token refreshed successfully", content = @Content(schema = @Schema(implementation = SignInOutDto.class)))
    public ResponseEntity<?> RefreshToken(
            @CookieValue(name = "refresh_token", required = false) String refreshToken
    ) {
        var result = refreshTokenCommand.execute(refreshToken);
        return ResponseHelper.toResponse(result);
    }

}
