package com.pck4x.task_manager.modules.chat.use_cases.handlers;

import com.pck4x.task_manager.modules.chat.interfaces.acl.IChatPermissionService;
import com.pck4x.task_manager.modules.chat.interfaces.repositories.IChannelRepository;
import com.pck4x.task_manager.modules.chat.use_cases.command.DeleteChannelCommand;
import com.pck4x.task_manager.shared.result.OutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DeleteChannelCommandHandler implements DeleteChannelCommand {

    private final IChannelRepository channelRepository;
    private final IChatPermissionService chatPermissionService;

    @Override
    @Transactional
    public OutputPort<Void> execute(UUID userId, UUID channelId) {
        var channelOpt = channelRepository.findById(channelId);
        if (channelOpt.isEmpty()) {
            return OutputPort.failure(HttpStatus.NOT_FOUND, "Channel not found");
        }

        var channel = channelOpt.get();

        if (!chatPermissionService.canDeleteChannel(channel.getWorkspaceId(), userId)) {
            return OutputPort.failure(HttpStatus.FORBIDDEN, "Only workspace owner or admins can delete channels");
        }

        channelRepository.deleteById(channelId);
        return OutputPort.success(null, HttpStatus.OK, "Channel deleted successfully");
    }
}
