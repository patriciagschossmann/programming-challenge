package de.bcxp.challenge.config;

import java.io.IOException;
import java.io.InputStream;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Loads and provides access to application configuration properties.
 */
public class AppConfig {
  private static final Logger logger = Logger.getLogger(AppConfig.class.getName());
  private Properties props;

  public AppConfig(Properties props) {
    this.props = props;
  }

  /**
   * @param fileName path to the .properties file
   * @throws IllegalStateException if the file is missing, unreabable, or has
   *                               malformed encoding
   */
  public void load(String fileName) {
    try (InputStream config = AppConfig.class.getClassLoader().getResourceAsStream(fileName)) {

      if (config == null) {
        String errMsg = "Config file not found: " + fileName;
        logger.log(Level.SEVERE, errMsg);
        throw new IllegalStateException(errMsg);
      }

      props.load(config);

    } catch (IOException e) {
      String errMsg = "Could not load config file: " + fileName;
      logger.log(Level.SEVERE, errMsg, e);
      throw new IllegalStateException(errMsg, e);
    } catch (IllegalArgumentException e) {
      String errMsg = "Config file has malformed Unicode escape sequence: " + fileName;
      logger.log(Level.SEVERE, errMsg, e);
      throw new IllegalStateException(errMsg, e);
    }
  }

  /**
   * @param key see {@link ConfigKeys} for available keys
   * @throws IllegalStateException if {@code key} is not present in the loaded
   *                               properties
   */
  public String get(String key) {
    return require(key);
  }

  private String require(String key) {
    String val = props.getProperty(key);
    if (val == null) throw new IllegalStateException("Missing required config: " + key);
    return val;
  }
}
