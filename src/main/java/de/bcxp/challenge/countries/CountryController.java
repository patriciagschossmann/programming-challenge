package de.bcxp.challenge.countries;

public class CountryController {
  private CountryService countryService;

  public CountryController(CountryService countryService) {
    this.countryService = countryService;
  }

  public void printCountryWithHighestPopulationDensity() {
    String countryWithHighestPopulationDensity = countryService.getCountryWithHighestPopulationDensity();
    System.out.printf("Country with highest population density: %s%n", countryWithHighestPopulationDensity);
  }
}
