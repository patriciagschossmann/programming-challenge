package de.bcxp.challenge.countries;

import java.util.List;

public abstract class CountryDataLoader {
  protected String srcPath;
  protected char separator;

  public CountryDataLoader(String srcPath, char separator) {
    this.srcPath = srcPath;
    this.separator = separator;
  }

  protected abstract List<Country> load();
}
