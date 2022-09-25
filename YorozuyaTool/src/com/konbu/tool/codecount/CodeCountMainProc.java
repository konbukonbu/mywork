package com.konbu.tool.codecount;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CodeCountMainProc {

  /**
   * 集計処理のメイン処理<br>
   * 参考文献<br>
   * https://tagsqa.com/detail/43946
   * https://winmundo.com/pipe-read-output-git-bash-with-processbuilder-in-java-2/
   * https://qiita.com/radiocat/items/3abba165d259f8db5642
   */
  static void countMainProc(String gitBashPath, String targetGitPath, String inputFromDate, String inputToDate, String author) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
    try {
      Date fromDate = dateFormat.parse(inputFromDate);
      Date toDate = dateFormat.parse(inputToDate);
      long diffTime = toDate.getTime() - fromDate.getTime();
      if (diffTime < 0) {
        System.out.println("集計開始日～終了日の大小関係が不正です.");
      }

      final long MILLIS_OF_DAY = 1000 * 60 * 60 * 24;
      long diffDays = diffTime / MILLIS_OF_DAY;

      Calendar fromCal = Calendar.getInstance();
      fromCal.setTime(fromDate);
      Calendar toCal = Calendar.getInstance();
      toCal.setTime(fromDate);
      toCal.add(Calendar.DATE, 1);

      //累積のコミット行数
      long totalCommitRowCnt = 0;

      //コミット行数集計結果のレポートファイル
      File reportFile = new File("./report.txt");
      FileWriter filewriter = null;
      if (reportFile.exists()) {
        reportFile.delete();
      }
      reportFile.createNewFile();
      filewriter = new FileWriter(reportFile);

      for (int i = 0; i < diffDays; i++) {

        //cal→date
        String currentFromDate = new SimpleDateFormat("yyyy-MM-dd").format(fromCal.getTime());
        String currentToDate = new SimpleDateFormat("yyyy-MM-dd").format(toCal.getTime());
        long commitRowCnt = execGitLogCmdAndCountCommitRow(gitBashPath, targetGitPath, currentFromDate, currentToDate, author);
        totalCommitRowCnt = totalCommitRowCnt + commitRowCnt;

        //for-debug
        System.out.println(currentFromDate + "," + currentToDate + "," + commitRowCnt + "," + totalCommitRowCnt);

        StringBuilder reportBuilder = new StringBuilder();
        if (i == 0) {//ヘッダ行
          reportBuilder.append("開始日").append("\t").append("終了日").append("\t").append("コミット行数").append("\t").append("コミット行数(累積)").append(System.lineSeparator());
        }
        reportBuilder.append(currentFromDate).append("\t").append(currentToDate).append("\t").append(commitRowCnt).append("\t").append(totalCommitRowCnt);
        filewriter.write(reportBuilder.toString());
        filewriter.write(System.lineSeparator());

        //cal加算
        fromCal.add(Calendar.DATE, 1);
        toCal.add(Calendar.DATE, 1);
      }

      filewriter.close();
      System.out.println("[" + author + "]さんは[" + inputFromDate + "]から[" + inputToDate + "]の間に[" + totalCommitRowCnt + "]行コミットしました.");

    } catch (ParseException e) {
      e.printStackTrace();
      System.out.println("入力した集計開始日・終了日がyyyyMMdd形式ではなかったため処理を終了します.");
      System.exit(99);
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("処理結果レポートファイル作成時に予期せぬIO例外が発生したため処理を終了する.");
      System.exit(99);
    }

    System.out.println("処理結果を[report.txt]に出力しました. ※添付のサンプル結果グラフ.xlsxを参考に活用ください.");
    System.out.println("コミット行数カウントツールの処理を正常終了します.");
    System.exit(0);
  }

  /**
   * 引数の条件をもとに、git logコマンドを実行し、コミット行数を取得する.
   */
  private static long execGitLogCmdAndCountCommitRow(String pathGitBash, String targetGitPath, String fromDate, String toDate, String author) {
    //返却用のコミット行数を格納するlong変数
    long commitRowCnt = 0;

    //git logコマンド
    StringBuilder execCmdBuilder = new StringBuilder();
    //targetGitPathに移動するためのコマンド
    execCmdBuilder.append("cd ").append(targetGitPath).append(" && ");

    String gitLogAuthorOption = " --author=" + author;//author 設定時のみコマンドに挿入する.
    if (null == author || "".equals(author)) {
      gitLogAuthorOption = "";
    }

    //gitlogのコマンド
    execCmdBuilder.append("git log --numstat --pretty=\"%H\"").append(gitLogAuthorOption).append(" --since=")
        .append(fromDate).append(" --until=").append(toDate).append(" --no-merges");

    //for-debug
    System.out.println("[" + execCmdBuilder.toString() + "]を実行する.");

    ArrayList<String> cmdList = new ArrayList<>();
    cmdList.add(pathGitBash);
    cmdList.add("-c");//bashの場合は/cではなく、-cを設定する.
    cmdList.add(execCmdBuilder.toString());

    ProcessBuilder pb = new ProcessBuilder(cmdList);
    try {
      Process process = pb.start();
      InputStream is = process.getInputStream(); //標準出力
      commitRowCnt = getCommitRowCntFromGitLogCmd(is);
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("予期せぬエラーが発生したため異常終了します。");
      System.exit(99);
    } //processの開始

    return commitRowCnt;
  }

  /**
   * git logコマンドの出力結果から、コミット行数を集計する.
   */
  private static long getCommitRowCntFromGitLogCmd(InputStream is) throws IOException {
    String charSet = "Shift-JIS";//Windows環境を想定
    BufferedReader br = new BufferedReader(new InputStreamReader(is, charSet));

    long commitRowCnt = 0;

    try {
      for (;;) {
        String line = br.readLine();
        if (line == null) {
          break;
        } else {
          //for-debug
          System.out.println(line);
          String[] lineAry = line.split("\t");//タブ区切り

          //処理対象の行：[数値(追加行)] [数値(削除行)] [対象ファイル部]
          //処理対象の行以外は、、処理対象外としてcontinueする.
          if (lineAry.length <= 2) {
            continue;
          }
          long plusRowCnt = 0;
          long minusRowCnt = 0;
          try {
            plusRowCnt = Long.parseLong(lineAry[0]);
            minusRowCnt = Long.parseLong(lineAry[1]);
          } catch (NumberFormatException e) {
            continue;
          }

          //足し合わせて、コード行数として合算
          commitRowCnt = commitRowCnt + plusRowCnt;
          //TODO 要検討:削除行数を加算するか否かを選択できるようにする.
          // commitRowCnt = commitRowCnt + minusRowCnt;
        }
      }
    } finally {
      br.close();
    }

    //for-debug
    System.out.println(commitRowCnt);
    return commitRowCnt;
  }
}
