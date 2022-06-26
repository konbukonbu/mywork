package y2022.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @author こんぶおーって
 * @see https://www.ne.jp/asahi/hishidama/home/tech/java/process.html
 * @see https://www.jpcert.or.jp/java-rules/fio07-j.html
 * @see https://www.fujitsu.com/jp/about/faq/sfw-interstage/applicationserver/11822.html
 * 
 *
 */
public class OSProcessExecSample {

  public static void main(String[] args) {
    execProcessBuilder("./input/timeout.bat");
//    execProcessBuilder("./input/echoecho.bat");
  }

  private static void execProcessBuilder(String cmd) {
    final long TIMEOUT_SEC = 7;

    ArrayList<String> cmdList = new ArrayList<>();
    cmdList.add(cmd);

    //Runtime.execよりProcessBuilderの方がよさげ
    //Runtime.execのIFにて、Stringを1つ渡すIFがあるが、コマンドの文字列を引数付き（スペース区切り）で渡すと、そのコマンドが実行されるが、
    //Windowsではスペース入りのファイル名やディレクトリ名が使えたりするので、その場合は変な所で区切られることになってしまう。
    // なので、ProcessBuilderの方をつかって、String1つではなく、Stringの配列で渡すようにした方が無難.

    ProcessBuilder pb = new ProcessBuilder(cmdList);
    pb.redirectErrorStream(true); // 標準エラーを標準出力にマージする

    try {
      Process process = pb.start();//processの開始

      //注意1：外部プロセスは標準出力（や標準エラー）にがんがん書き込みたいのだが、
      // その受け側であるProcessのInputStreamはバッファーサイズに限りがある。
      // ★InputStreamが読み込めないと、書き込み側（外部プロセス）がブロッキング（一時停止）される。★
      // なので、プロセスの終了待ち（waitFor()）のタイミングを標準出力より前にしてしまうと、
      // 出力量が多い場合に、永久にバッファがあかず、デッドロック状態に陥る.

      //注意2：redirectErrorStreamの実施意図
      //例えば、標準出力→標準エラー出力の順番で出力しようとした場合
      // （レアケースだと思うが）標準エラー出力のバッファサイズ以上にエラー出力された場合に、
      // 注意点1と同じ現象が標準出力を読み込むタイミングで発生する.
      // 解決策としてはredirectErrorStreamにより、エラー出力を標準出力にリダイレクトマージし、
      // 1回の読み込みにより、バッファにたまっているストリームを読み出す処理を実施するということ。これによりデッドロックが解消される.
      
      //注意3：標準出力処理を前に持っていくと、タイムアウト処理が機能しないことになる(コマンドの実行結果を待ち合わせることになるから）
      //ここは現在検討中
      // 時間監視をして、タイムオーバーになったら、Processをkillして、処理を抜けるとか、、、

      InputStream is = process.getInputStream(); //標準出力
      printInputStream(is);

      System.out.println("判定処理");
      boolean isSuccess = process.waitFor(TIMEOUT_SEC, TimeUnit.SECONDS);
      if (isSuccess) {

        int exitValue = process.exitValue();
        System.out.println("終了コード：" + exitValue);

      } else {
        System.out.println("タイムアウトしたで");
      }

    } catch (IOException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }

  public static void printInputStream(InputStream is) throws IOException {
    String charSet = "Shift-JIS";
    BufferedReader br = new BufferedReader(new InputStreamReader(is, charSet));
    try {
      for (;;) {
        String line = br.readLine();
        if (line == null)
          break;
        System.out.println(line);
      }
    } finally {
      br.close();
    }
  }

}
