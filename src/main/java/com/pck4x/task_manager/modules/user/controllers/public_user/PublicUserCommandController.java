package com.pck4x.task_manager.modules.user.controllers.public_user;

import com.pck4x.task_manager.modules.user.application.dtos.input.RegisterPublicUserDto;
import com.pck4x.task_manager.modules.user.application.use_cases.input_port.command.IRegisterPublicUserInputPort;
import com.pck4x.task_manager.modules.user.application.use_cases.output_port.command.IRegisterPublicUserOutputPort;
import com.pck4x.task_manager.shared.helper.ResponseHelper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Tag(name = "Public User Management")
@RequiredArgsConstructor
public class PublicUserCommandController {
    private final IRegisterPublicUserOutputPort outputPort;
    private final IRegisterPublicUserInputPort inputPort;

    @PostMapping("/auth/register")
    public ResponseEntity<?> publicRegister(@RequestBody RegisterPublicUserDto input) {
        inputPort.Handle(input);
        return ResponseHelper.toResponse(outputPort);
    }


}
