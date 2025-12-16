package com.pck4x.task_manager.modules.chat.use_cases.handlers;

import com.pck4x.task_manager.modules.chat.domain.models.TChatMessage;
import com.pck4x.task_manager.modules.chat.interfaces.repositories.IChatChannelRepository;
import com.pck4x.task_manager.modules.chat.interfaces.repositories.IChatMessageRepository;
import com.pck4x.task_manager.modules.chat.objects.dtos.command.SendMessageDto;
import com.pck4x.task_manager.modules.chat.use_cases.command.SendMessageCommand;
import com.pck4x.task_manager.modules.chat.use_cases.events.MessageSentEvent;
import com.pck4x.task_manager.shared.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SendMessageCommandHandler implements SendMessageCommand {
    private final IChatChannelRepository chatChannelRepository;
    private final IChatMessageRepository chatMessageRepository;
    private final com.pck4x.task_manager.modules.workspace.interfaces.repositories.IWorkspaceMemberRepository workspaceMemberRepository;
    private final org.springframework.context.ApplicationEventPublisher eventPublisher;

    @Override
    public Result execute(UUID sendId, SendMessageDto input) {
        var channel = chatChannelRepository.findById(input.getChatChannelId())
                .orElse(null);

        if (channel == null)
            return Result.notFound("Channel not found");

        var member = workspaceMemberRepository.findByWorkspaceIdAndMemberId(channel.getWorkspaceId(), sendId);
        if (member.isEmpty())
            return Result.forbidden("You are not a member of this workspace");

        var chatMessage = TChatMessage.create(
                input.getChatChannelId(),
                sendId,
                input.getMessage(),
                null);

        chatMessageRepository.save(chatMessage);

        eventPublisher.publishEvent(new MessageSentEvent(
                channel.getId(),
                input.getMessage(),
                sendId,
                chatMessage.getSentAt()));

        return Result.success(null, "Message sent");
    }
}
