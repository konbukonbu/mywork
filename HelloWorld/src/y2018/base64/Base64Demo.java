package y2018.base64;

import java.io.PrintStream;
import java.util.Base64;

/**
 * @author adachi
 * @see https://gist.github.com/komiya-atsushi/d878e6e4bf9ba6dae8fa
 */
public class Base64Demo {
  private static final PrintStream o = System.out;

  public static void main(String[] args) {
      エンコードとデコードのデモ();
      _UrlEncoderのデモ();
      _MimeEncoderのデモ();
  }

  static void エンコードとデコードのデモ() {
      final String text = "abcd1234t1b83";

      // エンコードは、Base64.getEncoder() で得られるオブジェクトを利用する
      // Base64 エンコード結果を String としたい場合は、Encoder#encodeToString() を呼び出す
      String encoded = Base64.getEncoder()
              .encodeToString(text.getBytes());

      // デコードは Base64.getDecoder() で得られるオブジェクトを利用する
      String decoded = new String(Base64.getDecoder()
              .decode(encoded));

      o.println("# Base64.getEncoder() / Base64.getDecoder() のデモ");
      o.println("エンコード結果 : " + encoded);
      o.println("デコード結果 : " + decoded);
      o.println();
  }

  static void _UrlEncoderのデモ() {
      String text = "?????>";

      // UrlEncoder だと、Base64 で使われる記号 +/ がそれぞれ -_ に置き換えられる
      String encoded = Base64.getUrlEncoder()
              .encodeToString(text.getBytes());

      // デコーダは Base64.getUrlDecoder() で得られたものを使う必要がある
      String decoded = new String(Base64.getUrlDecoder()
              .decode(encoded));

      o.println("# Base64.getUrlEncoder() / Base64.getUrlDecoder() のデモ");
      o.println("エンコード結果 : " + encoded);
      o.println("デコード結果 : " + decoded);
      o.println();
  }

  static void _MimeEncoderのデモ() {
      final String text = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";

      String encoded = Base64.getMimeEncoder()
              .encodeToString(text.getBytes());

      String decoded = new String(Base64.getMimeDecoder()
              .decode(encoded));

      o.println("# Base64.getMimeEncoder() / Base64.getMimeDecoder() のデモ");
      o.println("エンコード結果 : " + encoded);
      o.println("デコード結果 : " + decoded);
      o.println();
  }
}
