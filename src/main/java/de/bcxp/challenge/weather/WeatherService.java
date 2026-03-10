package de.bcxp.challenge.weather;

/**
 * Query operations for weather data.
 */
public interface WeatherService {

  /**
   * Finds the day with the smallest temperature spread, computed as maximum
   * temperature minus minimum temperature.
   */
  public String getDayWithSmallestTempSpread();
}
