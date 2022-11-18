//package com.example.chatproject.websocket;
//import android.annotation.SuppressLint;
//
//import com.example.chatproject.data.model.ChatMessage;
//import com.example.chatproject.data.model.NewMessage;
//import com.example.chatproject.data.model.WebsocketMsg;
//import com.example.chatproject.data.model.WebsocketMsg2;
//import com.example.chatproject.ui.ChatsFragment;
//import com.example.chatproject.ui.ChattingFragment;
//import com.example.chatproject.ui.recyclerview_chatting.ChattingAdapter;
//import com.google.gson.Gson;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//import com.google.gson.reflect.TypeToken;
//
//import java.lang.reflect.Type;
//import java.util.ArrayList;
//import java.util.List;
//
//import okhttp3.Response;
//import okhttp3.WebSocket;
//import okhttp3.WebSocketListener;
//
//public final class WebSocketClient extends WebSocketListener {
//    private static final int NORMAL_CLOSURE_STATUS = 1000;
//    public WebSocket ws;
//
//    @Override
//    public void onOpen(WebSocket webSocket, Response response) {
//        String json = "{\"type\":0}";
//        ws.send(json);
//    }
//
//    @Override
//    public void onMessage(WebSocket webSocket, String response) {
//        Gson gson = new Gson();
//
//        JsonParser parser = new JsonParser();
//        JsonObject obj = parser.parse(response).getAsJsonObject();
//        String type = obj.get("type").getAsString();
//
//        WebsocketMsg2 data = gson.fromJson(response, WebsocketMsg2.class);
//
//        switch(type) {
//            case "0":
////                get all chats last message
//                Type typeZero =  new TypeToken<ArrayList<ChatMessage>>(){}.getType();
//                ArrayList<ChatMessage> arrayZero = gson.fromJson(data.getBody(), typeZero);
//                ChatsFragment.chatsList = arrayZero;
//               break;
//            case "2":
////                get conversation's all messages
//                ChattingFragment.chatMessages = null;
//                Type typeTwo =  new TypeToken<ArrayList<ChatMessage>>(){}.getType();
//                ArrayList<ChatMessage> arrayTwo = gson.fromJson(data.getBody(), typeTwo);
//                ChattingFragment.chatMessages = arrayTwo;
//                break;
//            case "5":
////                receive new chat message
////                Type typeFive =  new TypeToken<ArrayList<NewMessage>>(){}.getType();
////                ArrayList<ChatMessage> arrayFive = gson.fromJson(data.getBody(), typeFive);
//                ChatMessage newMessage = gson.fromJson(data.getBody(), ChatMessage.class);
//                ChattingFragment.chatMessages.add(newMessage);
//                ChattingFragment.adapter.notifyDataSetChanged();
//                break;
//            default:
//                System.out.println("---------------Other");
//        }
//    }
//
//    @Override
//    public void onClosing(WebSocket webSocket, int code, String reason) {
//        webSocket.close(NORMAL_CLOSURE_STATUS, null);
//        System.out.println("----------Closing : " + code + " / " + reason);
//    }
//
//    @Override
//    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
//        System.out.println("----------Error : " + t.getMessage());
//    }
//}