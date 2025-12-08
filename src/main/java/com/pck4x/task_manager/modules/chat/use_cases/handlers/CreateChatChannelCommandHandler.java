package com.pck4x.task_manager.modules.chat.use_cases.handlers;

import com.pck4x.task_manager.modules.chat.domain.models.TChatChannel;
import com.pck4x.task_manager.modules.chat.interfaces.acl.IChatPermissionService;
import com.pck4x.task_manager.modules.chat.interfaces.repositories.IChatChannelRepository;
import com.pck4x.task_manager.modules.chat.objects.dtos.command.CreateChatChannelDto;
import com.pck4x.task_manager.modules.chat.use_cases.command.CreateChatChannelCommand;
import com.pck4x.task_manager.shared.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CreateChatChannelCommandHandler implements CreateChatChannelCommand {
    private final IChatChannelRepository chatChannelRepository;
    private final IChatPermissionService chatPermissionService;

    @Override
    public Result<UUID> execute(UUID ownerId, CreateChatChannelDto input) {

        if (!chatPermissionService.canCreateChannel(input.getWorkspaceId(), ownerId)) {
            return Result.error("Only Workspace Owner or Admins can create channels");
        }

        var chatChannel = TChatChannel.create(
                input.getWorkspaceId(),
                input.getName(),
                input.description);

        var saved = chatChannelRepository.save(chatChannel);

        if (saved != null) {
            return Result.create(saved.getId(), "Channel registered successfully");
        }

        return Result.error("Channel not registered");

    }
}
