package com.pck4x.task_manager.modules.chat.controllers;

import com.pck4x.task_manager.modules.chat.objects.dtos.command.CreateChatChannelDto;
import com.pck4x.task_manager.modules.chat.use_cases.command.CreateChatChannelCommand;
import com.pck4x.task_manager.shared.helper.ResponseHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/chat")
@Tag(name = "Chat")
@SecurityRequirement(name = "bearerAuth")
@AllArgsConstructor
public class ChatChannelCommandController {
    private final CreateChatChannelCommand createChatChannelCommand;

    @PostMapping("/create-channel")
    @Operation(summary = "", description = "")
    public ResponseEntity<?> Create(
            @Parameter(hidden = true) @AuthenticationPrincipal String userId,
            @RequestBody CreateChatChannelDto input
    ){
        var result = createChatChannelCommand.execute(UUID.fromString(userId), input);
        return ResponseHelper.toResponse(result);
    }
}
