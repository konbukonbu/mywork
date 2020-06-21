package y2018.auth;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;

/**
 *
 * @author adachi
 * @see https://qiita.com/yoshikingt/items/5276f1f715db4c5ba991 ＠ see
 *      https://blog.ohgaki.net/use-2-factor-authentication-with-your-web-sites#
 *      i-2
 */
public class ClientAuthSample {

  public static void main(String[] args) {
    exec共通鍵を発行_URIを生成();
    // exec検証();
  }

  private static void exec共通鍵を発行_URIを生成() {
    String serviceName = "zaif.jp";
    String userId = "konbu@yahoo.co.jp";

    GoogleAuthenticator gAuth = new GoogleAuthenticator();
    GoogleAuthenticatorKey key = gAuth.createCredentials();
    System.out.println(key.getConfig().getHmacHashFunction());

    String secret = key.getKey();// シード
    String uri = "otpauth://totp/" + serviceName + ":" + userId + "?secret=" + secret + "&issuer=" + serviceName;
    System.out.println(uri);
  }

  private static void exec検証() {
    String secret = "W3CJSLBISG76YZ4B";

    // 検証
    int inputSecret = Integer.parseInt("380621");
    GoogleAuthenticator gAuth1 = new GoogleAuthenticator();
    boolean isCodeValid = gAuth1.authorize(secret, inputSecret);
    System.out.println(isCodeValid);
  }

}
