package y2022.csv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

/**
 * @author こんぶおー
 * @see https://qiita.com/satio_koibuti/items/e90a9e30db289ac1b1f0
 * @see https://cyzennt.co.jp/blog/2021/08/31/%E3%83%98%E3%83%83%E3%83%80%E3%83%AC%E3%82%B3%E3%83%BC%E3%83%89%E3%83%BB%E3%83%87%E3%83%BC%E3%82%BF%E3%83%AC%E3%82%B3%E3%83%BC%E3%83%89%E3%83%BB%E3%83%88%E3%83%AC%E3%83%BC%E3%83%A9%E3%83%AC%E3%82%B3/
 */
public class HelloWorldCsv {

  //  private static final int READ_MODE_HEAD = 1;
  //  private static final int READ_MODE_DATA = 2;
  //  private static final int READ_MODE_TRAILER = 3; 

  public static void main(String[] args) throws IOException {
    //    convCsvToBean();
    convBeanToCsv();
  }

  /**
   * 
   * @throws IOException
   * @see https://qiita.com/igapyon/items/7972e5dd899ec9ced776
   * @see https://qiita.com/nulltemp/items/6a2e35e41e0dd544847d
   */
  private static void convBeanToCsv() throws IOException {
    CSVFormat.Builder formatBuilder = CSVFormat.DEFAULT.builder();
    formatBuilder.setDelimiter(',');
    CSVFormat csvFormat = formatBuilder.build();

    String outputFilePath = "./output/sampleCsv.txt";
    String charSet = "UTF-8";
    List<SampleDataEntity> dataEntityList = createEntityList();

    int recordCount = 0;
    try (CSVPrinter printer = new CSVPrinter(new BufferedWriter( //
        new OutputStreamWriter(new FileOutputStream(outputFilePath), charSet)), csvFormat)) {
      
      for (SampleDataEntity dataEntity : dataEntityList) {
        recordCount++;
        printer.print(dataEntity.getFileKbn());
        printer.print(dataEntity.getShohinCode());
        printer.print(dataEntity.getShohinName());
        printer.print(dataEntity.getShohinPrice());
        if(dataEntityList.size() > recordCount) {//最終行は改行しない
          printer.println();
        }
      }
    }
  }

  private static List<SampleDataEntity> createEntityList() {
    List<SampleDataEntity> dataEntityList = new ArrayList<>();
    SampleDataEntity dataEntity1 = new SampleDataEntity();
    dataEntity1.setFileKbn("2");
    dataEntity1.setShohinCode("SH001");
    dataEntity1.setShohinName("塩昆布");
    dataEntity1.setShohinPrice("250");

    SampleDataEntity dataEntity2 = new SampleDataEntity();
    dataEntity2.setFileKbn("2");
    dataEntity2.setShohinCode("SH002");
    dataEntity2.setShohinName("梅昆布");
    dataEntity2.setShohinPrice("288");

    SampleDataEntity dataEntity3 = new SampleDataEntity();
    dataEntity3.setFileKbn("3");
    dataEntity3.setShohinCode("GG001");
    dataEntity3.setShohinName("スーパー玩具");
    dataEntity3.setShohinPrice("1298");

    SampleDataEntity dataEntity4 = new SampleDataEntity();
    dataEntity4.setFileKbn("3");
    dataEntity4.setShohinCode("GG002");
    dataEntity4.setShohinName("ウルトラ玩具");
    dataEntity4.setShohinPrice("2998");

    dataEntityList.add(dataEntity1);
    dataEntityList.add(dataEntity2);
    dataEntityList.add(dataEntity3);
    dataEntityList.add(dataEntity4);
    return dataEntityList;
  }

  private static void convCsvToBean() throws IOException {
    File csvFile = new File("./input/sampleCsv.txt");
    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(csvFile), "UTF-8"));

    CSVFormat.Builder formatBuilder = CSVFormat.DEFAULT.builder();
    formatBuilder.setDelimiter(',');
    CSVFormat csvFormat = formatBuilder.build();
    CSVParser csvParser = new CSVParser(br, csvFormat);

    int dataRecordCount = 0;//データ部のレコード件数
    CSVRecord trailerRecord;//トレーラレコード部
    List<SampleDataEntity> dataEntityList = new ArrayList<SampleDataEntity>();
    for (CSVRecord line : csvParser) {

      //ヘッダ部(1行目)の処理
      if (line.getRecordNumber() == 1) { //ヘッダ部：ファイル区分(1)でなかったら異常終了
        System.out.println("ヘッダの処理");
        System.out.println(line);

        continue;
      }

      String fileKbn = line.get(0);
      //データ部・トレーラ部を見分ける処理
      if ("9".equals(fileKbn)) {
        trailerRecord = line;//トレーラ部のデータを退避
      } else {

        SampleDataEntity dataEntity = new SampleDataEntity();
        dataEntity.setFileKbn(line.get(0));
        dataEntity.setShohinCode(line.get(1));
        dataEntity.setShohinName(line.get(2));
        dataEntity.setShohinPrice(line.get(3));

        dataEntityList.add(dataEntity);//データ部のEntityListを設定
        dataRecordCount++;
      }
    }

    //トレーラ部のチェック処理：ファイル区分(9)でなかったら異常終了.データ部の総件数と一致してなかったら異常終了.
  }

}
