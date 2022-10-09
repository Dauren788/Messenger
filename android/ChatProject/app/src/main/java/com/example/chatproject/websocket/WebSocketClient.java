package com.example.chatproject.websocket;
import com.example.chatproject.data.model.ChatMessage;
import com.example.chatproject.data.model.WebsocketMsg;
import com.example.chatproject.ui.ChatsFragment;
import com.example.chatproject.ui.ChattingFragment;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

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
        Gson gson = new Gson();

        JsonParser parser = new JsonParser();
        JsonObject obj = parser.parse(response).getAsJsonObject();
        String type = obj.get("type").getAsString();

        WebsocketMsg data = gson.fromJson(response, WebsocketMsg.class);

        switch(type) {
            case "0":
                ChatsFragment.chatsList = data.getMessage();
               break;
            case "2":
                ChattingFragment.chatMessages = data.getMessage();
                break;
            default:
                System.out.println("---------------Other");
        }
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