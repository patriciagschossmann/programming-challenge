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

/**
 * Utility class for parsing delimiter-separated CSV files from the classpath
 * using OpenCSV.
 */
public class OpenCSVParser {
  private static final Logger logger = Logger.getLogger(OpenCSVParser.class.getName());

  private static InputStream openStream(String filePath) {
    InputStream stream = OpenCSVParser.class.getClassLoader().getResourceAsStream(filePath);
    if (stream == null)
      throw new IllegalStateException("CSV file not found: " + filePath);
    return stream;
  }

  /**
   * Parses a classpath CSV file, skipping the header row.
   *
   * @param filePath  path to the CSV file
   * @param separator field delimiter used in the file
   * @throws IllegalStateException if the file is not found or cannot be parsed
   */
  public static List<String[]> parseByRow(String filePath, char separator) {
    CSVParser parser = new CSVParserBuilder()
        .withSeparator(separator)
        .build();

    List<String[]> rows = new ArrayList<>();

    try (
        InputStream stream = openStream(filePath);
        InputStreamReader inputStreamReader = new InputStreamReader(stream);
        CSVReader reader = new CSVReaderBuilder(inputStreamReader)
            .withCSVParser(parser)
            .build()) {
      reader.readNext(); // skip header

      String[] row;
      while ((row = reader.readNext()) != null) {
        rows.add(row);
      }
    } catch (IOException | CsvValidationException e) {
      String errMsg = "Could not parse csv file: " + filePath;
      logger.log(Level.SEVERE, errMsg, e.getMessage());
      throw new IllegalStateException(errMsg, e);
    }

    return rows;
  }
}
