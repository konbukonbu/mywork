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

public class CobolAnalyser {

  private static final String DIR_INPUT_COBOL_FILE = "./input";

  private static final String DIR_OUTPUT_COBOL_FILE = "./output";

  private static final String INPUT_FILE_CHARSET = "UTF-8";

  private static final Pattern PATTERN_PROCEDURE_DIVISION_START = Pattern.compile(".*PROCEDURE[ 　]*DIVISION\\..*");

  private static final Pattern PATTERN_SECTION_START = Pattern.compile(".*SECTION\\..*");

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
    boolean procedureDivisionFlg = false;
    List<AnalyseResultDto> resultDtoList = new ArrayList<>();
    while ((line = bufferedReader.readLine()) != null) {
      行番号++;
      AnalyseResultDto resultDto = new AnalyseResultDto();
      resultDto.set行番号(行番号);
      resultDto.setソースコード(line);

      //PROCEDURE DIVISIONの判定
      if (PATTERN_PROCEDURE_DIVISION_START.matcher(line).matches()) {
        procedureDivisionFlg = true;
      }
      if (procedureDivisionFlg) {
        resultDto.setDIVISION名("PROCEDURE");
      }

      //SECTION判定
      if(procedureDivisionFlg) {

      }




      resultDtoList.add(resultDto);

    }

    System.out.println(procedureDivisionFlg);

    for (AnalyseResultDto resultDto : resultDtoList) {
      printer.printRecord(resultDto.get行番号(), resultDto.getソースコード(), resultDto.getDIVISION名());
    }

    // 最後にファイルを閉じてリソースを開放する
    bufferedReader.close();
    printer.close();
  }

}
