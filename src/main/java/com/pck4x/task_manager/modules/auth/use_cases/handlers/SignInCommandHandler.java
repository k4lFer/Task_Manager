package com.pck4x.task_manager.modules.auth.use_cases.handlers;

import com.pck4x.task_manager.modules.auth.interfaces.repositories.IUserRepository;
import com.pck4x.task_manager.modules.auth.interfaces.services.IJwtService;
import com.pck4x.task_manager.modules.auth.objects.dtos.command.SignInDto;
import com.pck4x.task_manager.modules.auth.objects.dtos.output.SignInOutDto;
import com.pck4x.task_manager.modules.auth.use_cases.command.SignInCommand;
import com.pck4x.task_manager.shared.result.Result;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SignInCommandHandler implements SignInCommand {
    private final IUserRepository userRepository;
    private final IJwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Result<SignInOutDto> execute(SignInDto input) {
        var userOptional = userRepository.findByUsername(input.getUsername());

        if (userOptional.isEmpty()) {
            return Result.notFound("Invalid credentials");
        }

        var user = userOptional.get();

        if (!passwordEncoder.matches(input.getPassword(), user.getPassword())) {
            return Result.notFound("Invalid credentials");
        }

        var accessToken = jwtService.generateAccessToken(user.getId());
        var refreshToken = jwtService.generateRefreshToken(user.getId());

        SignInOutDto output = SignInOutDto.builder()
                .id(user.getId())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();


        return Result.success(output, "Login Successful");
    }
}
