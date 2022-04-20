package y2022.constant;

/**
 * 
 * @author こんぶおー🐧
 *@see https://npnl.hatenablog.jp/entry/2012/05/03/141341
 */
public class PropConsts {

  private static final String A_PROP_FILE_PATH = "/hoge/A.properties";
  private static final String B_PROP_FILE_PATH = "/fuga/B.properties";
  private static final String C_PROP_FILE_PATH = "/hoga/C.properties";

  //まぁもっとテクイやり方あると思うけど、テク過ぎると説明するのが大変なんで

  /** A.properties のEnum */
  public enum AProp {
    /** key1:〇〇に関する情報*/
    key_a_1,
    /** key2:〇〇に関する情報*/
    key_a_2,
    ;

    public static String getFilePath() {//やり方次第で、getFilePathを強制させられるが。。。あんま複雑にすべきでない気もするので
      return A_PROP_FILE_PATH;
    }
  }

  /** B.properties のEnum */
  public enum BProp {
    /** key1:〇〇に関する情報*/
    key_b_1,
    /** key2:〇〇に関する情報*/
    key_b_2,
    ;

    public static String getFilePath() {
      return B_PROP_FILE_PATH;
    }
  }

  /** C.properties のEnum */
  public enum CProp {
    /** key1:〇〇に関する情報*/
    key_c_1,
    /** key2:〇〇に関する情報*/
    key_c_2,
    ;

    public static String getFilePath() {
      return C_PROP_FILE_PATH;
    }
  }

  //↓こういうことも考えたけど、制御側は、ちゃんと個々のプロパティを認識しないとダメなので
  //  public List<String> getPropertyFilePathList(){
  //    List<String> propertyFilePathList = new ArrayList<>();
  //    propertyFilePathList.add(A_PROP_FILE_PATH);
  //    propertyFilePathList.add(B_PROP_FILE_PATH);
  //    propertyFilePathList.add(C_PROP_FILE_PATH);
  //    return null;
  //  }

}
