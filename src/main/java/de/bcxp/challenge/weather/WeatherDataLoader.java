package de.bcxp.challenge.weather;

import java.util.List;

public abstract class WeatherDataLoader {
  protected String srcPath;
  protected char separator;

  public WeatherDataLoader(String srcPath, char separator) {
    this.srcPath = srcPath;
    this.separator = separator;
  }

  protected abstract List<Weather> load();
}
