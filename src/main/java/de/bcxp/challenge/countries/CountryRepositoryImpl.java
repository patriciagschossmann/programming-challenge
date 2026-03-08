package de.bcxp.challenge.countries;

import java.util.List;

public class CountryRepositoryImpl implements CountryRepository {
  private List<Country> countryList;

  public CountryRepositoryImpl(CountryDataLoader countryDataLoader) {
    countryList = countryDataLoader.load();
  }

  public List<Country> findAll() {
    return countryList;
  }
}
