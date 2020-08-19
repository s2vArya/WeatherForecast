package com.example.weatherforecast;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class ApiDataRequest extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static void RequestByCityName(String cityName, final Context context) {

        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + cityName +"&APPID=0466c2473f115a4d226c8ce8b6280210";
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);

        // Request a string response from the provided URL.
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray weather = response.getJSONArray("weather");
                            JSONObject main_class = response.getJSONObject("main");
                            JSONObject wind = response.getJSONObject("wind");
                            JSONObject sys = response.getJSONObject("sys");

                            JSONObject x = weather.getJSONObject(0);
                            JSONObject descIndex = weather.getJSONObject(0);
                            JSONObject icon = weather.getJSONObject(0);
                            _mainState = x.getString("main");
                            _description = descIndex.getString("description");
                            _iconId = icon.getString("icon");

                            _temp = Math.round(main_class.getDouble("temp") - 273) + "";
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
                            Log.e("Chal rha hai Bhai", _description);
                        } catch (Exception e) {
                            Log.d("Connection Problem", e.toString());
                        }
                        //Update UI
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Connection Problem", Toast.LENGTH_LONG).show();
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}