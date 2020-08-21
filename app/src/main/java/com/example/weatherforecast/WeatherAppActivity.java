package com.example.weatherforecast;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class WeatherAppActivity extends ApiDataRequest implements View.OnClickListener, SharedPreferences.OnSharedPreferenceChangeListener {

    private Toolbar customToolbar;
    private FrameLayout toolbarLayout;
    private ImageView cardWeatherStatusImage;
    private TextView cardWeatherStatusText;
    private TextView cardWeatherStatusTemperature;
    private TextView windSpeedTxt;
    private TextView windAngleTxt;
    private TextView pressureTxt;
    private TextView humidityTxt;
    private CardView todayDetailCardView;
    private TextView mMainTempTxt;
    private TextView mMainStateTxt;
    private SearchView searchView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_app);
        SetUpToolBar();
        GetDataFromLocal();

        customToolbar = findViewById(R.id.customToolbar);
        toolbarLayout = findViewById(R.id.toolbarLayout);
        mMainTempTxt = findViewById(R.id.mainTempTxt);
        mMainStateTxt = findViewById(R.id.mainStateTxt);
        cardWeatherStatusImage = findViewById(R.id.cardWeatherStatusImage);
        cardWeatherStatusText = findViewById(R.id.cardWeatherStatusText);
        cardWeatherStatusTemperature = findViewById(R.id.cardWeatherStatusTemperature);
        windSpeedTxt = findViewById(R.id.windSpeedTxt);
        windAngleTxt = findViewById(R.id.windAngleTxt);
        pressureTxt = findViewById(R.id.pressureTxt);
        humidityTxt = findViewById(R.id.humidityTxt);
        todayDetailCardView = findViewById(R.id.todayDetailCardView);
        todayDetailCardView.setOnClickListener(this);

        HandleIntent(getIntent());
    }

    private void SetUpToolBar() {
        toolbar = findViewById(R.id.customToolbar);
        setSupportActionBar(toolbar);
    }

    private void SetIcon(String iconId) {
        if (iconId != null) {
            switch (LocalDataActivity.getIconId()) {
                case "01d":
                case "01n":
                    cardWeatherStatusImage.setImageResource(R.drawable.a01d);
                    return;
                case "02d":
                case "02n":
                    cardWeatherStatusImage.setImageResource(R.drawable.a02d);
                    return;
                case "03d":
                case "03n":
                    cardWeatherStatusImage.setImageResource(R.drawable.a03d);
                    return;
                case "04d":
                case "04n":
                    cardWeatherStatusImage.setImageResource(R.drawable.a04d);
                    return;
                case "09d":
                case "09n":
                    cardWeatherStatusImage.setImageResource(R.drawable.a09d);
                    return;
                case "10d":
                case "10n":
                    cardWeatherStatusImage.setImageResource(R.drawable.a10d);
                    return;
                case "11d":
                case "11n":
                    cardWeatherStatusImage.setImageResource(R.drawable.a11d);
                    return;
                case "13d":
                case "13n":
                    cardWeatherStatusImage.setImageResource(R.drawable.a13d);
                    return;
                case "50d":
                case "50n":
                    cardWeatherStatusImage.setImageResource(R.drawable.a50d);
                    return;
            }
        } else {
            cardWeatherStatusImage.setImageResource(R.drawable.a01d);
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.todayDetailCardView) {
            bottomSheetFunction();
        }
    }

    private void bottomSheetFunction() {

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(WeatherAppActivity.this, R.style.BottomSheetDialogTheme);
        View bottomSheetView = LayoutInflater.from(WeatherAppActivity.this).inflate(R.layout.bottom_sheet_layout,
                (RelativeLayout) findViewById(R.id.bottom_sheet_container));
        bottomSheetView.findViewById(R.id.bottom_sheet_container).setOnClickListener(new View.OnClickListener() {
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
        final TextView temp = bottomSheetDialog.findViewById(R.id.bs_temp);
        final TextView feelsLike = bottomSheetDialog.findViewById(R.id.bs_feels_like);
        final TextView tempMin = bottomSheetDialog.findViewById(R.id.bs_temp_min);
        final TextView tempMax = bottomSheetDialog.findViewById(R.id.bs_temp_max);
        final TextView pressure = bottomSheetDialog.findViewById(R.id.bs_pressure);
        final TextView humidity = bottomSheetDialog.findViewById(R.id.bs_humidity);
        final TextView speed = bottomSheetDialog.findViewById(R.id.bs_speed);
        final TextView degree = bottomSheetDialog.findViewById(R.id.bs_degree);
        final TextView country = bottomSheetDialog.findViewById(R.id.bs_country);

        cityName.setText(String.format("City: %s", LocalDataActivity.getCityName()));
        mainState.setText(String.format("State: %s", LocalDataActivity.getMainState()));
        description.setText(String.format("Description: %s", LocalDataActivity.getDescription()));
        temp.setText(String.format("Temp: %s°C", LocalDataActivity.getTemp()));
        feelsLike.setText(String.format("Feels Like: %s", LocalDataActivity.getFeelsLike()));
        tempMin.setText(String.format("Min Temp: %s", LocalDataActivity.getTempMin()));
        tempMax.setText(String.format("Max Temp: %s", LocalDataActivity.getTempMax()));
        pressure.setText(String.format("Pressure: %s", LocalDataActivity.getPressure()));
        humidity.setText(String.format("Humidity: %s", LocalDataActivity.getHumidity()));
        speed.setText(String.format("Speed: %s", LocalDataActivity.getSpeed()));
        degree.setText(String.format("Degree: %s", LocalDataActivity.getDegree()));
        country.setText(String.format("Country: %s", LocalDataActivity.getCountry()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.search_location).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryRefinementEnabled(true);

        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        HandleIntent(intent);
    }

    // Get the intent, verify the action and get the query
    private void HandleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String cityName = intent.getStringExtra(SearchManager.QUERY);
            RequestByCityName(cityName, getApplicationContext());
            searchView.setQuery("", false);
            searchView.clearFocus();
            searchView.onActionViewCollapsed();
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        GetDataFromLocal();
        if (key.equals("Temp")) {
            String a = LocalDataActivity.getTemp();
            mMainTempTxt.setText(a);
            cardWeatherStatusTemperature.setText(String.format("%s°C", a));
        }
        if (key.equals("MainState")) {
            String b = LocalDataActivity.getMainState();
            mMainStateTxt.setText(b);
        }
        if (key.equals("Description")) {
            cardWeatherStatusText.setText(LocalDataActivity.getDescription());
        }
        if (key.equals("Icon")) {
            SetIcon(LocalDataActivity.getIconId());
        }
        if (key.equals("FeelsLike")) {

        }
        if (key.equals("MinTemp")) {

        }
        if (key.equals("MaxTemp")) {

        }
        if (key.equals("Pressure")) {
            pressureTxt.setText(String.format("Pressure: %s", LocalDataActivity.getPressure()));
        }
        if (key.equals("Humidity")) {
            humidityTxt.setText(String.format("Humidity: %s", LocalDataActivity.getHumidity()));
        }
        if (key.equals("WindSpeed")) {
            windSpeedTxt.setText(String.format("Wind Speed: %s", LocalDataActivity.getSpeed()));
        }
        if (key.equals("WindAngle")) {
            windAngleTxt.setText(String.format("Direction: %s", LocalDataActivity.getDegree()));
        }
        if (key.equals("Country")) {

        }
        if (key.equals("CityName")) {
            toolbar.setTitle(LocalDataActivity.getCityName());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        LocalDataActivity.registerPref(this, this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalDataActivity.unregisterPref(this, this);
    }
}
