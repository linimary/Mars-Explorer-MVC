package com.linimary.marsexplorer.web;

import com.linimary.marsexplorer.domain.weather.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
public class WeatherController {

  private final WeatherService weatherService;

  @GetMapping("/weather")
  public Mono<String> getWeatherData(Model model) {
    return weatherService.getWeatherData()
        .doOnNext(weatherData -> model.addAttribute("weatherData", weatherData))
        .then(Mono.just("mars-weather"));
  }
}
