package y2018.googleauth;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;

/**
 * @see https://blog.ohgaki.net/use-2-factor-authentication-with-your-web-sites
 * @see https://www.slideshare.net/hatanakaakihiro/ss-53907884
 * @see https://qiita.com/yuizho/items/553f1aac138143d8f8d9
 *
 */
public class HelloWorld {
  public static final int CONST = 1;

  public static void main(String[] args) throws Exception {
    //		AuthyApiClient client = new AuthyApiClient("");
    //		System.out.println(client.getTokens().verify(123456, "123456"));

    GoogleAuthenticator gAuth = new GoogleAuthenticator();
    gAuth.setCredentialRepository(new MyCredentialRepository());
    GoogleAuthenticatorKey key = gAuth.createCredentials("");
    System.out.println(key.getVerificationCode());
  }

}
