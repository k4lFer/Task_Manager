package com.pck4x.task_manager.modules.notification.listeners;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.pck4x.task_manager.modules.chat.interfaces.services.IChatService;
import com.pck4x.task_manager.modules.chat.objects.dtos.query.ChatMessageResponseDto;
import com.pck4x.task_manager.modules.chat.objects.dtos.socket.ChatNotification;
import com.pck4x.task_manager.modules.chat.use_cases.events.MessageEditedEvent;
import com.pck4x.task_manager.modules.chat.use_cases.events.MessageSentEvent;
import com.pck4x.task_manager.modules.notification.services.WebSocketNotificationService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ChatEventListener {
    private final WebSocketNotificationService notificationService;
    private final IChatService chatService;

    @EventListener
    public void handleMessageSent(MessageSentEvent event) {
        String senderName = chatService.getSenderNameById(event.senderId());

        var payload = new ChatMessageResponseDto(
                event.id(),
                event.channelId(),
                event.message(),
                event.senderId(),
                senderName,
                event.sentAt(),
                null,
                false
        );

        notificationService.notifyChannel(event.channelId(),
                new ChatNotification<>(
                        ChatNotification.NotificationType.CREATED,
                        payload));
    }

    @EventListener
    public void handleMessageEdited(MessageEditedEvent event) {
        String senderName = chatService.getSenderNameById(event.editorId());

        var payload = new ChatMessageResponseDto(
                event.messageId(),
                event.channelId(),
                event.newMessage(),
                event.editorId(),
                senderName,
                null,
                event.editedAt(),
                true
        );

        notificationService.notifyChannel(event.channelId(),
                new ChatNotification<>(
                        ChatNotification.NotificationType.EDITED,
                        payload));
    }
}
