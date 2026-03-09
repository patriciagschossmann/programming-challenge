package de.bcxp.challenge;

import java.util.Properties;

import de.bcxp.challenge.config.AppConfig;
import de.bcxp.challenge.countries.CountryService;
import de.bcxp.challenge.factories.ServiceFactory;
import de.bcxp.challenge.weather.WeatherService;

/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 */
public final class App {

  /**
   * This is the main entry method of your program.
   * 
   * @param args The CLI arguments passed
   */
  public static void main(String... args) {
    Properties props = new Properties();
    AppConfig appConfig = new AppConfig(props);
    appConfig.load("de/bcxp/challenge/config.properties");

    WeatherService weatherService = ServiceFactory.buildWeatherService(appConfig);

    String dayWithSmallestTempSpread = weatherService.getDayWithSmallestTempSpread();
    System.out.printf("Day with smallest temperature spread: %s%n", dayWithSmallestTempSpread);

    CountryService countryService = ServiceFactory.buildCountryService(appConfig);
    String countryWithHighestPopulationDensity = countryService.getCountryWithHighestPopulationDensity();
    System.out.printf("Country with highest population density: %s%n", countryWithHighestPopulationDensity);
  }
}
