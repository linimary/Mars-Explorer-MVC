package com.linimary.marsexplorer.infrastructure.exception.weather;

public class WeatherDataFetchingException extends RuntimeException {
  public WeatherDataFetchingException(String message, Throwable cause) {
    super(message, cause);
  }
}