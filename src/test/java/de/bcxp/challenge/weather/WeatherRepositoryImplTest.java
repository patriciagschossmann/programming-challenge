package de.bcxp.challenge.weather;

import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.List;

import org.junit.jupiter.api.Test;

import de.bcxp.challenge.shared.DataLoader;

class WeatherRepositoryImplTest {

  private WeatherRepository repositoryWith(List<Weather> data) {
    DataLoader<Weather> stub = new DataLoader<Weather>(() -> List.of()) {
      @Override
      public List<Weather> load() {
        return data;
      }
    };
    return new WeatherRepositoryImpl(stub);
  }

  @Test
  void findAllReturnsLoadedData() {
    List<Weather> data = List.of(
        new Weather("1", 90, 60),
        new Weather("2", 80, 75),
        new Weather("3", 70, 50));
    WeatherRepository weatherRepository = repositoryWith(data);

    assertSame(data, weatherRepository.findAll());
  }
}
