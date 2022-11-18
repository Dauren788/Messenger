package com.example.chatproject.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class WebsocketMsg2 {
    @SerializedName(value="type")
    private int type;
    @SerializedName(value="body")
    private String body;

    public WebsocketMsg2(int type, String body) {
        this.type = type;
        this.body = body;
    }

    public int getType() {
        return type;
    }

    public String getBody() {
        return body;
    }
}