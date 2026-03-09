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

  public CountryCSVLoader(Supplier<List<String[]>> source) {
    super(source);
  }

  public static CountryCSVLoader fromCSV(String srcPath, char separator) {
    return new CountryCSVLoader(() -> OpenCSVParser.parseByLine(srcPath, separator));
  }

  public List<Country> load() {
    List<Country> countryList = new ArrayList<>();
    for (int i = 0; i < lines.size(); i++) {
      try {
        String[] line = lines.get(i);
        countryList.add(new Country(line[0], Integer.parseInt(line[3]), Integer.parseInt(line[4])));
      } catch (NumberFormatException e) {
        logger.log(Level.WARNING, "Invalid number at line " + (i + 2) + ", skipping line.", e.getMessage());
      }
    }
    return countryList;
  }
}
