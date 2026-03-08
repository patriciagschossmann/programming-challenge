package de.bcxp.challenge.weather;

import java.util.List;

public class WeatherRepositoryImpl implements WeatherRepository {

  private List<Weather> weatherList;

  public WeatherRepositoryImpl(WeatherDataLoader weatherDataLoader) {
    weatherList = weatherDataLoader.load();
  }

  public List<Weather> findAll() {
    return weatherList;
  }
}
