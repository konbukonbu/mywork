package practice_01_10.p09;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * インプットファイルの文字列に対し「Unicodeエスケープ形式」への変換処理を行い、<BR>
 * 処理結果を別リソースファイルとして出力する.<BR>
 * "あ" <-> "\u3042"
 *
 * @see https://qiita.com/sifue/items/039846cf8415efdc5c92
 * @see https://proengineer.internous.co.jp/content/columnfeature/9158
 * @author adachi
 */
public class PropertiesFileEditor {
  private static final String DIR_INPUT_FILE = "./resources/input09.properties";
  private static final String CHARSET_INPUT_FILE = "UTF-8";
  private static final String OUTPUT_FILE_NAME = "output09";
  private static final String DIR_OUTPUT_FILE = "./resources/" + OUTPUT_FILE_NAME + ".properties";
  private static final String CHARSET_OUTPUT_FILE = "UTF-8";

  private static final char C0 = '0';
  private static final String HEAD = "\\u", FORMAT = HEAD + "%04X";
  private static final int UNICODE_LENGTH = 4, UNICODE_RATE = 6;

  public static void main(String[] args) throws IOException {
    convertToUnicodeEscape();
  }

  /**
   * インプットファイルの文字列に対し「Unicodeエスケープ形式」への変換処理を行い、<BR>
   * 処理結果を別リソースファイルとして出力する.<BR>
   *
   * @throws IOException
   */
  private static void convertToUnicodeEscape() throws IOException {
    File inputFile = new File(DIR_INPUT_FILE);
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile), CHARSET_INPUT_FILE));

    File outputFile = new File(DIR_OUTPUT_FILE);
    outputFile.delete();
    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), CHARSET_OUTPUT_FILE));

    String line = "";
    while ((line = bufferedReader.readLine()) != null) {
      System.out.println("変換前=" + line);
      String unicodeStr = toUnicode2(line, false);
      System.out.println("変換後=" + unicodeStr);
      bufferedWriter.write(unicodeStr);
      bufferedWriter.newLine();
    }

    bufferedWriter.close();
    bufferedReader.close();
  }

  /**
   * Unicode文字列に変換する("あ" -> "\u3042")<br>
   * コードポイント値が0x10000～0x10FFFFのサロゲートペア文字には対応できない。
   *
   * @return unicodeエスケープ文字列
   */
  @Deprecated
  private static String convertToUnicode(String original) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < original.length(); i++) {
      sb.append(String.format(FORMAT, Character.codePointAt(original, i)));
    }
    String unicode = sb.toString();
    return unicode;
  }

  /**
   * コードポイント値が0x10000～0x10FFFFのサロゲートペア文字でもunicodeエスケープ文字列に変換できるよう改良したメソッド<br>
   * "𩸽" -> "\ud867\ude3d"(UTF-8だと４バイトになるぞよ）<br>
   * なお、[=]まで、エスケープしてしまうと、プロパティファイルの読み込みに失敗するため、＝は処理対象外とする。
   *
   * @see https://www.softel.co.jp/blogs/tech/archives/596
   * @see https://qiita.com/simiraaaa/items/cb29714293e012be77c3
   * @return unicodeエスケープ文字列
   */
  public static String toUnicode2(final String original, boolean toUpper) {
    final char[] chars = original.toCharArray();
    final int len = chars.length;
    StringBuilder sb = new StringBuilder(len * UNICODE_RATE);
    for (int i = 0; i < len; ++i) {
      final String hexString = Integer.toHexString((int) chars[i]);
      if ('=' == (chars[i])) {// =は対象外
        sb.append("=");
        continue;
      }

      sb.append(HEAD);
      for (int j = UNICODE_LENGTH - hexString.length(); j > 0; --j) {
        sb.append(C0);
      }
      sb.append(hexString);
    }
    return (toUpper) ? sb.toString().toUpperCase() : sb.toString();
  }
}
