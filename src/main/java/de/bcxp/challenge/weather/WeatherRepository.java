package de.bcxp.challenge.weather;

import java.util.List;

public interface WeatherRepository {
  public List<Weather> findAll();
}
