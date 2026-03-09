package de.bcxp.challenge.countries;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CountryServiceImplTest {

  private CountryService serviceWith(List<Country> data) {
    CountryRepository stub = new CountryRepository() {
      @Override
      public List<Country> findAll() {
        return data;
      }
    };
    return new CountryServiceImpl(stub);
  }

  @Test
  void returnsCountryWithHighestPopulationDensity() {
    CountryService countryService = serviceWith(List.of(
        new Country("Germany", 83_500_000, 357_000), // density: ~232.49
        new Country("Monaco", 38_000, 2), // density: ~19.0k
        new Country("Brazil", 212_600_000, 8_516_000) // density: ~26.07
    ));

    assertEquals("Monaco", countryService.getCountryWithHighestPopulationDensity());
  }

  @Test
  void singleEntryReturnsThatCountry() {
    CountryService countryService = serviceWith(List.of(
        new Country("Monaco", 39_000, 2)));

    assertEquals("Monaco", countryService.getCountryWithHighestPopulationDensity());
  }

  @Test
  void sameDensityReturnsOneOfTiedCountries() {
    CountryService countryService = serviceWith(List.of(
        new Country("Norway", 5_650_000, 385_000), // density: ~14.68
        new Country("Finland", 5_650_000, 385_000) // density: ~14.68
    ));

    assertTrue(List.of("Norway", "Finland").contains(countryService.getCountryWithHighestPopulationDensity()));
  }

  @Test
  void emptyRepositoryThrowsIllegalStateException() {
    CountryService countryService = serviceWith(List.of());

    IllegalStateException e = assertThrows(
        IllegalStateException.class,
        () -> countryService.getCountryWithHighestPopulationDensity());
    assertEquals("No countries data available", e.getMessage());
  }
}
