package com.example.chatproject.ui;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;

import com.example.chatproject.MainActivity;
import com.example.chatproject.R;

import androidx.annotation.RequiresApi;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chatproject.databinding.ActivitySettingsBinding;

public class SettingsActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.content_settings);

        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);

        if (seekBar != null) {
            seekBar.setProgress(MainActivity.select_coef);
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    if (progress != 0) {
                        MainActivity.size_coef = progress; //выбранный коэффициент
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                }
            });
        }
    }
}