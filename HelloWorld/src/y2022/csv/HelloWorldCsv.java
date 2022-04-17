package y2022.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
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
    convCsvToBean();
  }

  private static void convCsvToBean() throws IOException {
    File csvFile = new File("./input/sampleCsv.txt");
    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(csvFile), "UTF-8"));
    CSVFormat csvFormat = CSVFormat.DEFAULT;
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
