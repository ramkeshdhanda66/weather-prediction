package com.weather.prediction.model;

import lombok.Data;

@Data
public class Main {
    private float temp;
    private float feels_like;
    private float temp_min;
    private float temp_max;
    private int pressure;
    private int sea_level;
    private int grnd_level;
    private int humidity;
    private float temp_kf;
}
