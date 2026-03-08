package de.bcxp.challenge.weather;

public class Weather {
  private int day;
  private int maxTempF;
  private int minTempF;

  public Weather(int day, int maxTemp, int minTemp) {
    this.day = day;
    this.maxTempF = maxTemp;
    this.minTempF = minTemp;
  }

  public int getDay() {
    return day;
  }

  public int getMaxTempF() {
    return maxTempF;
  }

  public int getMinTempF() {
    return minTempF;
  }
}
