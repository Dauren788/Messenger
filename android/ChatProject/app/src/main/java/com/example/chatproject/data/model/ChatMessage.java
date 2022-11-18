package com.example.chatproject.data.model;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatMessage {
    @SerializedName(value="LastMessage")
    private String LastMessage;
    @SerializedName(value="conversation_id", alternate = "ConversationID")
    private String conversationId;
    @SerializedName(value="username", alternate = "Username")
    private String username;
    @SerializedName(value="message_id", alternate = "MessageId")
    private String messageId;
    @SerializedName(value="from_user", alternate = "FromUser")
    private String fromUser;
    @SerializedName(value="text", alternate = "Text")
    private String text;
    @SerializedName(value="created_at", alternate = "CreatedAt")
    private Date createdAt;

    public ChatMessage(String conversationId, String username, String messageId, String fromUser, String text, Date createdAt) {
        this.username = username;
        this.conversationId = conversationId;
        this.messageId = messageId;
        this.fromUser = fromUser;
        this.text = text;
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "conversationId='" + conversationId + '\'' +
                ", messageId='" + messageId + '\'' +
                ", fromUser='" + fromUser + '\'' +
                ", text='" + text + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

    public String getProfileId() {
        return this.fromUser;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public String getProfileUsername() {
        return this.username;
    }

    public String getMessageText() {
        return this.text;
    }

    public String getMessageSendDate() {
        Date date = createdAt;
        DateFormat dateFormat = new SimpleDateFormat("hh:mm");
        String strDate = dateFormat.format(date);
        return strDate;
    }

    public String getConversationId() {
        return this.conversationId;
    }

}
