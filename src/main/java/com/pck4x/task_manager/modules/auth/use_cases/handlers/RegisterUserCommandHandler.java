package com.pck4x.task_manager.modules.auth.use_cases.handlers;

import com.pck4x.task_manager.modules.auth.domain.models.TPerson;
import com.pck4x.task_manager.modules.auth.domain.models.TUser;
import com.pck4x.task_manager.modules.auth.interfaces.repositories.IPersonRepository;
import com.pck4x.task_manager.modules.auth.interfaces.repositories.IUserRepository;
import com.pck4x.task_manager.modules.auth.objects.dtos.command.RegisterUserDto;
import com.pck4x.task_manager.modules.auth.use_cases.command.RegisterUserCommand;
import com.pck4x.task_manager.modules.auth.use_cases.event.UserCreatedEvent;
import com.pck4x.task_manager.shared.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RegisterUserCommandHandler implements RegisterUserCommand {
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    @Transactional
    public Result<UUID> execute(RegisterUserDto input) {
        TUser user = TUser.create(
                input.getFirstName(),
                input.getLastName(),
                input.getBirthDate(),
                input.getUsername(),
                input.getEmail(),
                passwordEncoder.encode(input.getPassword())
        );

        var saved = userRepository.save(user);

        if (saved != null) {
            eventPublisher.publishEvent(
                    new UserCreatedEvent(
                            user.getId(),
                            user.getCreatedAt()
                    )
            );
            return Result.create(user.getId(), "User registered successfully");
        }

        return Result.notFound("User not registered");

    }
}
