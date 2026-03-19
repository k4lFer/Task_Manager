package com.pck4x.task_manager.modules.chat.use_cases.handlers;

import com.pck4x.task_manager.modules.chat.interfaces.repositories.IChatMessageRepository;
import com.pck4x.task_manager.modules.chat.objects.dtos.command.EditMessageDto;
import com.pck4x.task_manager.modules.chat.use_cases.command.EditMessageCommand;
import com.pck4x.task_manager.modules.chat.use_cases.events.MessageEditedEvent;
import com.pck4x.task_manager.shared.result.OutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class EditMessageCommandHandler implements EditMessageCommand {
    private final IChatMessageRepository chatMessageRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public OutputPort<?> execute(UUID editorId, EditMessageDto input) {
        var message = chatMessageRepository.findById(input.getMessageId())
                .orElse(null);

        if (message == null) {
            return OutputPort.failure(HttpStatus.NOT_FOUND, "Message not found");
        }

        if (!message.getUserId().equals(editorId)) {
            return OutputPort.failure(HttpStatus.FORBIDDEN, "You can only edit your own messages");
        }

        message.edit(input.getNewMessage());
        chatMessageRepository.save(message);

        eventPublisher.publishEvent(new MessageEditedEvent(
                message.getId(),
                message.getChannelId(),
                message.getMessage(),
                editorId,
                message.getEditedAt()));

        return OutputPort.success(null, HttpStatus.OK,"Message edited");
    }
}
