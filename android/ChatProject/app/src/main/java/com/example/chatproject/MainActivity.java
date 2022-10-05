package com.example.chatproject;
import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.chatproject.databinding.ActivityMainBinding;
import com.example.chatproject.ui.login.LogInActivity;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private Button start;
    private TextView output;
    private OkHttpClient client;
    public static String token;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Intent activityIntent;

        token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2NjUxNzIwNjIsInN1YiI6IjIwMTQifQ.LCuNBGGWmVJeeP0yWj1C5zPotsiK6XQnA5nPVjOQalY";

        // go straight to main if a token is stored
        if (token != null) {
            start();

            binding = ActivityMainBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());
            replaceFragment(new HomeFragment());

            binding.bottomNavigationView.setOnItemSelectedListener(item -> {
                switch (item.getItemId()) {
                    case R.id.home:
                        replaceFragment(new HomeFragment());
                        break;
                    case R.id.chats:
                        replaceFragment(new HomeFragment.ChatsFragment());
                        break;
                    case R.id.profile:
                        replaceFragment(new HomeFragment.ProfileFragment());
                        break;
                }

                return true;
            });
        } else {
            activityIntent = new Intent(this, LogInActivity.class);
            startActivity(activityIntent);
        }
    }

    private void start() {
        client = new OkHttpClient();
//        String resultToken = token.substring(1, token.length() - 1);
        Request request = new Request.Builder().url("ws://10.0.2.2:8080/ws/chats/?AuthToken="+token).build();
        WebSocketClient listener = new WebSocketClient();
        listener.ws = client.newWebSocket(request, listener);
        client.dispatcher().executorService().shutdown();
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}