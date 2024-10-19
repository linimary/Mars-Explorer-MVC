package com.linimary.marsexplorer.infrastructure.exception;

import com.linimary.marsexplorer.infrastructure.exception.rover.ApiClientException;
import com.linimary.marsexplorer.infrastructure.exception.rover.RoverPhotoNotFoundException;
import com.linimary.marsexplorer.infrastructure.exception.weather.WeatherDataFetchingException;
import com.linimary.marsexplorer.infrastructure.exception.weather.WeatherDataProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler({RoverPhotoNotFoundException.class, WeatherDataProcessingException.class})
  public ResponseEntity<String> handleNotFoundException(RuntimeException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
  }

  @ExceptionHandler({ApiClientException.class, WeatherDataFetchingException.class})
  public ResponseEntity<String> handleInternalServerException(RuntimeException ex) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleGenericException(Exception ex) {
    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body("An error occurred: " + ex.getMessage());
  }
}
