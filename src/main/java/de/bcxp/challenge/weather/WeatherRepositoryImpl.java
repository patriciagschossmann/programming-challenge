package de.bcxp.challenge.weather;

import java.util.List;

import de.bcxp.challenge.shared.DataLoader;

public class WeatherRepositoryImpl implements WeatherRepository {

  private List<Weather> weatherList;

  public WeatherRepositoryImpl(DataLoader<Weather> weatherDataLoader) {
    weatherList = weatherDataLoader.load();
  }

  public List<Weather> findAll() {
    return weatherList;
  }
}
