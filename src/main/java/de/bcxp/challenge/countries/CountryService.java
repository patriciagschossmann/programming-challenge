package de.bcxp.challenge.countries;

/**
 * Query operations for countries data.
 */
public interface CountryService {

  /**
   * Finds the country with the highest population density, computed as population
   * divided by area.
   */
  public String getCountryWithHighestPopulationDensity();
}
