package com.weather.prediction.service;

import com.weather.prediction.model.WeatherResponse;

public interface WeatherService {
    WeatherResponse getWeather(String city, String appId, int cnt);
}
