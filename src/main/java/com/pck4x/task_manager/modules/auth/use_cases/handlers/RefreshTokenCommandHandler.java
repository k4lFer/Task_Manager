package com.pck4x.task_manager.modules.auth.use_cases.handlers;

import com.pck4x.task_manager.modules.auth.interfaces.services.IJwtService;
import com.pck4x.task_manager.modules.auth.use_cases.command.RefreshTokenCommand;
import com.pck4x.task_manager.shared.result.OutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RefreshTokenCommandHandler implements RefreshTokenCommand {
    private final IJwtService jwtService;

    @Override
    public OutputPort<String> execute(String input) {
        if (input == null || input.isBlank()) {
            return OutputPort.failure(HttpStatus.BAD_REQUEST,"Refresh token is required");
        }

        var newToken = jwtService.generateAccessTokenByRefreshToken(input);

        if (newToken.isEmpty()) {
            return OutputPort.failure(HttpStatus.BAD_REQUEST,"Invalid or expired refresh token");
        }

        return OutputPort.success(newToken, HttpStatus.OK, "Generated access token");
    }
}
