package de.bcxp.challenge.shared;

import java.util.List;

public abstract class DataLoader<T> {
  protected String srcPath;

  public DataLoader(String srcPath) {
    this.srcPath = srcPath;
  }
  
  public abstract List<T> load();
}
