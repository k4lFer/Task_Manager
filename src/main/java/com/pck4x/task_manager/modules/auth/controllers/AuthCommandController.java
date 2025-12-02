package com.pck4x.task_manager.modules.auth.controllers;

import com.pck4x.task_manager.modules.auth.objects.dtos.command.RegisterUserDto;
import com.pck4x.task_manager.modules.auth.use_cases.command.RegisterUserCommand;
import com.pck4x.task_manager.shared.helper.ResponseHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "")
@AllArgsConstructor
public class AuthCommandController {
    private final RegisterUserCommand registerUserCommand;


    @PostMapping("/register")
    @Operation(summary = "", description = "")
    public ResponseEntity<?> Register(@RequestBody RegisterUserDto input){
        var result = registerUserCommand.execute(input);
        return ResponseHelper.toResponse(result);
    }

}
