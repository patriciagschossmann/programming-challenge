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

  /**
   * @param source returns raw CSV rows (header excluded) representing
   *               {@link Weather} records.
   *               Expected column order (0-indexed):
   *               {@code 0=day, 1=maxTempF, 2=minTempF}
   */
  public WeatherCSVLoader(Supplier<List<String[]>> source) {
    super(source);
  }

  /**
   * Creates a {@code WeatherCSVLoader} by parsing a classpath CSV file.
   *
   * @param srcPath   path to the weather CSV file
   * @param separator field delimiter used in the file
   */
  public static WeatherCSVLoader fromCSV(String srcPath, char separator) {
    return new WeatherCSVLoader(() -> OpenCSVParser.parseByRow(srcPath, separator));
  }

  /**
   * Converts the raw CSV rows into a list of {@link Weather} objects.
   * rows with non-numeric temperature values are skipped and logged.
   */
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
