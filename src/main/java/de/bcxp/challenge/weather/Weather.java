package de.bcxp.challenge.weather;

public class Weather {
  private String day;
  private int maxTempF; // Fahrenheit
  private int minTempF; // Fahrenheit

  public Weather(String day, int maxTemp, int minTemp) {
    this.day = day;
    this.maxTempF = maxTemp;
    this.minTempF = minTemp;
  }

  public String getDay() {
    return day;
  }

  public int getMaxTempF() {
    return maxTempF;
  }

  public int getMinTempF() {
    return minTempF;
  }
}
