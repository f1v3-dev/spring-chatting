package com.f1v3.chat.model;

import lombok.Data;

/**
 * ChatMessage Model.
 *
 * @author 정승조
 * @version 2024. 09. 11.
 */
@Data
public class ChatMessage {
    private MessageType type;
    private String content;
    private String sender;

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }
}
