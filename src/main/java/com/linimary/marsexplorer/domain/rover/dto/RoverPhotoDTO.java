package com.linimary.marsexplorer.domain.rover.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class RoverPhotoDTO {
  private String id;

  @JsonProperty("img_src")
  private String imgSrc;

  @JsonProperty("earth_date")
  private String earthDate;
}
