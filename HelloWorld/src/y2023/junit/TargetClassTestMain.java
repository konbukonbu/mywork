package y2023.junit;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class TargetClassTestMain {

  public static void main(String[] args) throws Exception {
    test02();
  }

  /**
   * Java17でも、「--add-opens java.base/java.lang=ALL-UNNAMED --add-opens java.base/java.lang.reflect=ALL-UNNAMED」などと、
   * オプション（無名モジュールに対して、参照可とする設定を行う）を指定して実行すれば成功する。
   * 
   * @see https://www.infoq.com/jp/news/2021/07/internals-encapsulated-jdk17/
   * @see https://stackoverflow.com/questions/74723932/java-17-reflection-issue
   * @sse https://segakuin.com/java/ant/
   */
  private static void test02() throws Exception {
    TargetClass clazz = new TargetClass();
    System.out.println("更新前: " + clazz.getTargetField()); // 更新前: 1
    Field field = clazz.getClass().getDeclaredField("TARGET_FIELD");
    //Field field = TargetClass.class.getDeclaredField("TARGET_FIELD"); // 更新対象アクセス用のFieldオブジェクトを取得する。
    field.setAccessible(true);

    // =====================================
    // modifiers自体がprivateであるため、Field[]を取得し、
    // その中からmodifiersの名前のフィールドを取得し、static/finalフィールドに任意の値を設定できるよう処理する.
    // =====================================
    Method getDeclaredFields0 = Class.class.getDeclaredMethod("getDeclaredFields0", boolean.class);
    getDeclaredFields0.setAccessible(true);
    Field[] fields = (Field[]) getDeclaredFields0.invoke(Field.class, false);
    Field modifiersField = null;
    for (Field each : fields) {
      if ("modifiers".equals(each.getName())) {
        modifiersField = each;
        break;
      }
    }
    modifiersField.setAccessible(true);
    modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

    field.set(null, 9999);
    System.out.println("更新後: " + clazz.getTargetField());
  }

  /**
   * Java17だと失敗するコード
   */
  private static void test01() throws Exception {
    TargetClass clazz = new TargetClass();
    System.out.println("更新前: " + clazz.getTargetField()); // 更新前: 1
    Field targetField = clazz.getClass().getDeclaredField("TARGET_FIELD"); // 更新対象アクセス用のFieldオブジェクトを取得する。

    //TODO ここからが、ポイント、modifiers自体がprivateだからfieldをぶん回すとかいる
    Field modifiersField = Field.class.getDeclaredField("modifiers"); // Fieldクラスはmodifiersでアクセス対象のフィールドのアクセス判定を行っているのでこれを更新する。
    modifiersField.setAccessible(true); // modifiers自体もprivateなのでアクセス可能にする。
    modifiersField.setInt(targetField,
        targetField.getModifiers() & ~Modifier.PRIVATE & ~Modifier.FINAL); // 更新対象アクセス用のFieldオブジェクトのmodifiersからprivateとfinalを外す。
    targetField.set(null, 1000); // 値更新: 1 -> 1000
    System.out.println("       ＿人人人人人＿"); //        ＿人人人人人＿
    System.out.println("更新後: ＞　 " + clazz.getTargetField() + " 　＜"); // 更新後: ＞　 1000 　＜
    System.out.println("       ￣Y^Y^Y^Y￣"); //       ￣Y^Y^Y^Y
  }

}
