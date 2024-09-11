package com.f1v3.chat.listener;

import com.f1v3.chat.model.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Objects;

/**
 * @author 정승조
 * @version 2024. 09. 11.
 */
@Slf4j
@RequiredArgsConstructor
public class WebSocketEventListener {

    private final SimpMessageSendingOperations messagingTemplate;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        log.info("Received a new web socket connection!");
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());

        String username = (String) accessor.getSessionAttributes().get("username");

        if (Objects.nonNull(username)) {
            log.info("User Disconnected = {}", username);

            ChatMessage message = new ChatMessage();
            message.setType(ChatMessage.MessageType.LEAVE);
            message.setSender(username);

            messagingTemplate.convertAndSend("/topic/public", message);
        }
    }
}
