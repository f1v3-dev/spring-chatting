package com.f1v3.chat.controller;

import com.f1v3.chat.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.Map;

/**
 * 채팅 요청을 처리하는 컨트롤러 클래스입니다.
 *
 * @author 정승조
 * @version 2024. 09. 11.
 */
@Controller
public class ChatController {

    @SendTo("/topic/public")
    @MessageMapping("/chat.sendMessage")
    public ChatMessage sendMessage(@Payload ChatMessage message) {
        return message;
    }

    @SendTo("/topic/public")
    @MessageMapping("/chat.addUser")
    public ChatMessage addUser(@Payload ChatMessage message,
                               SimpMessageHeaderAccessor headerAccessor) {

        // username 를 세션에 저장합니다.
        Map<String, Object> sessionAttributes = headerAccessor.getSessionAttributes();
        sessionAttributes.put("username", message.getSender());

        return message;
    }

}
