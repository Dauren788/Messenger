package com.example.chatproject;
import com.example.chatproject.data.model.ChatMessage;
import com.google.gson.reflect.TypeToken;

import org.intellij.lang.annotations.Language;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public final class WebSocketClient extends WebSocketListener {
    private static final int NORMAL_CLOSURE_STATUS = 1000;
    public WebSocket ws;

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        String json = "{\"type\":0}";
        ws.send(json);
    }

    @Override
    public void onMessage(WebSocket webSocket, String response) {
//        Type chatMessages = new TypeToken<List<ChatMessage>>(){}.getType();
//        List<ChatMessage> posts = gson.fromJson(response, chatMessages);
        System.out.println("----------Receiving : " + response);
    }

    @Override
    public void onMessage(WebSocket webSocket, ByteString bytes) {
        System.out.println("----------Receiving bytes : " + bytes.hex());
    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        webSocket.close(NORMAL_CLOSURE_STATUS, null);
        System.out.println("----------Closing : " + code + " / " + reason);
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        System.out.println("----------Error : " + t.getMessage());
    }

}