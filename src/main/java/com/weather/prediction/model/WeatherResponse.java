package com.weather.prediction.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class WeatherResponse {
 private String cod;
 private String message;
 private int cnt;
 private ArrayList<WeatherDetails> list = new ArrayList <WeatherDetails> ();
 private City city;

}