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

    /**Mintu Giri**/
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
    public  static  String getMainState()
    {
        return _mainState;
    }
    public  static  String getDescription()
    {
        return _description;
    }
    public  static  String getIconId()
    {
        return _iconId;
    }
    public  static  String getTemp()
    {
        return _temp;
    }
    public  static  String getFeelsLike()
    {
        return _feels_like;
    }
    public  static  String getTempMin()
    {
        return _temp_min;
    }
    public  static  String getTempMax()
    {
        return _temp_max;
    }
    public  static  String getPressure()
    {
        return _pressure;
    }
    public  static  String getHumidity()
    {
        return _humidity;
    }
    public  static  String getSpeed()
    {
        return _speed;
    }
    public  static  String getDegree()
    {
        return _degree;
    }
    public  static  String getCountry()
    {
        return _country;
    }
    public  static  String getCityName()
    {
        return _cityName;
    }
    /**Mintu Giri**/

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_data_request);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();
        RequestByCityName("Dibiyapur");
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

                            _temp = Math.round(main_class.getDouble("temp") - 273) + "°C";
                            _feels_like = Math.round(main_class.getDouble("feels_like")-273) + "°C";
                            _temp_min = Math.round(main_class.getDouble("temp_min")-273) + "°C";
                            _temp_max = Math.round(main_class.getDouble("temp_max")-273) + "°C";
                            _pressure = Math.round(main_class.getDouble("pressure")) + " Pa";
                            _humidity = Math.round(main_class.getDouble("humidity")) + " g/m\u00B3";
                            _speed = Math.rint(wind.getDouble("speed")) + " m/sec";
                            _degree = Math.rint(wind.getDouble("deg")) +"°";
                            _country = sys.getString("country");
                            _cityName = response.getString("name");
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