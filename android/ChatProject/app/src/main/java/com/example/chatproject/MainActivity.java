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

import com.example.chatproject.data.model.LoggedInUser;
import com.example.chatproject.databinding.ActivityMainBinding;
import com.example.chatproject.ui.ChatsFragment;
import com.example.chatproject.ui.FeedsFragment;
import com.example.chatproject.ui.LoginActivity;
import com.example.chatproject.ui.ProfileFragment;
import com.example.chatproject.websocket.WebSocketClient;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    public OkHttpClient client;
    public static WebSocketClient wsListener;
    public static LoggedInUser loggedUser;

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
        client = new OkHttpClient();
        Request request = new Request.Builder().url("ws://10.0.2.2:8080/ws/chats/").header("AuthToken", loggedUser.getJwtToken()).build();
        wsListener = new WebSocketClient();
        wsListener.ws = client.newWebSocket(request, wsListener);
        client.dispatcher().executorService().shutdown();
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}