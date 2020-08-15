package com.example.weatherforecast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;


public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        SetToolbar();
    }

    private void SetToolbar() {
        Toolbar toolbar = findViewById(R.id.appbar);
        toolbar.setTitle("Settings");
        setSupportActionBar(toolbar);
    }
}