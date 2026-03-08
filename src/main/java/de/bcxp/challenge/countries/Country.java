package de.bcxp.challenge.countries;

public class Country {
  private String name;
  private int population;
  private int areaSqKm;

  public Country(String name, int population, int areaSqKm) {
    this.name = name;
    this.population = population;
    this.areaSqKm = areaSqKm;
  }

  public String getName() {
    return name;
  }

  public int getPopulation() {
    return population;
  }

  public int getAreaSqKm() {
    return areaSqKm;
  }
}
