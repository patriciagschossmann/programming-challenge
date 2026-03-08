package de.bcxp.challenge.factories;

import java.lang.UnsupportedOperationException;

import java.util.logging.Level;
import java.util.logging.Logger;

import de.bcxp.challenge.weather.WeatherController;
import de.bcxp.challenge.weather.WeatherCSVLoader;
import de.bcxp.challenge.weather.WeatherDataLoader;
import de.bcxp.challenge.weather.WeatherRepository;
import de.bcxp.challenge.weather.WeatherRepositoryImpl;
import de.bcxp.challenge.weather.WeatherService;
import de.bcxp.challenge.weather.WeatherServiceImpl;

public class AppFactory {
  private static final Logger logger = Logger.getLogger(AppFactory.class.getName());

  public static WeatherController buildWeatherController(String fileFormat, String srcPath) {
    WeatherDataLoader weatherDataLoader;
    switch (fileFormat) {
      case "csv":
        weatherDataLoader = new WeatherCSVLoader(srcPath);
        break;
      default:
        String errMsg = "Unsupported file format: " + fileFormat + ". Supported: csv";
        logger.log(Level.SEVERE, errMsg);
        throw new UnsupportedOperationException(errMsg);
    }

    WeatherRepository weatherRepository = new WeatherRepositoryImpl(weatherDataLoader);

    WeatherService weatherService = new WeatherServiceImpl(weatherRepository);

    WeatherController weatherController = new WeatherController(weatherService);

    return weatherController;
  }
}
