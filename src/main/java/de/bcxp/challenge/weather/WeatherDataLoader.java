package de.bcxp.challenge.weather;

import java.util.List;

public abstract class WeatherDataLoader {
  protected String srcPath;

  public WeatherDataLoader(String srcPath) {
    this.srcPath = srcPath;
  }

  protected abstract List<Weather> load();
}
