package y2022.crypt;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author konbu🐧
 * @see https://itsakura.com/sb-bcryptpasswordencoder#s1
 * @see https://cheatsheetseries.owasp.org/cheatsheets/Password_Storage_Cheat_Sheet.html
 */
public class HelloBCrypt {

  public static void main(String[] args) {
    method();
  }

  private static void method() {
    String password = "あいうえ";
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    String hash = passwordEncoder.encode(password);
    System.out.println(hash);//$2a$10$G9sUlgB4a4Nr0eY96/tkx.klRjw6My7ejhNcYg5V124uFeQm6OtiS
    //60バイト

    if (passwordEncoder.matches(password, hash)) {
      System.out.println("OK");
    } else {
      System.out.println("NG");
    }
  }

}
