package com.pck4x.task_manager.modules.auth.use_cases.handlers;

import com.pck4x.task_manager.modules.auth.domain.models.TUser;
import com.pck4x.task_manager.modules.auth.interfaces.repositories.IUserRepository;
import com.pck4x.task_manager.modules.auth.objects.dtos.command.RegisterUserDto;
import com.pck4x.task_manager.modules.auth.use_cases.command.RegisterUserCommand;
import com.pck4x.task_manager.modules.auth.use_cases.event.UserCreatedEvent;
import com.pck4x.task_manager.shared.result.OutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
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
    public OutputPort<UUID> execute(RegisterUserDto input) {
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
            return OutputPort.success(user.getId(), HttpStatus.CREATED,"User registered successfully");
        }

        return OutputPort.failure(HttpStatus.BAD_REQUEST,"User not registered");

    }
}
