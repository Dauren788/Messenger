package com.example.messenger.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.messenger.R;
import com.example.messenger.databinding.ActivitySignInBinding;
import com.example.messenger.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListeners();
    }

    private void setListeners(){
        binding.textSignIn.setOnClickListener(v -> onBackPressed());
    }
}