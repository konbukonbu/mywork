package y2023.junit;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectionTestMain {

  public static void main(String[] args) throws Exception {
    Class targetClass = SampleUtil.class;
    String fieldName = "STATIC_NUMBER";
    Object newValue = 999;

//    setFinalStatic(targetClass, fieldName, newValue);
    setFinalStatic2(targetClass, fieldName, newValue);
  }

  /**
   * @see https://qiita.com/5at00001040/items/83bd7ea85d0f545ae7c3
   * 
   */
  public static void setFinalStatic(Class targetClass, String fieldName, Object newValue) throws Exception {

    Object clazzObj = targetClass.getDeclaredConstructor().newInstance();

    // 更新対象のFieldオブジェクトを取得,アクセス可を設定。
    final Field fields[] = clazzObj.getClass().getDeclaredFields();
    Field targetField = null;
    for (int i = 0; i < fields.length; i++) {
      System.out.println("Field: " + fields[i]);
      if (fieldName.equals(fields[i].getName())) {
        targetField = fields[i];
      }
    }
    System.out.println(targetField);
    targetField.setAccessible(true);

    // staticフィールドを変更できるよう、modifiersを探し出し設定する.
    Method getDeclaredFields0 = Class.class.getDeclaredMethod("getDeclaredFields0", boolean.class);
    getDeclaredFields0.setAccessible(true);
    Field[] modifyFields = (Field[]) getDeclaredFields0.invoke(Field.class, false);
    Field modifiersField = null;
    for (Field each : modifyFields) {
      if ("modifiers".equals(each.getName())) {
        modifiersField = each;
        break;
      }
    }
    modifiersField.setAccessible(true);
    modifiersField.setInt(targetField, targetField.getModifiers() & ~Modifier.FINAL);

    System.out.println("更新前: " + targetField.get(null));
    targetField.set(null, newValue);
    System.out.println("更新後: " + targetField.get(null));
  }

  /**
   * privateメンバにアクセスする方法（staticフィールドの値は更新できない）
   * @see https://www.glamenv-septzen.net/view/449
   * 
   */
  public static void setFinalStatic1(Class clazz, String fieldName, Object newValue) throws Exception {
    final Field fields[] = clazz.getDeclaredFields();
    Field targetField = null;
    for (int i = 0; i < fields.length; i++) {
      System.out.println("Field: " + fields[i]);
      if (fieldName.equals(fields[i].getName())) {
        targetField = fields[i];
      }
    }
    
    targetField.setAccessible(true);
    System.out.println(targetField.get(null));
    //    targetField.set(null, newValue); staticフィールドは更新できない。
  }

  /**
   * privateメンバにアクセスする方法（staticフィールドの値は更新できない）
   * @see https://www.glamenv-septzen.net/view/449
   * 
   */
  public static void setFinalStatic2(Class clazz, String fieldName, Object newValue) throws Exception {
    final Field targetField = clazz.getDeclaredField(fieldName);
    targetField.setAccessible(true);
    
    System.out.println(targetField.get(null));
    //    targetField.set(null, newValue); staticフィールドは更新できない。
  }
}
