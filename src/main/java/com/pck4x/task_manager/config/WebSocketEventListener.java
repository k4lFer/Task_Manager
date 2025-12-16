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
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        // El usuario ya fue autenticado y guardado en el header por JwtStompChannelInterceptor
        UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) headerAccessor.getUser();

        if (auth != null) {
            String userId = (String) auth.getPrincipal(); // El principal es el userId
            presenceService.userConnected(userId);
            System.out.println("STOMP Session connected for user: " + userId);
        }
    }

    // --- EVENTO DE DESCONEXIÓN ---
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        // Para la desconexión, el usuario permanece en el contexto de la sesión
        UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) headerAccessor.getUser();

        if (auth != null) {
            String userId = (String) auth.getPrincipal();
            presenceService.userDisconnected(userId);
            System.out.println("STOMP Session disconnected for user: " + userId);
        }
    }
}