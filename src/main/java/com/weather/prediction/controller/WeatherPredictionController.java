package com.weather.prediction.controller;

import com.weather.prediction.model.ErrorPayload;
import com.weather.prediction.model.WeatherResponse;
import com.weather.prediction.service.WeatherService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class WeatherPredictionController {

    @Autowired
    WeatherService weatherService;

    @ApiResponses({
            @ApiResponse(responseCode = "200", description ="Return Weather information", content = {
                    @Content(schema = @Schema(implementation = WeatherResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Bad Request, required request parameters might be missed", content = {
                    @Content(schema = @Schema(implementation = ErrorPayload.class),mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "The Weather details with given city was not found.", content = {
                    @Content(schema = @Schema(implementation = ErrorPayload.class),mediaType = "application/json") })
    })
    @Parameters({
            @Parameter(name = "city", description = "City name"),
            @Parameter(name = "key", description = "API key"),
            @Parameter(name = "numofdays", description = "Number of days")
    })
    @GetMapping("/getPrediction")
    public ResponseEntity<?> getPredictions(
            @RequestParam(value = "city") String city,
            @RequestParam (value = "key") String appid,
            @RequestParam(value = "numofdays") String numofdays){

        log.info("request parameters : city = {}, appid = {}, numofdays = {}", city,appid,numofdays);
        WeatherResponse response= weatherService.getWeather(city, appid, Integer.parseInt(numofdays));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
