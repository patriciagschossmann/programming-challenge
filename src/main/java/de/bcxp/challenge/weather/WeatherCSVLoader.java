package de.bcxp.challenge.weather;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.bcxp.challenge.parser.OpenCSVParser;
import de.bcxp.challenge.shared.DataLoader;

public class WeatherCSVLoader extends DataLoader<Weather> {

  private static final Logger logger = Logger.getLogger(WeatherCSVLoader.class.getName());

  public WeatherCSVLoader(Supplier<List<String[]>> source) {
    super(source);
  }

  public static WeatherCSVLoader fromCSV(String srcPath, char separator) {
    return new WeatherCSVLoader(() -> OpenCSVParser.parseByLine(srcPath, separator));
  }

  public List<Weather> load() {
    List<Weather> weatherList = new ArrayList<>();
    for (int i = 0; i < lines.size(); i++) {
      try {
        String[] line = lines.get(i);
        weatherList.add(new Weather(line[0], Integer.parseInt(line[1]), Integer.parseInt(line[2])));
      } catch (NumberFormatException e) {
        logger.log(Level.WARNING, "Invalid number at line " + (i + 2) + ", skipping line.", e.getMessage());
      }
    }
    return weatherList;
  }
}
