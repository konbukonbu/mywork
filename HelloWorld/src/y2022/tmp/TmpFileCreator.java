package y2022.tmp;

/**
 * 
 * @author User
 * @see http://dotnsf.blog.jp/archives/1061700427.html
 */
public class TmpFileCreator {

  public static void main(String[] args) {
    sysoutTmpDir();
  }

  private static void sysoutTmpDir() {
    String tmpdir = System.getProperty("java.io.tmpdir");
    System.out.println(tmpdir);
    // https://dk521123.hatenablog.com/entry/37439082

    // ファイルセパレータが付く付かないが、環境によって変わる。
    //Windows-> C:\Users\User\AppData\Local\Temp\
    //Linux-> /tmp
    //だから 
    //String filePath = tmpDir+"\"+fileName　とか
    //String filePath = tmpDir + fileName　とかってやると事故る
    //少しデリケートな感じなので、共通関数かした方がいいのではないか説が浮上🐧==3333

    //tmpフォルダ使うこと自体はそんなに悪くはなさそう
    //https://densan-hoshigumi.com/server/tmp-file-disappearance
    //削除処理はsystemd-tmpfiles-clean.timerで定期実行される
    //tmp配下のファイルは変更やアクセスが無くなってから10日で削除される
    //削除期間を変えたければ、設定を変更する
  }

}
