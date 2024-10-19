package com.linimary.marsexplorer.domain.weather.service;

import com.linimary.marsexplorer.domain.weather.dto.WeatherDTO;
import com.linimary.marsexplorer.infrastructure.exception.weather.WeatherDataFetchingException;
import com.linimary.marsexplorer.infrastructure.exception.weather.WeatherDataProcessingException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

  private final WebClient webClient;

  @Value("${weather.api.uri}")
  private String weatherApiUri;

  @Override
  public Mono<List<WeatherDTO>> getWeatherData() {
    return getWeatherDataFromApi()
        .map(this::mapToWeatherDTOs)
        .onErrorMap(e -> new WeatherDataProcessingException("Error processing weather data", e));
  }

  private Mono<Map<String, Object>> getWeatherDataFromApi() {
    return webClient.get()
        .uri(weatherApiUri)
        .retrieve()
        .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
        .onErrorMap(e -> new WeatherDataFetchingException("Error fetching weather data", e));
  }

  private List<WeatherDTO> mapToWeatherDTOs(Map<String, Object> data) {
    return data.entrySet().stream()
        .filter(entry -> entry.getValue() instanceof Map)
        .filter(entry -> !entry.getKey().equals("validity_checks"))
        .map(this::mapToWeatherDTO)
        .collect(Collectors.toList());
  }

  private WeatherDTO mapToWeatherDTO(Map.Entry<String, Object> entry) {
    String sol = entry.getKey();
    Map<String, Object> solData = (Map<String, Object>) entry.getValue();
    String date = (String) solData.get("First_UTC");

    Map<String, Object> at = (Map<String, Object>) solData.get("AT");
    Double minTemperature = (at != null && at.containsKey("mn")) ? (Double) at.get("mn") : null;
    Double maxTemperature = (at != null && at.containsKey("mx")) ? (Double) at.get("mx") : null;

    return new WeatherDTO(sol, date, minTemperature, maxTemperature);
  }
}