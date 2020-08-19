package com.example.weatherforecast;

public class DataFlowConstructor {

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

    public DataFlowConstructor() {

    }

    public String get_mainState() {
        return _mainState;
    }

    public void set_mainState(String _mainState) {
        this._mainState = _mainState;
    }

    public String get_description() {
        return _description;
    }

    public void set_description(String _description) {
        this._description = _description;
    }

    public String get_iconId() {
        return _iconId;
    }

    public void set_iconId(String _iconId) {
        this._iconId = _iconId;
    }

    public String get_temp() {
        return _temp;
    }

    public void set_temp(String _temp) {
        this._temp = _temp;
    }

    public String get_feels_like() {
        return _feels_like;
    }

    public void set_feels_like(String _feels_like) {
        this._feels_like = _feels_like;
    }

    public String get_temp_min() {
        return _temp_min;
    }

    public void set_temp_min(String _temp_min) {
        this._temp_min = _temp_min;
    }

    public String get_temp_max() {
        return _temp_max;
    }

    public void set_temp_max(String _temp_max) {
        this._temp_max = _temp_max;
    }

    public String get_pressure() {
        return _pressure;
    }

    public void set_pressure(String _pressure) {
        this._pressure = _pressure;
    }

    public String get_humidity() {
        return _humidity;
    }

    public void set_humidity(String _humidity) {
        this._humidity = _humidity;
    }

    public String get_speed() {
        return _speed;
    }

    public void set_speed(String _speed) {
        this._speed = _speed;
    }

    public String get_degree() {
        return _degree;
    }

    public void set_degree(String _degree) {
        this._degree = _degree;
    }

    public String get_country() {
        return _country;
    }

    public void set_country(String _country) {
        this._country = _country;
    }

    public String get_cityName() {
        return _cityName;
    }

    public void set_cityName(String _cityName) {
        this._cityName = _cityName;
    }


    public DataFlowConstructor(String _mainState, String _description, String _iconId, String _temp, String _feels_like, String _temp_min, String _temp_max, String _pressure, String _humidity, String _speed, String _degree, String _country, String _cityName) {
        this._mainState = _mainState;
        this._description = _description;
        this._iconId = _iconId;
        this._temp = _temp;
        this._feels_like = _feels_like;
        this._temp_min = _temp_min;
        this._temp_max = _temp_max;
        this._pressure = _pressure;
        this._humidity = _humidity;
        this._speed = _speed;
        this._degree = _degree;
        this._country = _country;
        this._cityName = _cityName;
    }
}
