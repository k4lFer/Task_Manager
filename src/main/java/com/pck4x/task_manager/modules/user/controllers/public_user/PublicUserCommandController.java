package com.pck4x.task_manager.modules.user.controllers.public_user;

import com.pck4x.task_manager.modules.user.application.dtos.input.PublicRegisterUserInput;
import com.pck4x.task_manager.modules.user.application.use_cases.command.RegisterPublicUserCommand;
import com.pck4x.task_manager.modules.user.application.use_cases.handlers.RegisterPublicUserCommandHandler;
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
    private final RegisterPublicUserCommandHandler handler;

    @PostMapping("/auth/register")
    public ResponseEntity<?> publicRegister(@RequestBody PublicRegisterUserInput input) {
        var command = new RegisterPublicUserCommand(input);
        var result = handler.Execute(command);
        return ResponseHelper.toResponse(result);
    }


}
