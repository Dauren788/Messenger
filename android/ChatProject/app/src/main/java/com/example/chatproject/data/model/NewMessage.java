package com.example.chatproject.data.model;

import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewMessage {
    @SerializedName(value="message_id", alternate = "MessageID")
    private String messageId;
    @SerializedName(value="conversation_id", alternate = "ConversationID")
    private String conversationId;
    @SerializedName(value="from_user", alternate = "FromUser")
    private String fromUser;
    @SerializedName(value="text", alternate = "Text")
    private String text;
    @SerializedName(value="created_at", alternate = "CreatedAt")
    private Date createdAt;

    public NewMessage(String conversationId, String messageId, String fromUser, String text, Date createdAt) {
        this.conversationId = conversationId;
        this.messageId = messageId;
        this.fromUser = fromUser;
        this.text = text;
        this.createdAt = createdAt;
    }

    public String getProfileId() {
        return this.fromUser;
    }

    public String getMessageText() {
        return this.text;
    }

    public String getMessageSendDate() {
        Date date = createdAt;
        DateFormat dateFormat = new SimpleDateFormat("mm-dd hh:mm:ss");
        String strDate = dateFormat.format(date);
        return strDate;
    }

    public String getConversationId() {
        return this.conversationId;
    }

}
