package de.bcxp.challenge.countries;

import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.List;

import org.junit.jupiter.api.Test;

import de.bcxp.challenge.shared.DataLoader;

class CountryRepositoryImplTest {

  private CountryRepository repositoryWith(List<Country> data) {
    DataLoader<Country> stub = new DataLoader<Country>(() -> List.of()) {
      @Override
      public List<Country> load() {
        return data;
      }
    };
    return new CountryRepositoryImpl(stub);
  }

  @Test
  void findAllReturnsLoadedData() {
    List<Country> data = List.of(
        new Country("Germany", 83_500_000, 357_000),
        new Country("Monaco", 38_000, 2),
        new Country("Brazil", 212_600_000, 8_516_000));
    CountryRepository countryRepository = repositoryWith(data);

    assertSame(data, countryRepository.findAll());
  }
}
