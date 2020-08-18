package com.example.weatherforecast;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class ApiDataRequestActivity extends AppCompatActivity {
    private String _mainState;
    private String _description;
    private String _iconId;
    private String _temp;
    private String _feels_like;
    private String _temp_min;
    private String _temp_max;
    private String _pressure;
    private String _humidity;
    private String _speed;
    private String _degree;
    private String _country;
    private String _cityName;

    ProgressDialog progressDialog;
    TextView tb;

    DataFlowConstructor dataFlowConstructor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_data_request);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();
        dataFlowConstructor=new DataFlowConstructor();
        RequestByCityName("Dibiyapur");
        tb = findViewById(R.id.textView5);
        SetToolBar();
    }

    private void SetToolBar() {
        Toolbar toolbar = findViewById(R.id.customToolbar);
        toolbar.setTitle("Settings");
        setSupportActionBar(toolbar);
    }

    public void RequestByCityName(String cityName) {

        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + cityName + getString(R.string.openWeatherMapApi);
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        // Request a string response from the provided URL.
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONArray weather = response.getJSONArray("weather");
                            JSONObject main_class = response.getJSONObject("main");
                            //tb.setText(response.toString());
                            JSONObject wind = response.getJSONObject("wind");
                            JSONObject sys = response.getJSONObject("sys");

                            JSONObject x = weather.getJSONObject(0);
                            JSONObject descIndex = weather.getJSONObject(0);
                            JSONObject icon = weather.getJSONObject(0);
                            _mainState = x.getString("main");

                            _description = descIndex.getString("description");
                            _iconId = icon.getString("icon");

                            _temp = main_class.getDouble("temp") + "°C";
                            dataFlowConstructor.set_mainState(_temp);
                            _feels_like = main_class.getString("feels_like");
                            _temp_min = main_class.getString("temp_min");
                            _temp_max = main_class.getString("temp_max");
                            _pressure = main_class.getString("pressure") + "";
                            _humidity = main_class.getString("humidity");
                            _speed = wind.getString("speed");
                            _degree = wind.getString("deg");
                            _country = sys.getString("country");
                            _cityName = response.getString("name");
                            tb.setText(String.format("Weather State: %s\nWeather Description: %s\nIconId: %s\n" +
                                            "Temperature: %s\nFeels like: %s\n" +
                                            "Min Temperature: %s\nMax temperature: %s\n" +
                                            "Pressure: %s\n" +
                                            "Humidity: %s\nWind Speed: %s\nWind Angle: %s\nCountry: %s\nCity Name: %s",
                                    _mainState,
                                    _description,
                                    _iconId,
                                    _temp,
                                    _feels_like,
                                    _temp_min,
                                    _temp_max,
                                    _pressure,
                                    _humidity,
                                    _speed,
                                    _degree,
                                    _country,
                                    _cityName));
                            //Update UI
                        } catch (Exception e) {
                            Log.d("Connection Problem", e.toString());
                        }
                        startActivity(new Intent(ApiDataRequestActivity.this,WeatherAppActivity.class));
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(ApiDataRequestActivity.this, "Connection Problem", Toast.LENGTH_LONG).show();
                startActivity(new Intent(ApiDataRequestActivity.this,WeatherAppActivity.class));
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}