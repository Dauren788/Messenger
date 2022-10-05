package com.example.chatproject.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.JsonReader;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chatproject.MainActivity;
import com.example.chatproject.R;
import com.example.chatproject.databinding.ActivityLoginBinding;
import com.google.android.material.button.MaterialButton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class LogInActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListeners();
    }
    //    Intent intent = new Intent(this, MainActivity.class);
//    MaterialButton loginBtn = (MaterialButton) findViewById(R.id.buttonSignIn);
//    EditText usernameField = (EditText)findViewById(R.id.inputEmail);
//    EditText passwordField = (EditText)findViewById(R.id.inputPassword);
    private void setListeners () {
        binding.textCreateNewAccount.setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), LogUpActivity.class)));


        Button loginBtn = (Button) findViewById(R.id.buttonSignIn);
        EditText emailField = (EditText) findViewById(R.id.inputEmail);
        EditText passwordField = (EditText) findViewById(R.id.inputPassword);

        loginBtn.setOnClickListener(View -> {
            try {
                login(emailField.getText().toString(), passwordField.getText().toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void login(String username, String password) throws IOException {
        try {
            URL httpEndpoint = new URL("http://10.0.2.2:8080/login/");

            HttpURLConnection urlConnection =
                    (HttpURLConnection) httpEndpoint.openConnection();

            final String basicAuth = "Basic " + Base64.encodeToString((username + ":" + password).getBytes(), Base64.NO_WRAP);

            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Authorization", basicAuth);
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Connection", "keep-alive");
            urlConnection.setDoOutput(true);

            String line;
            BufferedReader reader;
            StringBuffer responseContent = new StringBuffer();

            int status = urlConnection.getResponseCode();

            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(urlConnection.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            } else {
                reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            }

            MainActivity.token = responseContent.toString();
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