package com.pck4x.task_manager.modules.chat.controllers;

import com.pck4x.task_manager.modules.chat.objects.dtos.command.CreateChatChannelDto;
import com.pck4x.task_manager.modules.chat.objects.dtos.command.SendMessageDto;
import com.pck4x.task_manager.modules.chat.use_cases.command.CreateChannelCommand;
import com.pck4x.task_manager.modules.chat.use_cases.command.DeleteChannelCommand;
import com.pck4x.task_manager.modules.chat.use_cases.command.EditMessageCommand;
import com.pck4x.task_manager.modules.chat.use_cases.command.SendMessageCommand;
import com.pck4x.task_manager.shared.helper.ResponseHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
public class ChannelCommandController {
    private final CreateChannelCommand createChannelCommand;
    private final DeleteChannelCommand deleteChannelCommand;
    private final SendMessageCommand sendMessageCommand;
    private final EditMessageCommand editMessageCommand;

    @PostMapping("/create-channel")
    @Operation(summary = "", description = "")
    public ResponseEntity<?> Create(
            @Parameter(hidden = true) @AuthenticationPrincipal String userId,
            @RequestBody CreateChatChannelDto input) {
        var result = createChannelCommand.execute(UUID.fromString(userId), input);
        return ResponseHelper.toResponse(result);
    }

    @DeleteMapping("/{channelId}")
    @Operation(summary = "", description = "")
    public ResponseEntity<?> Delete(
            @Parameter(hidden = true) @AuthenticationPrincipal String userId,
            @PathVariable UUID channelId) {
        var result = deleteChannelCommand.execute(UUID.fromString(userId), channelId);
        return ResponseHelper.toResponse(result);
    }

    @PostMapping("/send-message")
    @Operation(summary = "", description = "")
    public ResponseEntity<?> Send(
            @Parameter(hidden = true) @AuthenticationPrincipal String userId,
            @RequestBody SendMessageDto input) {
        var result = sendMessageCommand.execute(UUID.fromString(userId), input);
        return ResponseHelper.toResponse(result);
    }

    @org.springframework.web.bind.annotation.PutMapping("/edit-message")
    @Operation(summary = "Edit a message", description = "Updates a message content if the user is the owner")
    public ResponseEntity<?> Edit(
            @Parameter(hidden = true) @AuthenticationPrincipal String userId,
            @RequestBody com.pck4x.task_manager.modules.chat.objects.dtos.command.EditMessageDto input) {
        var result = editMessageCommand.execute(UUID.fromString(userId), input);
        return ResponseHelper.toResponse(result);
    }
}
