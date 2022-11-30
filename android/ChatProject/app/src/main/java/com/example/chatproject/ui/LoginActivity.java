package com.example.chatproject.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chatproject.MainActivity;
import com.example.chatproject.R;
import com.example.chatproject.data.model.LoggedInUser;
import com.example.chatproject.data.model.User;
import com.example.chatproject.databinding.ActivityLoginBinding;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.Route;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private String endpoint = "http://10.0.2.2:8080/login/";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListeners();
    }

    private void setListeners () {
        binding.textCreateNewAccount.setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), RegistrationActivity.class)));


        Button loginBtn = (Button) findViewById(R.id.buttonSignIn);
        EditText emailField = (EditText) findViewById(R.id.inputEmail);
        EditText passwordField = (EditText) findViewById(R.id.inputPassword);

        loginBtn.setOnClickListener(View -> {

            User user1 = new User(2, "default","Surn", "Name",
                "email@mail.ru", "23232", "asdasd");
            LoggedInUser logged = new LoggedInUser("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2Njk2OTYzNzMsInN1YiI6IjIwMTYifQ.2njJuukJ9C86b5uHyvuCR48vAC-bIc7pMEFVB4fTBz4", user1);

            //  LoggedInUser logged = new LoggedInUser("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2Njk2OTYzNzMsInN1YiI6IjIwMTYifQ.2njJuukJ9C86b5uHyvuCR48vAC-bIc7pMEFVB4fTBz4", null);
            MainActivity.loggedUser = logged;
              Intent returnBtn = new Intent(getApplicationContext(),
                    MainActivity.class);
            startActivity(returnBtn);
//            try {
//                login(emailField.getText().toString(), passwordField.getText().toString());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        });
    }

    private int responseCount(Response response) {
        int result = 1;
        while ((response = response.priorResponse()) != null) {
            result++;
        }
        return result;
    }

    public void login(String username, String password) throws IOException {
        try {
            MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            RequestBody body = RequestBody.create(JSON, "");

            Request request  = new Request.Builder()
                    .url(endpoint).post(body)
                    .addHeader("Authorization", Credentials.basic(username, password))
                    .build();
            OkHttpClient client = new OkHttpClient();
            Gson gson = new Gson();
            ResponseBody responseBody = client.newCall(request).execute().body();
            LoggedInUser responseEntity = gson.fromJson(responseBody.string(), LoggedInUser.class);

            MainActivity.loggedUser = responseEntity;
            Intent returnBtn = new Intent(getApplicationContext(),
                    MainActivity.class);
            startActivity(returnBtn);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}