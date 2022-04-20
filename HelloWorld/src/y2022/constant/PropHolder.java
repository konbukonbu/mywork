package y2022.constant;

import java.util.HashMap;
import java.util.Map;

public class PropHolder {

  private static Map<String, Object> propHolderMap = new HashMap<>();

  public static Object getProp(String propKey) {
    return propHolderMap.get(propKey);
  }

  public static void putProp(String propKey, Object object) {
    propHolderMap.put(propKey, object);
  }

}
