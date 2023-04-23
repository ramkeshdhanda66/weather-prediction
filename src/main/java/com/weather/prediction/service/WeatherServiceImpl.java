package com.weather.prediction.service;

import com.weather.prediction.model.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    RestClient restClient;


    @Override
    public WeatherResponse getWeather(String city, String appId, int cnt) {
        WeatherResponse response= restClient.getWeatherInfo(city, appId, cnt, WeatherResponse.class);

        return transformResponse(response);

    }

    private WeatherResponse transformResponse(WeatherResponse response) {
        String weatherType = response.getList().get(0).getWeather().get(0).getMain();
        float windSpeed = response.getList().get(0).getWind().getSpeed();
        float temp = response.getList().get(0).getMain().getTemp();
        if (weatherType.contains("Rain")){
            response.setMessage("Carry umbrella");
        }else if (weatherType.contains("Thunderstorms")){
            response.setMessage("Don’t step out! A Storm is brewing!");
        }else if (windSpeed> 10){
            response.setMessage("It’s too windy, watch out!");
        }
        return response;
    }
}
//Develop, test and deploy a micro service to show the output of a city's (to be taken as an input parameter) next 3 days high and low temperatures. If rain is predicted in next 3 days or temperature goes above 40 degree Celsius then mention 'Carry umbrella' or 'Use sunscreen lotion' respectively in the output, for that day;
//        Demonstrate adding additional conditions, with the least code changes & deployment:
//
//        1. In case of high winds (i.e.,) Wind > 10mph, mention “It’s too windy, watch out!”
//        2. In case of Thunderstorms, mention “Don’t step out! A Storm is brewing!”