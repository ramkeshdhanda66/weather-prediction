package com.weather.prediction.exception;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.prediction.model.ErrorPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class})
    protected ResponseEntity<ErrorPayload> handleConflict(RuntimeException ex, WebRequest request) {
       return handleException(ex, ex.getMessage(), HttpStatus.CONFLICT, new HttpHeaders(),  request);
    }

    @ExceptionHandler(value = HttpClientErrorException.class)
    private ResponseEntity<ErrorPayload> handleHttpClientErrorException(HttpClientErrorException ex) throws JsonProcessingException {

        String message = ex.getMessage();
        String jsonMessage = message.substring(message.indexOf('{'), message.indexOf('}')+1);
        ObjectMapper objectMapper = new ObjectMapper();
        Map map = objectMapper.readValue(jsonMessage, Map.class);
        message = map.get("message").toString();
        return handleException(ex, message, HttpStatus.valueOf(ex.getStatusCode().value()), null, null);

    }
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    private ResponseEntity<ErrorPayload> handleMissingServletRequestParameterException(Exception ex) {
        log.error("in MissingServletRequestParameterException {} ", ex.getMessage());
        return handleException(ex, ex.getMessage(),HttpStatus.BAD_REQUEST, null, null);
    }

    @ExceptionHandler(value = Exception.class)
    private ResponseEntity<ErrorPayload> handleException(Exception ex) {
        return handleException(ex, ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR, null, null);
    }
    private ResponseEntity<ErrorPayload> handleException(Exception ex, String message, HttpStatus httpStatus, HttpHeaders httpHeaders, WebRequest request) {
        ErrorPayload errorResponse = new ErrorPayload();
        errorResponse.setCode(httpStatus.value());
        errorResponse.setMessage(message);
        return new ResponseEntity<>(errorResponse,httpStatus);
    }
}
