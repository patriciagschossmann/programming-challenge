package de.bcxp.challenge.parser;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OpenCSVParserTest {

  @Test
  void nonExistentCSVThrowsIllegalStateException() {
    IllegalStateException e = assertThrows(
        IllegalStateException.class,
        () -> OpenCSVParser.parseByRow("de/bcxp/challenge/nonexistent.csv", ','));

    assertEquals("CSV file not found: de/bcxp/challenge/nonexistent.csv", e.getMessage());
  }

  @Test
  void malformedCSVThrowsIllegalStateException() {
    IllegalStateException e = assertThrows(
        IllegalStateException.class,
        () -> OpenCSVParser.parseByRow("de/bcxp/challenge/weather_malformed.csv", ','));

    assertEquals("Could not parse csv file: de/bcxp/challenge/weather_malformed.csv", e.getMessage());
  }

  @Test
  void validCSVReturnsCorrectRows() {
    List<String[]> rows = OpenCSVParser.parseByRow("de/bcxp/challenge/weather_valid.csv", ',');

    assertEquals(2, rows.size());
    assertEquals("1", rows.get(0)[0]);
    assertEquals("88", rows.get(0)[1]);
    assertEquals("59", rows.get(0)[2]);
  }

  @Test
  void headerOnlyCSVReturnsEmptyList() {
    List<String[]> rows = OpenCSVParser.parseByRow("de/bcxp/challenge/weather_empty.csv", ',');

    assertEquals(0, rows.size());
  }

  @Test
  void semicolonSeparatedCSVIsParsedCorrectly() {
    List<String[]> rows = OpenCSVParser.parseByRow("de/bcxp/challenge/weather_semicolon.csv", ';');

    assertEquals(2, rows.size());
    assertEquals("1", rows.get(0)[0]);
    assertEquals("88", rows.get(0)[1]);
    assertEquals("59", rows.get(0)[2]);
    assertEquals("2", rows.get(1)[0]);
    assertEquals("79", rows.get(1)[1]);
    assertEquals("63", rows.get(1)[2]);
  }
}
