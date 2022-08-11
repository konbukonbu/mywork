package com.sample.message.props;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * プロパティファイルへアクセスするためのUtilクラス
 * TODO リファクタリングとか、いろいろ見直し
 * @author adachi
 */
@Deprecated
public class PropertiesUtil {

  private static final long TTL = 1000 * 10;

  private PropertiesUtil() {
  }

  private static ResourceBundle loadResourceBundle() {
    ResourceBundle resource = ResourceBundle.getBundle("business",
        new ResourceBundle.Control() {
          public long getTimeToLive(String aBaseName, Locale aLocale) {
            return TTL;
          }
        });
    return resource;
  }

  public static String getValue(String key) {
    return loadResourceBundle().getString(key);
  }
}
