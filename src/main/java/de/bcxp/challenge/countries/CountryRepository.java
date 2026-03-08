package de.bcxp.challenge.countries;

import java.util.List;

public interface CountryRepository {
  public List<Country> findAll();
}
