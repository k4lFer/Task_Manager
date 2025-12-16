package com.pck4x.task_manager.config;

import com.pck4x.task_manager.modules.chat.infrastructure.acl.PresenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@RequiredArgsConstructor
public class WebSocketEventListener {

    private final PresenceService presenceService;

    // --- EVENTO DE CONEXIÓN ---
    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());

        if (accessor.getUser() instanceof UsernamePasswordAuthenticationToken auth) {

            String userId = (String) auth.getPrincipal();
            String sessionId = accessor.getSessionId();

            presenceService.userConnected(sessionId, userId);
        }
    }

    // --- EVENTO DE DESCONEXIÓN ---
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        presenceService.userDisconnected(event.getSessionId());
    }
}