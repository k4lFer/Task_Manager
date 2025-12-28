package com.pck4x.task_manager.modules.auth.use_cases.handlers;

import com.pck4x.task_manager.modules.auth.interfaces.services.IJwtService;
import com.pck4x.task_manager.modules.auth.use_cases.command.RefreshTokenCommand;
import com.pck4x.task_manager.shared.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RefreshTokenCommandHandler implements RefreshTokenCommand {
    private final IJwtService jwtService;

    @Override
    public Result<String> execute(String input) {
        if (input == null || input.isBlank()) {
            return Result.error("Refresh token is required");
        }

        var newToken = jwtService.generateAccessTokenByRefreshToken(input);

        if (newToken.isEmpty()) {
            return Result.error("Invalid or expired refresh token");
        }

        return Result.success(newToken, "Generated access token");
    }
}
