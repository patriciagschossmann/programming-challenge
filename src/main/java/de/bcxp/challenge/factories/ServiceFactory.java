package de.bcxp.challenge.factories;

import java.lang.UnsupportedOperationException;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.bcxp.challenge.config.AppConfig;
import de.bcxp.challenge.config.ConfigKeys;

import de.bcxp.challenge.countries.Country;
import de.bcxp.challenge.countries.CountryCSVLoader;
import de.bcxp.challenge.countries.CountryRepository;
import de.bcxp.challenge.countries.CountryRepositoryImpl;
import de.bcxp.challenge.countries.CountryService;
import de.bcxp.challenge.countries.CountryServiceImpl;

import de.bcxp.challenge.shared.DataLoader;

import de.bcxp.challenge.weather.Weather;
import de.bcxp.challenge.weather.WeatherCSVLoader;
import de.bcxp.challenge.weather.WeatherRepository;
import de.bcxp.challenge.weather.WeatherRepositoryImpl;
import de.bcxp.challenge.weather.WeatherService;
import de.bcxp.challenge.weather.WeatherServiceImpl;

public class ServiceFactory {
  private static final Logger logger = Logger.getLogger(ServiceFactory.class.getName());

  public static WeatherService buildWeatherService(AppConfig config) {
    String fileFormat = config.get(ConfigKeys.WeatherConfig.FILE_FORMAT);
    String srcPath = config.get(ConfigKeys.WeatherConfig.SRC_PATH);

    DataLoader<Weather> weatherDataLoader;
    switch (fileFormat) {
      case ConfigKeys.SupportedFormat.CSV:
        char separator = config.get(ConfigKeys.WeatherConfig.CSV_SEPARATOR).charAt(0);
        weatherDataLoader = new WeatherCSVLoader(srcPath, separator);
        break;
      default:
        String errMsg = "Unsupported file format: " + fileFormat + ". Supported: csv";
        logger.log(Level.SEVERE, errMsg);
        throw new UnsupportedOperationException(errMsg);
    }

    WeatherRepository weatherRepository = new WeatherRepositoryImpl(weatherDataLoader);

    WeatherService weatherService = new WeatherServiceImpl(weatherRepository);

    return weatherService;
  }

  public static CountryService buildCountryService(AppConfig config) {
    String fileFormat = config.get(ConfigKeys.CountriesConfig.FILE_FORMAT);
    String srcPath = config.get(ConfigKeys.CountriesConfig.SRC_PATH);

    DataLoader<Country> countryDataLoader;
    switch (fileFormat) {
      case ConfigKeys.SupportedFormat.CSV:
        char separator = config.get(ConfigKeys.CountriesConfig.CSV_SEPARATOR).charAt(0);
        countryDataLoader = new CountryCSVLoader(srcPath, separator);
        break;
      default:
        String errMsg = "Unsupported file format: " + fileFormat + ". Supported: csv";
        logger.log(Level.SEVERE, errMsg);
        throw new UnsupportedOperationException(errMsg);
    }

    CountryRepository countryRepository = new CountryRepositoryImpl(countryDataLoader);

    CountryService countryService = new CountryServiceImpl(countryRepository);

    return countryService;
  }
}
