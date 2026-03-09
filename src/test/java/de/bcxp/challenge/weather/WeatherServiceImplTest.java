package de.bcxp.challenge.weather;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WeatherServiceImplTest {

  private WeatherService serviceWith(List<Weather> data) {
    WeatherRepository stub = new WeatherRepository() {
      @Override
      public List<Weather> findAll() {
        return data;
      }
    };
    return new WeatherServiceImpl(stub);
  }

  @Test
  void returnsCorrectDayWithSmallestSpread() {
    WeatherService weatherService = serviceWith(List.of(
        new Weather("1", 90, 60), // spread: 30
        new Weather("2", 80, 75), // spread: 5
        new Weather("3", 70, 50) // spread: 20
    ));

    assertEquals("2", weatherService.getDayWithSmallestTempSpread());
  }

  @Test
  void singleEntryReturnsThatDay() {
    WeatherService weatherService = serviceWith(List.of(
        new Weather("5", 88, 72)));

    assertEquals("5", weatherService.getDayWithSmallestTempSpread());
  }

  @Test
  void sameSpreadReturnsOneOfTiedDays() {
    WeatherService weatherService = serviceWith(List.of(
        new Weather("2", 77, 72), // spread: 5
        new Weather("5", 83, 78) // spread: 5
    ));

    assertTrue(List.of("2", "5").contains(weatherService.getDayWithSmallestTempSpread()));
  }

  @Test
  void emptyRepositoryThrowsIllegalStateException() {
    WeatherService weatherService = serviceWith(List.of());

    IllegalStateException e = assertThrows(
        IllegalStateException.class,
        () -> weatherService.getDayWithSmallestTempSpread());
    assertEquals("No weather data available", e.getMessage());
  }
}
