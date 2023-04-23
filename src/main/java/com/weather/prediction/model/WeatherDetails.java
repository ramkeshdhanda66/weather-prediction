package com.weather.prediction.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
public class WeatherDetails {
    private int dt;
    private Main main;
    private ArrayList< Weather > weather = new ArrayList < Weather > ();
    private Clouds clouds;
    private Wind wind;
    private int visibility;
    private float pop;
    private Sys sys;
    private String dt_txt;
}
