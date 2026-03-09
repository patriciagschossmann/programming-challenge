package de.bcxp.challenge.parser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;;

public class OpenCSVParser {
  private static final Logger logger = Logger.getLogger(OpenCSVParser.class.getName());

  private static InputStream openStream(String filePath) {
    InputStream stream = OpenCSVParser.class.getClassLoader().getResourceAsStream(filePath);
    if (stream == null)
      throw new IllegalStateException("CSV file not found: " + filePath);
    return stream;
  }

  public static List<String[]> parseByLine(String filePath, char separator) {
    CSVParser parser = new CSVParserBuilder()
        .withSeparator(separator)
        .build();

    List<String[]> lines = new ArrayList<>();

    try (
        InputStream stream = openStream(filePath);
        InputStreamReader inputStreamReader = new InputStreamReader(stream);
        CSVReader reader = new CSVReaderBuilder(inputStreamReader)
            .withCSVParser(parser)
            .build()) {
      reader.readNext(); // skip header

      String[] line;
      while ((line = reader.readNext()) != null) {
        lines.add(line);
      }
    } catch (IOException | CsvValidationException e) {
      String errMsg = "Could not parse csv file: " + filePath;
      logger.log(Level.SEVERE, errMsg, e.getMessage());
      throw new IllegalStateException(errMsg, e);
    }

    return lines;
  }
}
