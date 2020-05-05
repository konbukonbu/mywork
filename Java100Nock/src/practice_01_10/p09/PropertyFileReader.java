package practice_01_10.p09;

import java.io.IOException;
import java.util.ResourceBundle;

public class PropertyFileReader {

  // private static final String OUTPUT_FILE_NAME = "output09";
  private static final String OUTPUT_FILE_NAME = "output09_02";

  public static void main(String[] args) throws IOException {
    readPropertiesFile();
  }

  /**
   * 変換したファイルをプロパティファイルとして読みこむ
   *
   * @throws IOException
   * @see https://www.sejuku.net/blog/20689
   * @see https://www.sejuku.net/blog/20794
   */
  private static void readPropertiesFile() throws IOException {
    ResourceBundle rb = ResourceBundle.getBundle(OUTPUT_FILE_NAME);
    System.out.println("key001=" + rb.getString("key001"));
    System.out.println("key002=" + rb.getString("key002"));
  }
}
