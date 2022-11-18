package com.example.chatproject;
import android.annotation.SuppressLint;
import android.os.StrictMode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.chatproject.data.model.ChatMessage;
import com.example.chatproject.data.model.LoggedInUser;
import com.example.chatproject.data.model.WebsocketMsg2;
import com.example.chatproject.databinding.ActivityMainBinding;
import com.example.chatproject.ui.ChatsFragment;
import com.example.chatproject.ui.ChattingFragment;
import com.example.chatproject.ui.FeedsFragment;
import com.example.chatproject.ui.LoginActivity;
import com.example.chatproject.ui.ProfileFragment;
//import com.example.chatproject.websocket.WebSocketClient;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    public OkHttpClient client;
    public static WebSocketClient wsListener;
    public static LoggedInUser loggedUser;
    Thread mainThread;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Intent activityIntent;

        // go straight to main if a token is stored
        if (loggedUser != null) {
            startWs();

            binding = ActivityMainBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());
            replaceFragment(new FeedsFragment());

            binding.bottomNavigationView.setOnItemSelectedListener(item -> {
                switch (item.getItemId()) {
                    case R.id.feed:
                        replaceFragment(new FeedsFragment());
                        break;
                    case R.id.chats:
                        replaceFragment(new ChatsFragment());
                        break;
                    case R.id.profile:
                        replaceFragment(new ProfileFragment());
                        break;
                }

                return true;
            });
        } else {
            activityIntent = new Intent(this, LoginActivity.class);
            startActivity(activityIntent);
        }
    }

    private void startWs() {
        mainThread = new Thread() {
            @Override
            public void run() {
                    synchronized (this) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                client = new OkHttpClient();
                                Request request = new Request.Builder().url("ws://10.0.2.2:8080/ws/chats/").header("AuthToken", loggedUser.getJwtToken()).build();
                                wsListener = new WebSocketClient();
                                wsListener.ws = client.newWebSocket(request, wsListener);
                                client.dispatcher().executorService().shutdown();
                            }
                        });
                    }
            }
        };
        mainThread.start();
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

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

            WebsocketMsg2 data = gson.fromJson(response, WebsocketMsg2.class);

            switch(type) {
                case "0":
//                get all chats last message
                    Type typeZero =  new TypeToken<ArrayList<ChatMessage>>(){}.getType();
                    ArrayList<ChatMessage> arrayZero = gson.fromJson(data.getBody(), typeZero);
                    ChatsFragment.chatsList = arrayZero;
                    break;
                case "2":
//                get conversation's all messages
                    ChattingFragment.chatMessages = null;
                    Type typeTwo =  new TypeToken<ArrayList<ChatMessage>>(){}.getType();
                    ArrayList<ChatMessage> arrayTwo = gson.fromJson(data.getBody(), typeTwo);
                    ChattingFragment.chatMessages = arrayTwo;
                    break;
                case "5":
//                receive new chat message
//                Type typeFive =  new TypeToken<ArrayList<NewMessage>>(){}.getType();
//                ArrayList<ChatMessage> arrayFive = gson.fromJson(data.getBody(), typeFive);
                    mainThread = new Thread() {
                        @Override
                        public void run() {
                            synchronized (this) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        ChatMessage newMessage = gson.fromJson(data.getBody(), ChatMessage.class);
                                        ChattingFragment.chatMessages.add(newMessage);
                                        ChattingFragment.adapter.notifyDataSetChanged();
                                    }
                                });
                            }
                        }
                    };
                    mainThread.start();
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
}