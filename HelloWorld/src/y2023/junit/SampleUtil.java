package y2023.junit;

/**
 * 
 * @author こんぶおーって
 * @see https://qiita.com/opengl-8080/items/a49d4dae9067413ccdd6
 * @see https://stackoverflow.com/questions/56039341/get-declared-fields-of-java-lang-reflect-fields-in-jdk12
 * @see https://qiita.com/5at00001040/items/83bd7ea85d0f545ae7c3
 */
public class SampleUtil {

  private static final int STATIC_NUMBER = 777;

  private int objectNumber = 111;

  public static long addMethod(long a, long b) {
    long returnValue = a + b;
    return returnValue;
  }

  public static void cry() {
    System.out.println("ワンワン!!");
  }

  public static int getStaticNumber() {
    return STATIC_NUMBER;
  }

  public int getObjectNumber() {
    return objectNumber;
  }
}
