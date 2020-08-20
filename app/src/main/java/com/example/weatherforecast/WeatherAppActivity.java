package com.example.weatherforecast;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
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
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class WeatherAppActivity extends ApiDataRequest implements View.OnClickListener {

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

    private static String asd;

    public static String getAsd() {
        return asd;
    }

    public static void setAsd(String asd) {
        WeatherAppActivity.asd = asd;
    }

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

        mMainStateTxt.setText(LocalDataActivity.getMainState());
        mMainTempTxt.setText(LocalDataActivity.getTemp());
        cardWeatherStatusText.setText(LocalDataActivity.getDescription());
        cardWeatherStatusTemperature.setText(String.format("%s°C",LocalDataActivity.getTemp()));
        windSpeedTxt.setText(String.format("Wind Speed: %s",LocalDataActivity.getSpeed()));
        windAngleTxt.setText(String.format("Direction: %s",LocalDataActivity.getDegree()));
        pressureTxt.setText(String.format("Pressure: %s",LocalDataActivity.getPressure()));
        humidityTxt.setText(String.format("Humidity: %s",LocalDataActivity.getHumidity()));
    }

    private void SetUpToolBar() {
        Toolbar toolbar = findViewById(R.id.customToolbar);
        toolbar.setTitle(LocalDataActivity.getCityName());
        setSupportActionBar(toolbar);
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

        cityName.setText(String.format("City: %s", LocalDataActivity.getCityName()));
        mainState.setText(String.format("State: %s", LocalDataActivity.getMainState()));
        description.setText(String.format("Description: %s", LocalDataActivity.getDescription()));
        iconId.setText(LocalDataActivity.getIconId());
        temp.setText(String.format("%s°C", LocalDataActivity.getTemp()));
        feelsLike.setText(LocalDataActivity.getFeelsLike());
        tempMin.setText(LocalDataActivity.getTempMin());
        tempMax.setText(LocalDataActivity.getTempMax());
        pressure.setText(LocalDataActivity.getPressure());
        humidity.setText(LocalDataActivity.getHumidity());
        speed.setText(LocalDataActivity.getSpeed());
        degree.setText(LocalDataActivity.getDegree());
        country.setText(LocalDataActivity.getCountry());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search_location).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        //searchView.setIconifiedByDefault(false); this will expand search view forever
        searchView.setSubmitButtonEnabled(true);//this brings submit button in view
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
            RequestByCityName(cityName,getApplicationContext());
            startActivity(new Intent(getApplicationContext(),WeatherAppActivity.class));
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.select_theme:
                ToggleTheme();
                break;
            case R.id.manage_cities:
                AddOrRemoveCities();
                break;
            case R.id.setting:
                startActivity(new Intent(this, SettingsActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void AddOrRemoveCities() {
    }

    private void ToggleTheme() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
    }
}
