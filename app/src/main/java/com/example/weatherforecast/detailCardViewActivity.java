package com.example.weatherforecast;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class detailCardViewActivity extends AppCompatActivity {
    private ImageView mCardWeatherStatusImage;
    private TextView mCardWeatherStatusText;
    private TextView mCardWeatherStatusTemperature;
    private TextView mSeeMoreTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_card_view);
        mCardWeatherStatusImage = findViewById(R.id.cardWeatherStatusImage);
        mCardWeatherStatusText = findViewById(R.id.cardWeatherStatusText);
        mCardWeatherStatusTemperature = findViewById(R.id.cardWeatherStatusTemperature);
        mSeeMoreTxt = findViewById(R.id.seeMoreTxt);

    }
}