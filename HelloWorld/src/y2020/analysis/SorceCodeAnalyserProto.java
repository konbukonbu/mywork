package y2020.analysis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class SorceCodeAnalyserProto {

  private static final String DIR_INPUT_COBOL_FILE = "./input";
  private static final String DIR_OUTPUT_COBOL_FILE = "./output";

  private static final String INPUT_FILE_CHARSET = "UTF-8";

  private static final Pattern PATTERN_IDENTIFICATION_DIVISION_START = Pattern.compile(".*IDENTIFICATION[ 　]*DIVISION\\..*");
  private static final Pattern PATTERN_ENVIRONMENT_DIVISION_START = Pattern.compile(".*ENVIRONMENT[ 　]*DIVISION\\..*");
  private static final Pattern PATTERN_DATA_DIVISION_START = Pattern.compile(".*DATA[ 　]*DIVISION\\..*");
  private static final Pattern PATTERN_PROCEDURE_DIVISION_START = Pattern.compile(".*PROCEDURE[ 　]*DIVISION\\..*");

  private static final Pattern PATTERN_SECTION_START = Pattern.compile(".*[ 　]SECTION\\..*");

  private static final String CONVERT_PATTERN_NAME_DBアクセス = "DBアクセス";
  private static final Pattern PATTERN_START_READ = Pattern.compile(".*[ 　]READ .*");
  private static final Pattern PATTERN_END_READ = Pattern.compile(".*[ 　]END-READ\\..*");

  private enum DivisionKbn {
    IDENTIFICATION, ENVIRONMENT, DATA, PROCEDURE, OTHER
  }

  public static void main(String[] args) throws IOException {
    readFileList();
  }

  private static void readFileList() throws IOException {
    //Fileクラスのオブジェクトを生成する
    File dir = new File(DIR_INPUT_COBOL_FILE);

    //listFilesメソッドを使用して一覧を取得する
    File[] fileList = dir.listFiles();

    for (File file : fileList) {
      readFile(file);
    }

  }

  private static void readFile(File file) throws IOException {

    String outputFileName = file.getName().toUpperCase().replaceAll(".COB", ".csv");
    CSVPrinter printer = new CSVPrinter(new FileWriter(DIR_OUTPUT_COBOL_FILE + File.separator + outputFileName), CSVFormat.EXCEL);
    printer.printRecord("行番号", "ソースコード");
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), INPUT_FILE_CHARSET));

    String line = "";
    int 行番号 = 0;
    int 直前のREAD行番号 = 0;
    int END_READ行番号 = 0;
    List<AnalyseResultDto> resultDtoList = new ArrayList<>();
    DivisionKbn currentDivisionKbn = DivisionKbn.OTHER;
    while ((line = bufferedReader.readLine()) != null) {
      行番号++;
      AnalyseResultDto resultDto = new AnalyseResultDto();
      resultDtoList.add(resultDto);
      resultDto.set行番号(行番号);
      resultDto.setソースコード(line);

      //DIVISIONの判定

      if (PATTERN_IDENTIFICATION_DIVISION_START.matcher(line).matches()) {
        currentDivisionKbn = DivisionKbn.IDENTIFICATION;
      } else if (PATTERN_ENVIRONMENT_DIVISION_START.matcher(line).matches()) {
        currentDivisionKbn = DivisionKbn.ENVIRONMENT;
      } else if (PATTERN_DATA_DIVISION_START.matcher(line).matches()) {
        currentDivisionKbn = DivisionKbn.DATA;
      } else if (PATTERN_PROCEDURE_DIVISION_START.matcher(line).matches()) {
        currentDivisionKbn = DivisionKbn.PROCEDURE;
      }
      resultDto.setDIVISION名(currentDivisionKbn.toString());

      //SECTION名判定
      if (PATTERN_SECTION_START.matcher(line).matches()) {
        String section名 = getSection名(line);
        resultDto.setSECTION名(section名);
      }

      //DBアクセスの解析
      if (DivisionKbn.PROCEDURE.equals(currentDivisionKbn)) {
        if (PATTERN_END_READ.matcher(line).matches()) {
          END_READ行番号 = 行番号;

        } else if (PATTERN_START_READ.matcher(line).matches()) {
          直前のREAD行番号 = 行番号;
        }

        if (END_READ行番号 != 0 && 直前のREAD行番号 != 0) {

          StringBuilder builderソースコードDBアクセス = new StringBuilder();
          for (int i = 直前のREAD行番号 - 1; END_READ行番号 > i; i++) {
            resultDtoList.get(i).setDBアクセスフラグ(true);
            builderソースコードDBアクセス.append(resultDtoList.get(i).getソースコード()).append(" ");
          }

          String DBアクセス先名 = getDBアクセス先(builderソースコードDBアクセス.toString());
          for (int i = 直前のREAD行番号 - 1; END_READ行番号 > i; i++) {
            resultDtoList.get(i).set変換パターン名(CONVERT_PATTERN_NAME_DBアクセス + ":" + DBアクセス先名);
          }

          直前のREAD行番号 = 0;
          END_READ行番号 = 0;
        }
      }

    }

    for (AnalyseResultDto resultDto : resultDtoList) {
      printer.printRecord(resultDto.get行番号(), resultDto.getソースコード(), resultDto.get変換パターン名(), resultDto.getDIVISION名(), resultDto.getSECTION名());
    }

    // 最後にファイルを閉じてリソースを開放する
    bufferedReader.close();
    printer.close();
  }

  private static String getDBアクセス先(String targetLine) {
    String[] targetLineStatementArg = targetLine.split("[ |　]");
    int dbアクセス先名ElemIndex = 0;
    int idx = 0;//READの１つ後
    for (String targetElem : targetLineStatementArg) {
      idx++;
      if ("READ".equals(targetElem)) {
        dbアクセス先名ElemIndex = idx;
      }
    }

    String アクセス先名 = targetLineStatementArg[dbアクセス先名ElemIndex];
    return アクセス先名;
  }

  private static String getSection名(String targetLine) {
    String[] targetLineStatementArg = targetLine.split("[ |　]");
    int section名ElemIndex = 0;
    int idx = -2;//SECTION.の１つ前
    for (String targetElem : targetLineStatementArg) {
      idx++;
      if ("SECTION.".equals(targetElem)) {
        section名ElemIndex = idx;
      }
    }

    String section名 = targetLineStatementArg[section名ElemIndex];
    return section名;
  }

}
