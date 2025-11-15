package com.pck4x.task_manager.modules.auth.controllers;

import com.pck4x.task_manager.modules.auth.application.dtos.input.SignInInput;
import com.pck4x.task_manager.modules.auth.application.use_cases.command.SignInCommand;
import com.pck4x.task_manager.modules.auth.application.use_cases.handlers.SignInCommandHandler;
import com.pck4x.task_manager.shared.helper.ResponseHelper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "")
@AllArgsConstructor
public class AuthController {
    private final SignInCommandHandler handler;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody SignInInput input){
        var command = new SignInCommand(input);
        var result = handler.Execute(command);
        return ResponseHelper.toResponse(result);
    }

}

