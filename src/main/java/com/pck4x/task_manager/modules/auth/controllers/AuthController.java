package com.pck4x.task_manager.modules.auth.controllers;

import com.pck4x.task_manager.modules.auth.application.dtos.input.SignInInputDto;
import com.pck4x.task_manager.modules.auth.application.use_cases.input_port.command.ISignInInputPort;
import com.pck4x.task_manager.modules.auth.application.use_cases.output_port.command.ISignInOutputPort;
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
    private final ISignInOutputPort outputPort;
    private final ISignInInputPort inputPort;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody SignInInputDto input){
        inputPort.Handle(input);
        return ResponseHelper.toResponse(outputPort);
    }

}

