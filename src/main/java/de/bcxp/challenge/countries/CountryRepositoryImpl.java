package de.bcxp.challenge.countries;

import java.util.List;

import de.bcxp.challenge.shared.DataLoader;

public class CountryRepositoryImpl implements CountryRepository {
  private List<Country> countryList;

  public CountryRepositoryImpl(DataLoader<Country> countryDataLoader) {
    countryList = countryDataLoader.load();
  }

  public List<Country> findAll() {
    return countryList;
  }
}
