package com.example.weatherforecast;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

public class WeatherAppActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_app);

        SetUpToolBar();
    }

    private void SetUpToolBar() {
        Toolbar toolbar = findViewById(R.id.customToolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search_location:
                SearchLocationData();
                break;
            case R.id.select_theme:
                ToggleTheme();
                break;
            case R.id.manage_cities:
                AddOrRemoveCities();
                break;
            case R.id.setting:
                startActivity(new Intent(this, ApiDataRequestActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void AddOrRemoveCities() {
    }

    private void ToggleTheme() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
    }

    private void SearchLocationData() {
    }
}