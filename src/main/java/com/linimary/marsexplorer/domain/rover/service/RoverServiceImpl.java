package com.linimary.marsexplorer.domain.rover.service;

import com.linimary.marsexplorer.domain.rover.dto.RoverPhotoDTO;
import com.linimary.marsexplorer.domain.rover.dto.RoverPhotoResponseDTO;
import com.linimary.marsexplorer.infrastructure.exception.rover.ApiClientException;
import com.linimary.marsexplorer.infrastructure.exception.rover.RoverPhotoNotFoundException;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RoverServiceImpl implements RoverService {

  private final WebClient webClient;

  @Value("${nasa.api.key}")
  private String apiKey;

  @Value("${rover.api.url}")
  private String roverApiUrl;

  public Mono<List<RoverPhotoDTO>> getPhotosByEarthDate(String earthDate) {
    return webClient.get()
        .uri(uriBuilder -> buildUri(uriBuilder, earthDate))
        .retrieve()
        .onStatus(HttpStatusCode::isError, this::handleError)
        .bodyToMono(RoverPhotoResponseDTO.class)
        .flatMap(this::processResponse);
  }

  private URI buildUri(UriBuilder uriBuilder, String earthDate) {
    return uriBuilder
        .path(roverApiUrl)
        .queryParam("earth_date", earthDate)
        .queryParam("api_key", apiKey)
        .build();
  }

  private Mono<Throwable> handleError(ClientResponse response) {
    return response.bodyToMono(String.class)
        .flatMap(errorMessage ->
            Mono.error(new ApiClientException("API request failed: " + errorMessage)));
  }

  private Mono<List<RoverPhotoDTO>> processResponse(RoverPhotoResponseDTO response) {
    if (response.getPhotos() == null || response.getPhotos().isEmpty()) {
      return Mono.error(new RoverPhotoNotFoundException("No photos found for this date."));
    }

    return Mono.just(response.getPhotos().stream()
        .map(photo -> new RoverPhotoDTO(
            String.valueOf(photo.getId()),
            photo.getImgSrc(),
            photo.getEarthDate()))
        .collect(Collectors.toList()));
  }
}