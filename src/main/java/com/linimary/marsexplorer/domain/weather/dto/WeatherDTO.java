package com.linimary.marsexplorer.domain.weather.dto;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
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
public class WeatherDTO {

  private String sol;
  private String date;
  private Double minTemperature;
  private Double maxTemperature;

  public String getFormattedDate() {
    var zonedDateTime = ZonedDateTime.parse(date);
    return zonedDateTime.format(DateTimeFormatter.ofPattern("MMM. dd"));
  }
}

