package y2022.crypt;

import java.util.Date;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 
 * @author User
 *@see https://qiita.com/simarei/items/fd2bfa1807a791cf2fd5
 */
public class HelloBCrypt2 {
  public static void main(String[] args) {

    String passStr = "昆布もりもり";
    String hashStr;

    // 単純なエンコードと認証
    BCryptPasswordEncoder bpe = new BCryptPasswordEncoder();
    hashStr = bpe.encode(passStr);
    System.out.println("original string   : " + passStr);
    System.out.println("encoded by Bcrypt : " + hashStr);

    boolean isMatch = bpe.matches(passStr, "$2a$10$m2ypVrfCChkZOXCU7TbAE.3z5.oMF0HeO0bfpctKWTblANUayLzOm");
    //    boolean isMatch = bpe.matches(passStr, hashStr);//一定の法則で毎回変わる？法則に従っていればmatch扱い？
    System.out.println("result            : " + (isMatch ? "match" : "not match"));

    System.out.println("");

    // strengthごとに時間計測
    double streachTimes;

    for (int strength = 4; strength <= 10; strength++) {
      streachTimes = Math.pow(2, strength);
      System.out.println("= strength : " + strength + ", streach times : " + String.format("%1$.0f", streachTimes) + " ========");
      hashStr = testBcryptEncode(passStr, strength);
      testBcyptVerify(passStr, hashStr, strength);
    }
  }

  /**
   * 処理時間を計測しながらBcyptでハッシュ化
   * @param passStr パスワード文字列
   * @param strength ハッシュ化の回数。4～31（2の何乗か）
   */
  private static String testBcryptEncode(String passStr, int strength) {

    // 開始時刻
    long startTime = (new Date()).getTime();

    // BCryptでのハッシュ化
    BCryptPasswordEncoder bpe = new BCryptPasswordEncoder(strength);
    String hashStr = bpe.encode(passStr);

    // ハッシュ化終了時刻
    long endTime = (new Date()).getTime();
    String timeDelta = String.format("%.4f", ((float) (endTime - startTime) / 1000));

    System.out.println("encoding time  : " + timeDelta + " sec.");
    System.out.println("passStr  : " + passStr);
    System.out.println("hashStr  : " + hashStr);
    return hashStr;
  }

  /**
   * 処理時間を計測しながら生パスワードとハッシュが合致するかの認証
   * @param passStr 生
   * @param hashStr ハッシュ
   * @param strength
   */
  private static void testBcyptVerify(String passStr, String hashStr, int strength) {

    // 開始時刻
    long startTime = (new Date()).getTime();

    // パスワードがハッシュに一致するかのチェック
    BCryptPasswordEncoder bpe = new BCryptPasswordEncoder(strength);
    boolean isMatch = bpe.matches(passStr, hashStr);

    // 検証終了時刻
    long endTime = (new Date()).getTime();
    String timeDelta = String.format("%.4f", ((float) (endTime - startTime) / 1000));

    if (isMatch) {
      System.out.println("verifying time : " + timeDelta + " sec.");
    } else {

    }
  }

}
