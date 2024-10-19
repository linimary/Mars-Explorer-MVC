package com.linimary.marsexplorer.web;

import com.linimary.marsexplorer.domain.rover.dto.RoverPhotoDTO;
import com.linimary.marsexplorer.domain.rover.service.RoverService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class RoverController {

  private final RoverService roverService;

  @GetMapping("/data-picker")
  public String showForm() {
    return "rover-photos";
  }

  @GetMapping("/photos")
  public String getPhotos(@RequestParam("earthDate") String earthDate, Model model) {
    List<RoverPhotoDTO> photos = roverService.getPhotosByEarthDate(earthDate).block();
    model.addAttribute("photos", photos);
    return "rover-photos";
  }
}
