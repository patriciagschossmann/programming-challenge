package de.bcxp.challenge.parser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.function.BiConsumer;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;;

public class OpenCSVParser {
  private static final Logger logger = Logger.getLogger(OpenCSVParser.class.getName());

  public static void parseByLine(String filePath, char separator, BiConsumer<Integer, String[]> lineHandler) {
    CSVParser parser = new CSVParserBuilder()
        .withSeparator(separator)
        .build();

    try (
        InputStream stream = OpenCSVParser.class.getClassLoader().getResourceAsStream(filePath);
        InputStreamReader inputStreamReader = new InputStreamReader(stream);
        CSVReader reader = new CSVReaderBuilder(inputStreamReader)
                .withCSVParser(parser)
                .build()
    ) {
      reader.readNext(); // skip header

      int lineNumber = 2; // data starts at line 2
      String[] line;
      while ((line = reader.readNext()) != null) {
        lineHandler.accept(lineNumber, line);
        lineNumber++;
      }
    } catch (IOException | CsvValidationException e) {
      String errMsg = "Could not parse csv file: " + filePath;
      logger.log(Level.SEVERE, errMsg, e.getMessage());
      throw new IllegalStateException(errMsg, e);
    }
  }
}
