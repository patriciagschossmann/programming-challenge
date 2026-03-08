package de.bcxp.challenge;

/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 */

import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.bcxp.challenge.factories.AppFactory;
import de.bcxp.challenge.weather.WeatherController;
import de.bcxp.challenge.countries.CountryController;

public final class App {

  /**
   * This is the main entry method of your program.
   * 
   * @param args The CLI arguments passed
   */
  private static final Logger logger = Logger.getLogger(App.class.getName());

  public static void main(String... args) {

    Properties prop = new Properties();
    try (InputStream input = App.class.getResourceAsStream("config.properties")) {

      if (input == null) {
        String errMsg = "Config file not found: config.properties";
        logger.log(Level.SEVERE, errMsg);
        throw new IllegalStateException(errMsg);
      }

      prop.load(input);

      String weatherFileFormat = prop.getProperty("weather.fileFormat");
      String weatherSrcPath = prop.getProperty("weather.srcPath");
      char weatherSeparator = prop.getProperty("weather.separator").charAt(0);

      WeatherController weatherController = AppFactory.buildWeatherController(weatherFileFormat, weatherSrcPath, weatherSeparator);
      weatherController.printDayWithSmallestTempSpread();

      // String dayWithSmallestTempSpread = "Someday"; // Your day analysis function
      // call …
      // System.out.printf("Day with smallest temperature spread: %s%n",
      // dayWithSmallestTempSpread);

      String countriesFileFormat = prop.getProperty("countries.fileFormat");
      String countriesSrcPath = prop.getProperty("countries.srcPath");
      char countriesSeparator = prop.getProperty("countries.separator").charAt(0);

      CountryController countryController = AppFactory.buildCountryController(countriesFileFormat, countriesSrcPath, countriesSeparator);
      countryController.printCountryWithHighestPopulationDensity();

      // String countryWithHighestPopulationDensity = "Some country"; // Your
      // population density analysis function call …
      // System.out.printf("Country with highest population density: %s%n",
      // countryWithHighestPopulationDensity);
    } catch (IOException e) {
      String errMsg = "Could not load config file";
      logger.log(Level.SEVERE, errMsg, e);
      throw new IllegalStateException(errMsg, e);
    }
  }
}
