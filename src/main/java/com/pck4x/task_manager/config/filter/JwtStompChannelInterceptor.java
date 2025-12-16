package com.pck4x.task_manager.config.filter;

import com.pck4x.task_manager.shared.security.ITokenProvider;
import com.pck4x.task_manager.shared.security.TokenType;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageDeliveryException;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JwtStompChannelInterceptor implements ChannelInterceptor {
    private final ITokenProvider tokenProvider;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {


        StompHeaderAccessor accessor =
                MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        if (accessor == null) return message;

        /*
        if (StompCommand.SUBSCRIBE.equals(accessor.getCommand())) {

            String destination = accessor.getDestination();
            // /topic/channel/{id}

            if (destination != null && destination.startsWith("/topic/channel/")) {

                UUID channelId = UUID.fromString(
                        destination.substring("/topic/channel/".length())
                );

                String userId = accessor.getUser().getName();

                boolean allowed = chatChannelRepository
                        .userBelongsToChannel(UUID.fromString(userId), channelId);

                if (!allowed) {
                    throw new MessageDeliveryException("FORBIDDEN_CHANNEL");
                }
            }
        }
        */

        if (StompCommand.CONNECT.equals(accessor.getCommand())) {

            String authHeader = accessor.getFirstNativeHeader("Authorization");

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                throw new IllegalArgumentException("Missing Authorization header");
            }

            String jwt = authHeader.substring(7);

            if (!tokenProvider.validateToken(jwt, TokenType.ACCESS)) {
                // Al lanzar esta excepción, el cliente recibirá un mensaje de comando ERROR
                throw new MessageDeliveryException("TOKEN_EXPIRED_OR_INVALID");
            }

            String userId = tokenProvider.extractSubject(jwt, TokenType.ACCESS);

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userId, null, List.of());

            accessor.setUser(authentication);
        }

        return message;
    }
}

