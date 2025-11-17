package com.pck4x.task_manager.modules.user.controllers.admin;

import com.pck4x.task_manager.modules.user.application.dtos.input.RegisterUserDto;
import com.pck4x.task_manager.modules.user.application.use_cases.input_port.command.IRegisterAdminUserInputPort;
import com.pck4x.task_manager.modules.user.application.use_cases.output_port.command.IRegisterAdminUserOutputPort;
import com.pck4x.task_manager.shared.helper.ResponseHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@Tag(name = "System administrator user management")
@RequiredArgsConstructor
public class UserCommandController {
    private final IRegisterAdminUserInputPort inputPort;
    private final IRegisterAdminUserOutputPort outputPort;

    @PostMapping("/register")
    @Operation(summary = "Create a new user", description = "Add a new user to the system")
    public ResponseEntity<?> RegisterUser(@RequestBody RegisterUserDto input) {
        inputPort.Handle(input);
        return ResponseHelper.toResponse(outputPort);
    }

}
