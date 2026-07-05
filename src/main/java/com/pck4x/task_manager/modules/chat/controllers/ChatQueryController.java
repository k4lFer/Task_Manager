package com.pck4x.task_manager.modules.chat.controllers;

import com.pck4x.task_manager.modules.chat.objects.dtos.query.ChatMessageResponseDto;
import com.pck4x.task_manager.modules.chat.objects.dtos.query.WorkspaceChannelDto;
import com.pck4x.task_manager.modules.chat.use_cases.query.GetChannelMessagesQuery;
import com.pck4x.task_manager.modules.chat.use_cases.query.GetWorkspaceChannelsQuery;
import com.pck4x.task_manager.shared.helper.ResponseHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Chat Query", description = "Operations for retrieving chat data")
public class ChatQueryController {
    private final GetChannelMessagesQuery getChannelMessagesQuery;
    private final GetWorkspaceChannelsQuery getWorkspaceChannelsQuery;

    @GetMapping("/workspaces/{id}/channels")
    @Operation(summary = "Get Workspace Channels", description = "Retrieves a list of channels for a specific workspace")
    @ApiResponse(responseCode = "200", description = "List of channels", content = @Content(schema = @Schema(implementation = WorkspaceChannelDto.class)))
    public ResponseEntity<?> getWorkspaceChannels(
            @PathVariable UUID id,
            @ParameterObject Pageable pageable
    ) {
        var result = getWorkspaceChannelsQuery.execute(id, pageable);
        return ResponseHelper.toResponse(result);
    }


    @GetMapping("/{channelId}/messages")
    @Operation(summary = "Get Channel Messages", description = "Retrieves a paginated list of messages for a specific channel")
    @ApiResponse(responseCode = "200", description = "Paginated list of messages", content = @Content(schema = @Schema(implementation = ChatMessageResponseDto.class)))
    public ResponseEntity<?> getChannelMessages(
            @PathVariable UUID channelId,
            @ParameterObject Pageable pageable) {
        var result = getChannelMessagesQuery.execute(channelId, pageable);
        return ResponseHelper.toResponse(result);
    }
}
