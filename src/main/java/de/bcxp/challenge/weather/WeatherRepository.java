package de.bcxp.challenge.weather;

import java.util.List;

/**
 * Data access for weather records.
 */
public interface WeatherRepository {

  /**
   * Returns all weather records available from the underlying data source.
   */
  public List<Weather> findAll();
}
