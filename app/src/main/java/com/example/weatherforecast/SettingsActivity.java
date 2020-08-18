package com.example.weatherforecast;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class SettingsActivity extends AppCompatActivity {

    TextView tb;
    DataFlowConstructor d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        d=new DataFlowConstructor();
        tb = findViewById(R.id.textView4);
        SetToolbar();
    }

    private void SetToolbar() {
        Toolbar toolbar = findViewById(R.id.customToolbar);
        toolbar.setTitle("Settings");
        setSupportActionBar(toolbar);
    }
}