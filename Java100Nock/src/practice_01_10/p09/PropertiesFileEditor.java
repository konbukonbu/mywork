package practice_01_10.p09;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * JavaのUnicode文字列の変換用処理<BR>
 * "あ" <-> "\u3042"
 *
 * @see https://qiita.com/sifue/items/039846cf8415efdc5c92
 * @see https://qiita.com/simiraaaa/items/cb29714293e012be77c3
 * @author adachi
 */
public class PropertiesFileEditor {
  private static final String DIR_INPUT_FILE = "./resources/input09.properties";

  public static void main(String[] args) throws IOException {
    File file = new File(DIR_INPUT_FILE);
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));

    String line = "";
    while ((line = bufferedReader.readLine()) != null) {
      System.out.println("変換前=" + line);
      String unicodeStr = convertToUnicode(line);
      System.out.println("変換後=" + unicodeStr);
    }

    bufferedReader.close();
  }

  /**
   * Unicode文字列に変換する("あ" -> "\u3042")
   *
   * @param インプット文字列
   * @return unicode文字列
   */
  private static String convertToUnicode(String original) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < original.length(); i++) {
      sb.append(String.format("\\u%04X", Character.codePointAt(original, i)));
    }
    String unicode = sb.toString();
    return unicode;
  }
}
