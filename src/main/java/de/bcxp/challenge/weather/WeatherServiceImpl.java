package de.bcxp.challenge.weather;

import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WeatherServiceImpl implements WeatherService {
  private static final Logger logger = Logger.getLogger(WeatherServiceImpl.class.getName());

  private WeatherRepository weatherRepository;

  public WeatherServiceImpl(WeatherRepository weatherRepository) {
    this.weatherRepository = weatherRepository;
  }

  public int getDayWithSmallestTempSpread() {
    return weatherRepository.findAll().stream()
        .min(Comparator.comparingInt(w -> w.getMaxTempF() - w.getMinTempF()))
        .map(w -> w.getDay())
        .orElseThrow(() -> {
          String errMsg = "No weather data available";
          logger.log(Level.SEVERE, errMsg);
          return new IllegalStateException(errMsg);
        });
  }
}
