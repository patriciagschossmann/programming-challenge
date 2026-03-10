package de.bcxp.challenge.countries;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CountryCSVLoaderTest {

  @Test
  void loadReturnsCorrectCountryObjects() {
    List<String[]> lines = List.of(
        new String[] { "Germany", "Berlin", "1957", "83500000", "357000", "3.8", "0.942", "96" },
        new String[] { "Monaco", "Monaco", "1993", "38000", "2", "7.1", "0.956", "0" });
    List<Country> result = new CountryCSVLoader(() -> lines).load();

    assertEquals(2, result.size());
    assertEquals("Germany", result.get(0).getName());
    assertEquals(83_500_000, result.get(0).getPopulation());
    assertEquals(357_000, result.get(0).getAreaSqKm());
    assertEquals("Monaco", result.get(1).getName());
    assertEquals(38_000, result.get(1).getPopulation());
    assertEquals(2, result.get(1).getAreaSqKm());
  }

  @Test
  void invalidNumberSkipsLine() {
    List<String[]> lines = List.<String[]>of(
        new String[] { "Germany", "Berlin", "1957", "notANumber", "357000", "3.8", "0.942", "96" });
    List<Country> result = new CountryCSVLoader(() -> lines).load();

    assertTrue(result.isEmpty());
  }

  @Test
  void emptyRowsReturnsEmptyList() {
    List<Country> result = new CountryCSVLoader(() -> List.of()).load();

    assertTrue(result.isEmpty());
  }
}
