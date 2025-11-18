package com.pck4x.task_manager.modules.auth.application.use_cases.interactor;

import com.pck4x.task_manager.modules.auth.application.dtos.input.SignInInputDto;
import com.pck4x.task_manager.modules.auth.application.dtos.output.SignInOutputDto;
import com.pck4x.task_manager.modules.auth.application.use_cases.input_port.command.ISignInInputPort;
import com.pck4x.task_manager.modules.auth.application.use_cases.output_port.command.ISignInOutputPort;
import com.pck4x.task_manager.modules.auth.domain.repository.IAuthRepository;
import com.pck4x.task_manager.shared.application.output_port.IHandleFailure;
import com.pck4x.task_manager.shared.application.output_port.IHandleSuccess;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class SignInInteractor implements ISignInInputPort {

    private final IAuthRepository authRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ISignInOutputPort output;

    @Override
    public void Handle(SignInInputDto input) {

        var user = authRepository.GetByUsername(input.getUserName());
        if (user == null) {
            output.HandleFailure(IHandleFailure.Fail("The user does not exist or their credentials are invalid.", HttpStatus.BAD_REQUEST));
            return;
        }

        if (!passwordEncoder.matches(input.getPassword(), user.getPassword())) {
            output.HandleFailure(IHandleFailure.Fail("Your credentials are invalid.", HttpStatus.BAD_REQUEST));
            return;
        }

        /*
        *
        * payload, contiene id de momento...
        *
        * Generar tokens
        * var accessToken = jwt.GenerateAccessToken(payload);
        * var refreshToken = jwt.GenerateRefreshToken(payload);
        * */

        var dto = new SignInOutputDto(
                user.getId(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString()
        );

        output.HandleSuccess(IHandleSuccess.HandleSuccess(dto, HttpStatus.OK, "Login successfully"));
    }
}
