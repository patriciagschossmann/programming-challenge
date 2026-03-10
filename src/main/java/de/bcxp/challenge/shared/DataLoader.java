package de.bcxp.challenge.shared;

import java.util.List;
import java.util.function.Supplier;

/**
 * Abstract base class for loading typed domain objects from a raw data source.
 *
 * @param <T> the domain type produced by this loader
 */
public abstract class DataLoader<T, U> {

  protected List<T> rows;

  /**
   * Creates a {@code DataLoader} by fetching raw data from the given source.
   *
   * @param source returns all data as {@code String[]} arrays
   */
  public DataLoader(Supplier<List<T>> source) {
    this.rows = source.get();
  }

  /**
   * Converts the raw data in {@link #rows} to a list of typed domain objects.
   */
  public abstract List<U> load();
}
