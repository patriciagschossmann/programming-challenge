package de.bcxp.challenge.config;

import java.io.IOException;
import java.io.InputStream;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppConfig {
  private static final Logger logger = Logger.getLogger(AppConfig.class.getName());
  private Properties props;

  public AppConfig(Properties props) {
    this.props = props;
  }

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

  public String get(String key) {
    return require(key);
  }

  private String require(String key) {
    String val = props.getProperty(key);
    if (val == null) throw new IllegalStateException("Missing required config: " + key);
    return val;
  }
}
