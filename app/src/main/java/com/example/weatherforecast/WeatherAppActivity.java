package com.example.weatherforecast;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class WeatherAppActivity extends AppCompatActivity {

    private Toolbar customToolbar;
    private FrameLayout toolbarLayout;
    private ImageView cardWeatherStatusImage;
    private TextView textView2;
    private TextView cardWeatherStatusText;
    private TextView cardWeatherStatusTemperature;
    private TextView windSpeedTxt;
    private TextView windAngleTxt;
    private CardView cardView2;
    private TextView pressureTxt;
    private TextView humidityTxt;
    private CardView todayDetailCardView;
    private TextView seeMoreTxt;
    private FloatingActionButton addHomeFloatingBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_app);
        SetUpToolBar();


        customToolbar = (Toolbar) findViewById(R.id.customToolbar);
        toolbarLayout = (FrameLayout) findViewById(R.id.toolbarLayout);
        cardWeatherStatusImage = (ImageView) findViewById(R.id.cardWeatherStatusImage);
        textView2 = (TextView) findViewById(R.id.textView2);
        cardWeatherStatusText = (TextView) findViewById(R.id.cardWeatherStatusText);
        cardWeatherStatusTemperature = (TextView) findViewById(R.id.cardWeatherStatusTemperature);
        windSpeedTxt = (TextView) findViewById(R.id.windSpeedTxt);
        windAngleTxt = (TextView) findViewById(R.id.windAngleTxt);
        cardView2 = (CardView) findViewById(R.id.cardView2);
        pressureTxt = (TextView) findViewById(R.id.pressureTxt);
        humidityTxt = (TextView) findViewById(R.id.humidityTxt);
        todayDetailCardView = (CardView) findViewById(R.id.todayDetailCardView);
        addHomeFloatingBtn = (FloatingActionButton) findViewById(R.id.addHomeFloatingBtn);

        cardWeatherStatusText.setText(ApiDataRequestActivity.getMainState());
        cardWeatherStatusTemperature.setText(ApiDataRequestActivity.getTemp());
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