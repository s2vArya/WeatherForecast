package com.example.weatherforecast;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public abstract class LocalDataActivity extends AppCompatActivity {

    /**
     * Mintu Giri
     **/
    private static String _mainState;
    private static String _description;
    private static String _iconId;
    private static String _temp;
    private static String _feels_like;
    private static String _temp_min;
    private static String _temp_max;
    private static String _pressure;
    private static String _humidity;
    private static String _speed;
    private static String _degree;
    private static String _country;
    private static String _cityName;


    public LocalDataActivity() {
    }

    public static String getMainState() {
        return _mainState;
    }

    public static String getDescription() {
        return _description;
    }

    public static String getIconId() {
        return _iconId;
    }

    public static String getTemp() {
        return _temp;
    }

    public static String getFeelsLike() {
        return _feels_like;
    }

    public static String getTempMin() {
        return _temp_min;
    }

    public static String getTempMax() {
        return _temp_max;
    }

    public static String getPressure() {
        return _pressure;
    }

    public static String getHumidity() {
        return _humidity;
    }

    public static String getSpeed() {
        return _speed;
    }

    public static String getDegree() {
        return _degree;
    }

    public static String getCountry() {
        return _country;
    }

    public static String getCityName() {
        return _cityName;
    }

    public static void set_mainState(String _mainState) {
        LocalDataActivity._mainState = _mainState;
    }

    public static void set_description(String _description) {
        LocalDataActivity._description = _description;
    }

    public static void set_iconId(String _iconId) {
        LocalDataActivity._iconId = _iconId;
    }

    public static void set_temp(String _temp) {
        LocalDataActivity._temp = _temp;
    }

    public static void set_feels_like(String _feels_like) {
        LocalDataActivity._feels_like = _feels_like;
    }

    public static void set_temp_min(String _temp_min) {
        LocalDataActivity._temp_min = _temp_min;
    }

    public static void set_temp_max(String _temp_max) {
        LocalDataActivity._temp_max = _temp_max;
    }

    public static void set_pressure(String _pressure) {
        LocalDataActivity._pressure = _pressure;
    }

    public static void set_humidity(String _humidity) {
        LocalDataActivity._humidity = _humidity;
    }

    public static void set_speed(String _speed) {
        LocalDataActivity._speed = _speed;
    }

    public static void set_degree(String _degree) {
        LocalDataActivity._degree = _degree;
    }

    public static void set_country(String _country) {
        LocalDataActivity._country = _country;
    }

    public static void set_cityName(String _cityName) {
        LocalDataActivity._cityName = _cityName;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void SaveDataToLocal(String _mainState,
                                String _description,
                                String _iconId,
                                String _temp,
                                String _feels_like,
                                String _temp_min,
                                String _temp_max,
                                String _pressure,
                                String _humidity,
                                String _speed,
                                String _degree,
                                String _country,
                                String _cityName) {

        SharedPreferences pref = getSharedPreferences("Weather Data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putString("MainState", _mainState);
        editor.putString("Description", _description);
        editor.putString("Icon", _iconId);
        editor.putString("Temp", _temp);
        editor.putString("FeelsLike", _feels_like);
        editor.putString("MinTemp", _temp_min);
        editor.putString("MaxTemp", _temp_max);
        editor.putString("Pressure", _pressure);
        editor.putString("Humidity", _humidity);
        editor.putString("WindSpeed", _speed);
        editor.putString("WindAngle", _degree);
        editor.putString("Country", _country);
        editor.putString("CityName", _cityName);
        editor.apply();// it's called confirmation... and save the data...it must used when edit data
    }

    public void GetDataFromLocal() {
        SharedPreferences pref = getSharedPreferences("Weather Data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        String mainState = pref.getString("MainState", null);
        String description = pref.getString("Description", null);
        String icon = pref.getString("Icon", null);
        String temp = pref.getString("Temp", null);
        String feelsLike = pref.getString("FeelsLike", null);
        String minTemp = pref.getString("MinTemp", null);
        String maxTemp = pref.getString("MaxTemp", null);
        String pressure = pref.getString("Pressure", null);
        String humidity = pref.getString("Humidity", null);
        String windSpeed = pref.getString("WindSpeed", null);
        String windAngle = pref.getString("WindAngle", null);
        String country = pref.getString("Country", null);
        String cityName = pref.getString("CityName", null);
        set_mainState(mainState);
        set_description(description);
        set_iconId(icon);
        set_temp(temp);
        set_feels_like(feelsLike);
        set_temp_min(minTemp);
        set_temp_max(maxTemp);
        set_pressure(pressure);
        set_humidity(humidity);
        set_speed(windSpeed);
        set_degree(windAngle);
        set_country(country);
        set_cityName(cityName);
        editor.apply();
    }

    public void RemoveLocalData() {
        SharedPreferences pref = getSharedPreferences("Weather Data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.remove("MainState");
        editor.remove("Description");
        editor.remove("Icon");
        editor.remove("Temp");
        editor.remove("FeelsLike");
        editor.remove("MinTemp");
        editor.remove("MaxTemp");
        editor.remove("Pressure");
        editor.remove("Humidity");
        editor.remove("WindSpeed");
        editor.remove("WindAngle");
        editor.remove("Country");
        editor.remove("CityName");
        editor.apply();
    }

    public static void registerPref(Context context, SharedPreferences.OnSharedPreferenceChangeListener listener) {
        SharedPreferences pref = context.getSharedPreferences("Weather Data", Context.MODE_PRIVATE);
        pref.registerOnSharedPreferenceChangeListener(listener);
    }

    public static void unregisterPref(Context context, SharedPreferences.OnSharedPreferenceChangeListener listener) {
        SharedPreferences pref = context.getSharedPreferences("Weather Data", Context.MODE_PRIVATE);
        pref.unregisterOnSharedPreferenceChangeListener(listener);
    }
}