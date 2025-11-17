package com.pck4x.task_manager.modules.user.application.use_cases.interactor.command;

import com.pck4x.task_manager.modules.user.application.dtos.input.RegisterPublicUserDto;
import com.pck4x.task_manager.modules.user.application.events.UserRegisteredEvent;
import com.pck4x.task_manager.modules.user.application.use_cases.input_port.command.IRegisterPublicUserInputPort;
import com.pck4x.task_manager.modules.user.application.use_cases.output_port.command.IRegisterPublicUserOutputPort;
import com.pck4x.task_manager.modules.user.domain.models.TUser;
import com.pck4x.task_manager.modules.user.domain.repository.IUserRepository;
import com.pck4x.task_manager.shared.application.output_port.IHandleFailure;
import com.pck4x.task_manager.shared.application.output_port.IHandleSuccess;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegisterPublicUserInteractor implements IRegisterPublicUserInputPort {
    private final IUserRepository userRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final IRegisterPublicUserOutputPort output;

    @Override
    public void Handle(RegisterPublicUserDto input) {
        var user = TUser.Create(
                input.getFirstName(),
                input.getLastName(),
                input.getBirthDate()
        );

        var saved = userRepository.Create(user);

        if (saved == null) {
            output.HandleFailure(IHandleFailure.Fail("User not registered", HttpStatus.BAD_REQUEST));
            return;
        }

        eventPublisher.publishEvent(
                new UserRegisteredEvent(
                        user.getId(),
                        input.getUserName(),
                        input.getPassword()
                )
        );

        output.HandleSuccess(IHandleSuccess.HandleSuccess(user.getId(), HttpStatus.CREATED, "User created"));

    }
}
