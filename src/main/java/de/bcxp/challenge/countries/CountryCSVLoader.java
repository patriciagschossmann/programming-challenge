package de.bcxp.challenge.countries;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.bcxp.challenge.parser.OpenCSVParser;
import de.bcxp.challenge.shared.DataLoader;

public class CountryCSVLoader extends DataLoader<Country> {

  private static final Logger logger = Logger.getLogger(CountryCSVLoader.class.getName());

  /**
   * @param source returns raw CSV rows (header excluded) representing
   *               {@link Country} records.
   *               Expected column order (0-indexed):
   *               {@code 0=name, 3=population, 4=areaSqKm}
   */
  public CountryCSVLoader(Supplier<List<String[]>> source) {
    super(source);
  }

  /**
   * Creates a {@code CountryCSVLoader} by parsing a classpath CSV file.
   *
   * @param srcPath   path to the countries CSV file
   * @param separator field delimiter used in the file
   */
  public static CountryCSVLoader fromCSV(String srcPath, char separator) {
    return new CountryCSVLoader(() -> OpenCSVParser.parseByRow(srcPath, separator));
  }

  /**
   * Converts the raw CSV rows into a list of {@link Country} objects.
   * rows that contain non-numeric population or area values are skipped and
   * logged.
   */
  public List<Country> load() {
    List<Country> countryList = new ArrayList<>();
    for (int i = 0; i < rows.size(); i++) {
      try {
        String[] row = rows.get(i);
        countryList.add(new Country(row[0], Integer.parseInt(row[3]), Integer.parseInt(row[4])));
      } catch (NumberFormatException e) {
        logger.log(Level.WARNING, "Invalid number at row " + (i + 2) + ", skipping row.", e.getMessage());
      }
    }
    return countryList;
  }
}
