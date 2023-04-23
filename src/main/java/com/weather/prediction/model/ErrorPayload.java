package com.weather.prediction.model;

import lombok.Data;

@Data
public class ErrorPayload {
    private String message;
    private int code;
}
