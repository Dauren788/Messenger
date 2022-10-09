package com.example.chatproject.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.chatproject.R;
import com.example.chatproject.databinding.ActivityRegistrationBinding;

public class RegistrationActivity extends AppCompatActivity {

    private ActivityRegistrationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListeners();
    }

    private void setListeners(){
        binding.textSignIn.setOnClickListener(view -> onBackPressed());
    }

}