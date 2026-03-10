package de.bcxp.challenge.shared;

import java.util.List;
import java.util.function.Supplier;

public abstract class DataLoader<T> {

  protected List<String[]> rows;

  public DataLoader(Supplier<List<String[]>> source) {
    this.rows = source.get();
  }

  public abstract List<T> load();
}
