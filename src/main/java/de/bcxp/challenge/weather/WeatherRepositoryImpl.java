package de.bcxp.challenge.weather;

import java.util.List;

import de.bcxp.challenge.shared.DataLoader;

public class WeatherRepositoryImpl implements WeatherRepository {

  private List<Weather> weatherList;

  /**
   * Creates a {@code WeatherRepository} by eagerly loading all weather records
   * from the given {@link DataLoader} into memory.
   */
  public WeatherRepositoryImpl(DataLoader<String[], Weather> weatherDataLoader) {
    weatherList = weatherDataLoader.load();
  }

  public List<Weather> findAll() {
    return weatherList;
  }
}
