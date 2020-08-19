package com.example.weatherforecast;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        SetToolbar();
    }

    private void SetToolbar() {
        Toolbar toolbar = findViewById(R.id.customToolbar);
        toolbar.setTitle("Settings");
        setSupportActionBar(toolbar);
    }
}