package com.pck4x.task_manager.modules.user.controllers;

import com.pck4x.task_manager.modules.user.application.dtos.input.RegisterUserInput;
import com.pck4x.task_manager.modules.user.application.use_cases.command.RegisterUserCommand;
import com.pck4x.task_manager.modules.user.application.use_cases.handlers.RegisterUserCommandHandler;
import com.pck4x.task_manager.shared.helper.ResponseHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserCommandController {
    private final RegisterUserCommandHandler handler;

    @PostMapping("/register")
    public ResponseEntity<?> RegisterUser(@RequestBody RegisterUserInput input) {
        var command = new RegisterUserCommand(input);

        var result = handler.Execute(command);

        return ResponseHelper.toResponse(result);
    }

    /*
    @PostMapping("/update-my-profile")
    public ResponseEntity<?> UpdateMyProfile(@RequestBody UpdateMyProfileInput input) {

        var command = new UpdateMyProfileCommand(id, input);
        var result = handler.Execute(command);
        return ResponseHelper.toResponse(result);
    }*/
}
