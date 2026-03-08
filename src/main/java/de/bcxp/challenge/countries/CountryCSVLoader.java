package de.bcxp.challenge.countries;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.bcxp.challenge.parser.OpenCSVParser;

public class CountryCSVLoader extends CountryDataLoader {
  private static final Logger logger = Logger.getLogger(CountryCSVLoader.class.getName());

  public CountryCSVLoader(String srcPath, char separator) {
    super(srcPath, separator);
  }

  public List<Country> load() {
    List<Country> countryList = new ArrayList<>();
    OpenCSVParser.parseByLine(srcPath, separator, (lineNumber, line) -> {
      try {
        countryList.add(new Country(line[0], Integer.parseInt(line[3]), Integer.parseInt(line[4])));
      } catch (NumberFormatException e) {
        logger.log(Level.WARNING, "Invalid number at line " + lineNumber + ", skipping line.", e.getMessage());
      }
    });
    return countryList;
  }
}
