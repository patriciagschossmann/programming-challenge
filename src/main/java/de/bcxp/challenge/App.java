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

      WeatherController weatherController = AppFactory.buildWeatherController(weatherFileFormat, weatherSrcPath);
      weatherController.printDayWithSmallestTempSpread();

      // String dayWithSmallestTempSpread = "Someday"; // Your day analysis function
      // call …
      // System.out.printf("Day with smallest temperature spread: %s%n",
      // dayWithSmallestTempSpread);

    } catch (IOException e) {
      String errMsg = "Could not load config file";
      logger.log(Level.SEVERE, errMsg, e);
      throw new IllegalStateException(errMsg, e);
    }
    
    // String countryWithHighestPopulationDensity = "Some country"; // Your
    // population density analysis function call …
    // System.out.printf("Country with highest population density: %s%n",
    // countryWithHighestPopulationDensity);
  }
}
