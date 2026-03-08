package de.bcxp.challenge.weather;

public class WeatherController {
  private WeatherService weatherService;

  public WeatherController(WeatherService weatherService) {
    this.weatherService = weatherService;
  }

  public void printDayWithSmallestTempSpread() {
    int dayWithSmallestTempSpread = weatherService.getDayWithSmallestTempSpread();
    System.out.printf("Day with smallest temperature spread: %d%n", dayWithSmallestTempSpread);
  }
}
