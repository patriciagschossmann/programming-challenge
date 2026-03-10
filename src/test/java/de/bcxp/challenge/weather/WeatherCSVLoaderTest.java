package de.bcxp.challenge.weather;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WeatherCSVLoaderTest {

  @Test
  void loadReturnsCorrectWeatherObjects() {
    List<String[]> lines = List.of(
        new String[] { "1", "88", "59" },
        new String[] { "2", "79", "63" });
    List<Weather> result = new WeatherCSVLoader(() -> lines).load();

    assertEquals(2, result.size());
    assertEquals("1", result.get(0).getDay());
    assertEquals(88, result.get(0).getMaxTempF());
    assertEquals(59, result.get(0).getMinTempF());
    assertEquals("2", result.get(1).getDay());
    assertEquals(79, result.get(1).getMaxTempF());
    assertEquals(63, result.get(1).getMinTempF());
  }

  @Test
  void invalidDataSkipsLine() {
    List<String[]> lines = List.<String[]>of(
        new String[] { "1", "notANumber", "59" });
    List<Weather> result = new WeatherCSVLoader(() -> lines).load();

    assertTrue(result.isEmpty());
  }

  @Test
  void emptyRowsReturnsEmptyList() {
    List<Weather> result = new WeatherCSVLoader(() -> List.of()).load();

    assertTrue(result.isEmpty());
  }
}
