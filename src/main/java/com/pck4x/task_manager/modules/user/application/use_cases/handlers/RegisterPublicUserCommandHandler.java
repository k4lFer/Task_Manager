package com.pck4x.task_manager.modules.user.application.use_cases.handlers;

import com.pck4x.task_manager.modules.user.application.events.UserRegisteredEvent;
import com.pck4x.task_manager.modules.user.application.use_cases.command.RegisterPublicUserCommand;
import com.pck4x.task_manager.modules.user.domain.models.TUser;
import com.pck4x.task_manager.modules.user.domain.repository.IUserRepository;
import com.pck4x.task_manager.shared.application.interfaces.IHandler;
import com.pck4x.task_manager.shared.result.Result;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegisterPublicUserCommandHandler implements IHandler<RegisterPublicUserCommand, Result> {
    private final IUserRepository userRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public Result Execute(RegisterPublicUserCommand input) {
        var user = TUser.Create(
                input.input().getFirstName(),
                input.input().getLastName(),
                input.input().getBirthDate()
        );

        var saved = userRepository.Create(user);

        if (saved != null) {
            eventPublisher.publishEvent(new UserRegisteredEvent(
                            user.getId(),
                            input.input().getUserName(),
                            input.input().getPassword()
                    )
            );
            return Result.create(user.getId(), "User registered successfully");
        }

        return Result.notFound("User not registered");
    }
}
