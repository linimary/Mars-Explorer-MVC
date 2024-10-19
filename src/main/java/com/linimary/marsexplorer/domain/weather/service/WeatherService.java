package com.linimary.marsexplorer.domain.weather.service;

import com.linimary.marsexplorer.domain.weather.dto.WeatherDTO;
import java.util.List;
import reactor.core.publisher.Mono;

public interface WeatherService {

  Mono<List<WeatherDTO>> getWeatherData();
}
