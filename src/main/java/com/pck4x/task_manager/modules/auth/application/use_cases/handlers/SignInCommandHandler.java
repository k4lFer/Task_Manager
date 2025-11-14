package com.pck4x.task_manager.modules.auth.application.use_cases.handlers;

import com.pck4x.task_manager.modules.auth.application.use_cases.command.SignInCommand;
import com.pck4x.task_manager.modules.auth.domain.repository.IAuthRepository;
import com.pck4x.task_manager.shared.application.interfaces.IHandler;
import com.pck4x.task_manager.shared.result.Result;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SignInCommandHandler implements IHandler<SignInCommand, Result> {
    private final IAuthRepository authRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public Result Execute(SignInCommand input) {
        var user = authRepository.GetByUsername(input.input().getUserName());

        if (user == null) return Result.notFound("User does not exist");

        if (passwordEncoder.matches(input.input().getPassword(), user.getPassword())) {
            // Generar el JWT, LLAMADA AL SERVICIO JWT


            return Result.success(user.getId(), "Login successfully");
        }

        return Result.error("Invalid credentials");
    }
}
