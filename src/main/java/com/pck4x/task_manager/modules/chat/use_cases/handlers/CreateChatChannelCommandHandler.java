package com.pck4x.task_manager.modules.chat.use_cases.handlers;

import com.pck4x.task_manager.modules.chat.domain.models.TChatChannel;
import com.pck4x.task_manager.modules.chat.interfaces.acl.IChatPermissionService;
import com.pck4x.task_manager.modules.chat.interfaces.repositories.IChatChannelRepository;
import com.pck4x.task_manager.modules.chat.objects.dtos.command.CreateChatChannelDto;
import com.pck4x.task_manager.modules.chat.use_cases.command.CreateChatChannelCommand;
import com.pck4x.task_manager.shared.result.OutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CreateChatChannelCommandHandler implements CreateChatChannelCommand {
    private final IChatChannelRepository chatChannelRepository;
    private final IChatPermissionService chatPermissionService;

    @Override
    public OutputPort<UUID> execute(UUID ownerId, CreateChatChannelDto input) {

        if (!chatPermissionService.canCreateChannel(input.getWorkspaceId(), ownerId)) {
            return OutputPort.failure(HttpStatus.FORBIDDEN, "Only Workspace Owner or Admins can create channels");
        }

        var chatChannel = TChatChannel.create(
                input.getWorkspaceId(),
                input.getName(),
                input.description);

        var saved = chatChannelRepository.save(chatChannel);

        if (saved != null) {
            return OutputPort.success(saved.getId(), HttpStatus.CREATED, "Channel registered successfully");
        }

        return OutputPort.failure(HttpStatus.BAD_REQUEST, "Channel not registered");

    }
}
