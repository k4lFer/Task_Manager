package com.pck4x.task_manager.modules.auth.use_cases.handlers;

import com.pck4x.task_manager.modules.auth.interfaces.repositories.IUserRepository;
import com.pck4x.task_manager.modules.auth.interfaces.services.IJwtService;
import com.pck4x.task_manager.modules.auth.objects.dtos.command.SignInDto;
import com.pck4x.task_manager.modules.auth.objects.dtos.output.SignInOutDto;
import com.pck4x.task_manager.modules.auth.use_cases.command.SignInCommand;
import com.pck4x.task_manager.shared.result.OutputPort;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SignInCommandHandler implements SignInCommand {
    private final IUserRepository userRepository;
    private final IJwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public OutputPort<SignInOutDto> execute(SignInDto input) {
        var userOptional = userRepository.findSignInUserByUsername(input.getUsername());

        if (userOptional.isEmpty()) {
            return OutputPort.failure(HttpStatus.BAD_REQUEST, "Invalid credentials");
        }

        var user = userOptional.get();

        if (!passwordEncoder.matches(input.getPassword(), user.password())) {
            return OutputPort.failure(HttpStatus.BAD_REQUEST, "Invalid credentials");
        }

        var accessToken = jwtService.generateAccessToken(user.id());
        var refreshToken = jwtService.generateRefreshToken(user.id());

        var output = new SignInOutDto(
                user.id(),
                user.username(),
                user.firstName(),
                user.lastName(),
                accessToken,
                refreshToken
        );
        return OutputPort.success(output, HttpStatus.OK, "Login Successful");
    }
}
