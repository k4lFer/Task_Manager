package com.pck4x.task_manager.modules.chat.infrastructure.acl;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PresenceService {

    // sessionId -> userId
    private final Map<String, String> sessions = new ConcurrentHashMap<>();

    private final SimpMessagingTemplate messagingTemplate;

    public PresenceService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void userConnected(String sessionId, String userId) {
        sessions.put(sessionId, userId);
        publishPresence();
    }

    public void userDisconnected(String sessionId) {
        sessions.remove(sessionId);
        publishPresence();
    }

    public boolean isUserOnline(String userId) {
        return sessions.containsValue(userId);
    }

    public Set<String> getOnlineUsers() {
        return new HashSet<>(sessions.values());
    }

    private void publishPresence() {
        List<String> onlineUsers = new ArrayList<>(sessions.values());

        System.out.println("PRESENCE PAYLOAD -> " + onlineUsers);
        messagingTemplate.convertAndSend(
                "/topic/online-users",
                onlineUsers
        );
    }
}
