package com.example.weatherforecast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        SplashScreenHandling();
    }

    /*Splash Screen Function*/
    private void SplashScreenHandling() {

        View v = getWindow().getDecorView();  //Hiding Status bar
        int ui = View.SYSTEM_UI_FLAG_FULLSCREEN;
        v.setSystemUiVisibility(ui);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                TransitionFunction();
                ApiDataRequest.RequestByCityName("Dibiyapur",getApplicationContext());
                finish();
            }
        }, 3000);
    }

    private void TransitionFunction() {
        startActivity(new Intent(this, WeatherAppActivity.class));
    }
}