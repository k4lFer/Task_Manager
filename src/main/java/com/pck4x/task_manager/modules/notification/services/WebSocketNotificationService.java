package com.pck4x.task_manager.modules.notification.services;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WebSocketNotificationService {
    private final SimpMessagingTemplate messagingTemplate;

    public void notifyChannel(UUID channelId, Object message) {
        messagingTemplate.convertAndSend("/topic/channel/" + channelId, message);
    }

    public void notifyUser(UUID userId, Object notification) {
        // Assuming user subscriptions are at /topic/user/{userId} or similar
        // For private messages, often /user/{userId}/queue/notifications is used,
        // but simple topic subscription works for basic needs.
        messagingTemplate.convertAndSend("/topic/user/" + userId, notification);
    }
}
