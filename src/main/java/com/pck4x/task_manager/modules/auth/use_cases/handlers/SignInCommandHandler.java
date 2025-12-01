package com.pck4x.task_manager.modules.auth.use_cases.handlers;

import com.pck4x.task_manager.modules.auth.interfaces.repositories.IUserRepository;
import com.pck4x.task_manager.modules.auth.objects.dtos.command.SignInDto;
import com.pck4x.task_manager.modules.auth.objects.dtos.output.SignInOutDto;
import com.pck4x.task_manager.modules.auth.use_cases.command.SignInCommand;
import com.pck4x.task_manager.shared.result.Result;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SignInCommandHandler implements SignInCommand {
    private final IUserRepository userRepository;

    @Override
    public Result<SignInOutDto> execute(SignInDto input) {
        var user = userRepository.findByUsername(input.getUsername());
        if (user.isEmpty()) return Result.notFound("User not found");


        return null;
    }
}
