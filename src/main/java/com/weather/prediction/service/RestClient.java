package com.weather.prediction.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.prediction.model.WeatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class RestClient {

    @Value("${backend.endpoint}")
    String endpointUrl;

    RestTemplate restTemplate;

    @Autowired
    public RestClient(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public WeatherResponse getWeatherInfo(String city, String appId, int cnt, Class<WeatherResponse> returnClassType) {
        WeatherResponse response = null;
        log.info("came in serviceImpl");

        String url = new StringBuilder(this.endpointUrl)
                .append("q=").append(city).append("&appid=").append(appId).append("&cnt=").append(cnt).toString();

        log.info("request- > {}", url);

        try {
          return restTemplate.getForObject(url, returnClassType);

        }catch (HttpClientErrorException e){
          throw e;
        }
        catch (Exception e) {
            log.error(e.getMessage());
            throw new HttpServerErrorException(HttpStatusCode.valueOf(500));
        }

    }

    private String[] getCodeAndMessage(HttpClientErrorException e) {
        ObjectMapper objectMapper =  new ObjectMapper();
        JsonNode jsonNode = null;
        String [] arr = new String[2];
        try {
            jsonNode = objectMapper.readTree(e.getMessage());
            log.info("jsonNode = > {}",jsonNode);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
        arr[0] = jsonNode.get("code").asText();
        arr[1] = jsonNode.get("message").asText();
      return arr;
    }
}
