package com.pck4x.task_manager.modules.chat.infrastructure.acl;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class PresenceService {
    private final Set<String> connectedUsers = Collections.synchronizedSet(new HashSet<>());
    private final SimpMessagingTemplate messagingTemplate;
    private static final String ONLINE_USERS_TOPIC = "/topic/online-users";

    public PresenceService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    /**
     * Añade un usuario a la lista de conectados y notifica a todos.
     */
    public void userConnected(String userId) {
        if (connectedUsers.add(userId)) {
            publishOnlineUsers();
        }
    }

    public void userDisconnected(String userId) {
        if (connectedUsers.remove(userId)) {
            publishOnlineUsers();
        }
    }

    public Set<String> getConnectedUsers() {
        return new HashSet<>(connectedUsers); // Retorna una copia segura
    }


    private void publishOnlineUsers() {
        messagingTemplate.convertAndSend(ONLINE_USERS_TOPIC, connectedUsers);
        System.out.println("Presence update: " + connectedUsers.size() + " users online.");
    }
}