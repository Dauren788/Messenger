package com.example.chatproject.data.model;

import java.util.Date;

public class ChatMessage {
    public String conversationId;
    public String messageId;
    public String fromUser;
    public String text;
    public Date createdAt;

    public ChatMessage(String conversationId, String messageId, String fromUser, String text, Date createdAt) {
        this.conversationId = conversationId;
        this.messageId = messageId;
        this.fromUser = fromUser;
        this.text = text;
        this.createdAt = createdAt;
    }

}
