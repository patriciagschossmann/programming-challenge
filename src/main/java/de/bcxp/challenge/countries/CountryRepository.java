package de.bcxp.challenge.countries;

import java.util.List;

/**
 * Data access for country records.
 */
public interface CountryRepository {

  /**
   * Returns all country records available from the underlying data source.
   */
  public List<Country> findAll();
}
