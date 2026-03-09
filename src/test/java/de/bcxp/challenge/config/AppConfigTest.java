package de.bcxp.challenge.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.io.InputStream;

import java.util.Properties;

class AppConfigTest {

  @Test
  void nonExistentConfigFileThrowsIllegalStateException() {
    String configFilePath = "de/bcxp/challenge/config_nonexistent.properties";
    Properties props = new Properties();
    AppConfig appConfig = new AppConfig(props);
    IllegalStateException e = assertThrows(
        IllegalStateException.class,
        () -> appConfig.load(configFilePath));

    assertEquals("Config file not found: " + configFilePath, e.getMessage());
  }

  @Test
  void malformedConfigFileThrowsIllegalStateException() {
    String configFilePath = "de/bcxp/challenge/config_malformed.properties";
    Properties props = new Properties();
    AppConfig appConfig = new AppConfig(props);
    IllegalStateException e = assertThrows(
        IllegalStateException.class,
        () -> appConfig.load(configFilePath));

    assertEquals("Config file has malformed Unicode escape sequence: " + configFilePath, e.getMessage());
  }

  @Test
  void loadPropertiesIOExceptionThrowsIllegalStateException() {
    String configFilePath = "de/bcxp/challenge/config_test.properties";
    Properties stub = new Properties() {
      @Override
      public synchronized void load(InputStream inStream) throws IOException {
        throw new IOException();
      }
    };
    AppConfig appConfig = new AppConfig(stub);
    IllegalStateException e = assertThrows(
        IllegalStateException.class,
        () -> appConfig.load(configFilePath));

    assertEquals("Could not load config file: " + configFilePath, e.getMessage());
  }

  @Test
  void missingKeyThrowsIllegalStateException() {
    String key = "nonexistent.key";
    Properties props = new Properties();
    AppConfig appConfig = new AppConfig(props);
    appConfig.load("de/bcxp/challenge/config_test.properties");
    IllegalStateException e = assertThrows(
        IllegalStateException.class,
        () -> appConfig.get(key));
    assertEquals("Missing required config: " + key, e.getMessage());
  }

  @Test
  void existingKeyReturnsValue() {
    Properties props = new Properties();
    AppConfig appConfig = new AppConfig(props);
    appConfig.load("de/bcxp/challenge/config_test.properties");
    assertEquals("expectedValue", appConfig.get("some.key"));
  }
}
