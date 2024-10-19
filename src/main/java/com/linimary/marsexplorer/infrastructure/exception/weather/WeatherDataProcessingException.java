package com.linimary.marsexplorer.infrastructure.exception.weather;

public class WeatherDataProcessingException extends RuntimeException {
  public WeatherDataProcessingException(String message, Throwable cause) {
    super(message, cause);
  }
}
