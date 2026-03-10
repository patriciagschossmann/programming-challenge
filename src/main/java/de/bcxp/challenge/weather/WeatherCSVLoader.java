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
    return new WeatherCSVLoader(() -> OpenCSVParser.parseByRow(srcPath, separator));
  }

  public List<Weather> load() {
    List<Weather> weatherList = new ArrayList<>();
    for (int i = 0; i < rows.size(); i++) {
      try {
        String[] row = rows.get(i);
        weatherList.add(new Weather(row[0], Integer.parseInt(row[1]), Integer.parseInt(row[2])));
      } catch (NumberFormatException e) {
        logger.log(Level.WARNING, "Invalid number at row " + (i + 2) + ", skipping row.", e.getMessage());
      }
    }
    return weatherList;
  }
}
