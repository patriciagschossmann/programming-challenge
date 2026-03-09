package de.bcxp.challenge.config;

public final class ConfigKeys {
  public static final class SupportedFormat {
    public static final String CSV = "csv";
  }

  public static final class WeatherConfig {
    public static final String SRC_PATH = "weather.srcPath";
    public static final String FILE_FORMAT = "weather.fileFormat";
    public static final String CSV_SEPARATOR = "weather.csvSeparator";
  }

  public static final class CountriesConfig {
    public static final String SRC_PATH = "countries.srcPath";
    public static final String FILE_FORMAT = "countries.fileFormat";
    public static final String CSV_SEPARATOR = "countries.csvSeparator";
  }
}
