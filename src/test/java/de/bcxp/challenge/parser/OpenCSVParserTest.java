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
        () -> OpenCSVParser.parseByLine("de/bcxp/challenge/nonexistent.csv", ','));

    assertEquals("CSV file not found: de/bcxp/challenge/nonexistent.csv", e.getMessage());
  }

  @Test
  void malformedCSVThrowsIllegalStateException() {
    IllegalStateException e = assertThrows(
        IllegalStateException.class,
        () -> OpenCSVParser.parseByLine("de/bcxp/challenge/weather_malformed.csv", ','));

    assertEquals("Could not parse csv file: de/bcxp/challenge/weather_malformed.csv", e.getMessage());
  }

  @Test
  void validCSVReturnsCorrectLines() {
    List<String[]> lines = OpenCSVParser.parseByLine("de/bcxp/challenge/weather_valid.csv", ',');

    assertEquals(2, lines.size());
    assertEquals("1", lines.get(0)[0]);
    assertEquals("88", lines.get(0)[1]);
    assertEquals("59", lines.get(0)[2]);
  }

  @Test
  void headerOnlyCSVReturnsEmptyList() {
    List<String[]> lines = OpenCSVParser.parseByLine("de/bcxp/challenge/weather_empty.csv", ',');

    assertEquals(0, lines.size());
  }

  @Test
  void semicolonSeparatedCSVIsParsedCorrectly() {
    List<String[]> lines = OpenCSVParser.parseByLine("de/bcxp/challenge/weather_semicolon.csv", ';');

    assertEquals(2, lines.size());
    assertEquals("1", lines.get(0)[0]);
    assertEquals("88", lines.get(0)[1]);
    assertEquals("59", lines.get(0)[2]);
    assertEquals("2", lines.get(1)[0]);
    assertEquals("79", lines.get(1)[1]);
    assertEquals("63", lines.get(1)[2]);
  }
}
