package com.example.weatherforecast;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
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

        todayDetailCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetFunction();
            }
        });
    }

    private void bottomSheetFunction() {

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                WeatherAppActivity.this,R.style.BottomSheetDialogTheme);
        View bottomSheetView = LayoutInflater.from(WeatherAppActivity.this)
                .inflate(R.layout.bottom_sheet_layout,
                        (LinearLayout)findViewById(R.id.bottom_sheet_container));
        bottomSheetView.findViewById(R.id.bottom_sheet_container)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        bottomSheetDialog.dismiss();
                    }
                });
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();

        final TextView cityName = bottomSheetDialog.findViewById(R.id.bs_cityName);
        final TextView mainState = bottomSheetDialog.findViewById(R.id.bs_mainState);
        final TextView description = bottomSheetDialog.findViewById(R.id.bs_description);
        final TextView iconId = bottomSheetDialog.findViewById(R.id.bs_iconId);
        final TextView temp = bottomSheetDialog.findViewById(R.id.bs_temp);
        final TextView feelsLike = bottomSheetDialog.findViewById(R.id.bs_feels_like);
        final TextView tempMin = bottomSheetDialog.findViewById(R.id.bs_temp_min);
        final TextView tempMax = bottomSheetDialog.findViewById(R.id.bs_temp_max);
        final TextView pressure = bottomSheetDialog.findViewById(R.id.bs_pressure);
        final TextView humidity = bottomSheetDialog.findViewById(R.id.bs_humidity);
        final TextView speed = bottomSheetDialog.findViewById(R.id.bs_speed);
        final TextView degree = bottomSheetDialog.findViewById(R.id.bs_degree);
        final TextView country = bottomSheetDialog.findViewById(R.id.bs_country);

        cityName.setText("City: " + ApiDataRequestActivity.getCityName());
        mainState.setText("State: " + ApiDataRequestActivity.getMainState());
        description.setText("Description: " + ApiDataRequestActivity.getDescription());
        iconId.setText(ApiDataRequestActivity.getIconId());
        temp.setText(ApiDataRequestActivity.getTemp());
        feelsLike.setText(ApiDataRequestActivity.getFeelsLike());
        tempMin.setText(ApiDataRequestActivity.getTempMin());
        tempMax.setText(ApiDataRequestActivity.getTempMax());
        pressure.setText(ApiDataRequestActivity.getPressure());
        humidity.setText(ApiDataRequestActivity.getHumidity());
        speed.setText(ApiDataRequestActivity.getSpeed());
        degree.setText(ApiDataRequestActivity.getDegree());
        country.setText(ApiDataRequestActivity.getCountry());
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