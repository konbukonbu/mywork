package y2022.zip;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * 
 * @author User
 * @see https://style.potepan.com/articles/29591.html#:~:text=Java%E3%81%A7ZIP%E3%83%95%E3%82%A1%E3%82%A4%E3%83%AB%E3%82%92%E5%9C%A7%E7%B8%AE%E3%81%99%E3%82%8B%E3%81%AB%E3%81%AF%E3%80%81%E3%80%8CZipOutputStream,%E4%B8%A1%E6%96%B9%E3%82%92%E3%82%B5%E3%83%9D%E3%83%BC%E3%83%88%E3%81%97%E3%81%BE%E3%81%99%E3%80%82
 * @see https://dev.classmethod.jp/articles/java-zipoutputstream-failure/
 */
public class ZipFileSample {

  //https://www.saka-en.com/java/java-zip-compress/
  //https://www.ne.jp/asahi/hishidama/home/tech/java/zip.html#h2_ZipOutputStream
  //https://g-weblog.com/blog/10
  public static void main(String[] args) {
    unzipFile1();
  }

  private static void makeZipFile1() {
    String targetFilePath = "./input/sampleFile.txt";
    String targetFileName = "sampleFile.txt";
    String destZipFilePath = "./output/sampleFile1.zip";
    File targetFile = new File(targetFilePath);
    File zipFile = new File(destZipFilePath);
    try (ZipOutputStream zipOut = new ZipOutputStream(new BufferedOutputStream((new FileOutputStream(zipFile))));
        BufferedInputStream bufIn = new BufferedInputStream(new FileInputStream(targetFile));) {
      ZipEntry zipEntry = new ZipEntry(targetFileName);//ここ注意！！
      zipOut.putNextEntry(zipEntry);

      int len;
      byte[] buf = new byte[1024];
      while ((len = bufIn.read(buf, 0, buf.length)) != -1) {
        zipOut.write(buf, 0, len);
      }
      zipOut.closeEntry();
    } catch (IOException e) {
    }
  }

  //https://flytech.work/blog/14899/
  private static void unzipFile1() {
    String targetZipFilePath = "./input/sampleFile1.zip";
    String destFileDirPath = "./output";

    try (
        ZipInputStream zipIn = new ZipInputStream(new BufferedInputStream(new FileInputStream(targetZipFilePath)));) {

      // zipファイルのエントリの準備 
      ZipEntry zipEntry = null;
      while ((zipEntry = zipIn.getNextEntry()) != null) {

        System.out.println(zipEntry.getName());

        // 解凍先のファイル 
        //TODO ここのディレクトリ作成の作成先を[destFileDirPath]配下にする処理 多分これでいいはず
        File uncompressFile = new File(destFileDirPath + "/" + zipEntry.getName());
        if (zipEntry.isDirectory()) {
          // ディレクトリの場合ディレクトリを作成 
          uncompressFile.mkdirs();

        } else {
          // 解凍先ファイル用の出力ストリーム
          BufferedOutputStream bostr = new BufferedOutputStream(new FileOutputStream(uncompressFile));

          int length;
          byte[] buf = new byte[1024];
          while ((length = zipIn.read(buf)) != -1) {
            bostr.write(buf, 0, length);
          }
          bostr.close();//TODO bostrのIO例外時のハンドリング　try-with-resource

        }
      }
      zipIn.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
