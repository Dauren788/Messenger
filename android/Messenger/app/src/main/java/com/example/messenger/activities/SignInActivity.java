package com.example.messenger.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.messenger.R;
import com.example.messenger.databinding.ActivitySignInBinding;

public class SignInActivity extends AppCompatActivity {

    private ActivitySignInBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

}