package com.weather.prediction;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
public class WeatherPredictionApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherPredictionApplication.class, args);
    }

//    @Bean
//    public OpenAPI customOpenAPI(@Value("${spring.application.description}") String appDesciption, @Value("${spring.application.version}") String appVersion) {
//        return new OpenAPI().info(new Info()
//						.title("Weather Prediction API")
//                        .version(appVersion)
//                        .description(appDesciption)
//                        .termsOfService("http://swagger.io/terms/")
//                        .license(new License().name("Apache 2.0").url("http://springdoc.org"))
//        );
//    }
}
