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

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
        private void setListeners (){
            binding.textCreateNewAccount.setOnClickListener(view ->
                    startActivity(new Intent(getApplicationContext(),LogUpActivity.class)));
//            loginBtn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if(usernameField.getText().toString().equals("admin") &&
//                             passwordField.getText().toString().equals("admin")){
//                        MainActivity.token = "asdasd";
//                        Toast.makeText(getApplicationContext(),
//                                "Login success...", Toast.LENGTH_SHORT).show();
//                        startActivity(intent);
//                    }
//                }
//            });
        }


  //      Intent intent = new Intent(this, MainActivity.class);

  //      MaterialButton loginBtn = (MaterialButton) findViewById(R.id.buttonSignIn);
  //      EditText usernameField = (EditText)findViewById(R.id.inputEmail);
  //      EditText passwordField = (EditText)findViewById(R.id.inputPassword);

//        loginBtn.setOnClickListener(View -> {
//            try {
//                login(usernameField.getText().toString(), passwordField.getText().toString());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });

//        loginBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(usernameField.getText().toString().equals("admin") &&
//                        passwordField.getText().toString().equals("admin")) {
//
//                    MainActivity.token = "asdasd";
//
//                    Toast.makeText(getApplicationContext(),
//                            "Login success...",Toast.LENGTH_SHORT).show();
//
//                    startActivity(intent);
//                }
//            }
//        });
//    }

    public void login(String username, String password) throws IOException {
        URL httpEndpoint = new URL("http://10.0.2.2:8000/login/");

        HttpsURLConnection myConnection =
                (HttpsURLConnection) httpEndpoint.openConnection();

        final String basicAuth = "Basic " + Base64.encodeToString((username + ":" + password).getBytes(), Base64.NO_WRAP);

        myConnection.setRequestProperty("Authorization", basicAuth);

        InputStream responseBody = myConnection.getInputStream();

        InputStreamReader responseBodyReader =
                new InputStreamReader(responseBody, "UTF-8");

        JsonReader jsonReader = new JsonReader(responseBodyReader);

        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String key = jsonReader.nextName();
            Toast.makeText(getApplicationContext(), key,Toast.LENGTH_SHORT).show();

            String value = jsonReader.nextString();
            Toast.makeText(getApplicationContext(), value,Toast.LENGTH_SHORT).show();
        }

        jsonReader.close();
        myConnection.disconnect();
    }
}