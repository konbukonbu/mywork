package y2018.properties;

import java.util.Locale;
import java.util.ResourceBundle;

public class PropertiesUtil {
  /** プロパティファイルのResourceBundleインスタンス */
  private static ResourceBundle resource = load();
  private static final long TTL = 1000 * 10;

  private PropertiesUtil() {
  }

  private static ResourceBundle load() {
    resource = ResourceBundle.getBundle("system",
        new ResourceBundle.Control() {
          public long getTimeToLive(String aBaseName, Locale aLocale) {
            return TTL;
          }
        });
    return resource;
  }

  public static String getValue(String key) {
    return resource.getString(key);
  }
}
