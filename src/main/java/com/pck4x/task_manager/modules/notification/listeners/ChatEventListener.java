package com.pck4x.task_manager.modules.notification.listeners;

import com.pck4x.task_manager.modules.chat.objects.dtos.socket.ChatNotification;
import com.pck4x.task_manager.modules.chat.use_cases.events.MessageEditedEvent;
import com.pck4x.task_manager.modules.chat.use_cases.events.MessageSentEvent;
import com.pck4x.task_manager.modules.notification.services.WebSocketNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatEventListener {
    private final WebSocketNotificationService notificationService;

    @EventListener
    public void handleMessageSent(MessageSentEvent event) {
        notificationService.notifyChannel(event.channelId(),
                new ChatNotification(
                        ChatNotification.NotificationType.CREATED,
                        event));
    }

    @EventListener
    public void handleMessageEdited(MessageEditedEvent event) {
        notificationService.notifyChannel(event.channelId(),
                new ChatNotification(
                        ChatNotification.NotificationType.EDITED,
                        event));
    }
}
