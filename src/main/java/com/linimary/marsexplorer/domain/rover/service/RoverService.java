package com.linimary.marsexplorer.domain.rover.service;

import com.linimary.marsexplorer.domain.rover.dto.RoverPhotoDTO;
import java.util.List;
import reactor.core.publisher.Mono;

public interface RoverService {
  Mono<List<RoverPhotoDTO>> getPhotosByEarthDate(String earthDate);
}
