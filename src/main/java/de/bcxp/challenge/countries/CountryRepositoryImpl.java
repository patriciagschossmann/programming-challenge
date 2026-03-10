package de.bcxp.challenge.countries;

import java.util.List;

import de.bcxp.challenge.shared.DataLoader;

public class CountryRepositoryImpl implements CountryRepository {
  private List<Country> countryList;

  /**
   * Creates a {@code CountryRepository} by eagerly loading all country records
   * from the given {@link DataLoader} into memory.
   */
  public CountryRepositoryImpl(DataLoader<String[], Country> countryDataLoader) {
    countryList = countryDataLoader.load();
  }

  public List<Country> findAll() {
    return countryList;
  }
}
