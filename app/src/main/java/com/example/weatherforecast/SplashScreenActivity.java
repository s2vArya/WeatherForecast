package com.example.weatherforecast;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreenActivity extends ApiDataRequest {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        SplashScreenHandling();
    }

    /*Splash Screen Function*/
    private void SplashScreenHandling() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                GetDataFromLocal();
                checkSharedPref();
                finish();
            }
        }, 3000);
    }

    private void checkSharedPref() {
        if (LocalDataActivity.getCityName() == null) {
            RequestByCityName("delhi", getApplicationContext());
        } else {
            RequestByCityName(LocalDataActivity.getCityName(), getApplicationContext());
        }
        TransitionFunction();
    }

    private void TransitionFunction() {
        startActivity(new Intent(this, WeatherAppActivity.class));
    }
}