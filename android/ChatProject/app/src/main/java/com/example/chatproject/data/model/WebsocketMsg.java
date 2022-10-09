package com.example.chatproject.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class WebsocketMsg {
    @SerializedName(value="type")
    private int type;
    @SerializedName(value="message")
    private ArrayList<ChatMessage> message;

    public WebsocketMsg(int type, ArrayList<ChatMessage> message) {
        this.type = type;
        this.message = message;
    }

    public int getType() {
        return type;
    }

    public ArrayList<ChatMessage> getMessage() {
        return message;
    }
}
