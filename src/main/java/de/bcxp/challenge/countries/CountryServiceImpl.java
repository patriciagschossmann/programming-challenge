package de.bcxp.challenge.countries;

import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CountryServiceImpl implements CountryService {
  private static final Logger logger = Logger.getLogger(CountryServiceImpl.class.getName());

  private CountryRepository countryRepository;

  public CountryServiceImpl(CountryRepository countryRepository) {
    this.countryRepository = countryRepository;
  }

  public String getCountryWithHighestPopulationDensity() {
    return countryRepository.findAll().stream()
        .max(Comparator.comparingInt(c -> c.getPopulation() / c.getAreaSqKm()))
        .map(c -> c.getName())
        .orElseThrow(() -> {
          String errMsg = "No countries data available";
          logger.log(Level.SEVERE, errMsg);
          return new IllegalStateException(errMsg);
        });
  }
}
