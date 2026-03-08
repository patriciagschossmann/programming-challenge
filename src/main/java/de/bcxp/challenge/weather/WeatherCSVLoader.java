package de.bcxp.challenge.weather;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.bcxp.challenge.parser.OpenCSVParser;

public class WeatherCSVLoader extends WeatherDataLoader {

  private static final Logger logger = Logger.getLogger(WeatherCSVLoader.class.getName());

  public WeatherCSVLoader(String srcPath, char separator) {
    super(srcPath, separator);
  }

  public List<Weather> load() {
    List<Weather> weatherList = new ArrayList<>();
    OpenCSVParser.parseByLine(srcPath, separator, (lineNumber, line) -> {
      try {
        weatherList.add(new Weather(Integer.parseInt(line[0]), Integer.parseInt(line[1]), Integer.parseInt(line[2])));
      } catch (NumberFormatException e) {
        logger.log(Level.WARNING, "Invalid number at line " + lineNumber + ", skipping line.", e.getMessage());
      }
    });
    return weatherList;
  }
}
