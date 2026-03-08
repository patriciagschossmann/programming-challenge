package de.bcxp.challenge.factories;

import java.lang.UnsupportedOperationException;

import java.util.logging.Level;
import java.util.logging.Logger;

import de.bcxp.challenge.countries.CountryController;
import de.bcxp.challenge.countries.CountryCSVLoader;
import de.bcxp.challenge.countries.CountryDataLoader;
import de.bcxp.challenge.countries.CountryRepository;
import de.bcxp.challenge.countries.CountryRepositoryImpl;
import de.bcxp.challenge.countries.CountryService;
import de.bcxp.challenge.countries.CountryServiceImpl;

import de.bcxp.challenge.weather.WeatherController;
import de.bcxp.challenge.weather.WeatherCSVLoader;
import de.bcxp.challenge.weather.WeatherDataLoader;
import de.bcxp.challenge.weather.WeatherRepository;
import de.bcxp.challenge.weather.WeatherRepositoryImpl;
import de.bcxp.challenge.weather.WeatherService;
import de.bcxp.challenge.weather.WeatherServiceImpl;

public class AppFactory {
  private static final Logger logger = Logger.getLogger(AppFactory.class.getName());

  public static WeatherController buildWeatherController(String fileFormat, String srcPath, char separator) {
    WeatherDataLoader weatherDataLoader;
    switch (fileFormat) {
      case "csv":
        weatherDataLoader = new WeatherCSVLoader(srcPath, separator);
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

  public static CountryController buildCountryController(String fileFormat, String srcPath, char separator) {
    CountryDataLoader countryDataLoader;
    switch (fileFormat) {
      case "csv":
        countryDataLoader = new CountryCSVLoader(srcPath, separator);
        break;
      default:
        String errMsg = "Unsupported file format: " + fileFormat + ". Supported: csv";
        logger.log(Level.SEVERE, errMsg);
        throw new UnsupportedOperationException(errMsg);
    }

    CountryRepository countryRepository = new CountryRepositoryImpl(countryDataLoader);

    CountryService countryService = new CountryServiceImpl(countryRepository);

    CountryController countryController = new CountryController(countryService);

    return countryController;
  }
}
