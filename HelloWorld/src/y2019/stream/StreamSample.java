package y2019.stream;

import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * @see https://qiita.com/rubytomato@github/items/93011c75ee4af6b59452
 * @author adachi
 */
public class StreamSample {

  public static void main(String[] args) {
    execパターン1();
    System.out.println("==========================");
    execパターン2();
    System.out.println("==========================");
    execパターン3();
  }

  /**
   * パターン1.ベタ書き
   */
  private static void execパターン1() {
    System.out.println("パターン1 start");
    Stream<String> stream = Stream.of("A", "B", "C", "D", "E");
    stream.forEach(new Consumer<String>() {
      @Override
      public void accept(String str) {
        System.out.println("str=" + str + "...任意の処理が書けるっちゅうねん(;´･ω･)");
      }
    });
    System.out.println("パターン1 end");
  }

  /**
   * パターン2.Consumerクラスを別クラス化（Innerクラス)にして、匿名内部クラスからの分離を図る
   */
  private static void execパターン2() {
    System.out.println("パターン2 start");
    Stream<String> stream1 = Stream.of("A", "B", "C", "D", "E");
    SampleConsumer consumer1 = new SampleConsumer();
    consumer1.setNum1(1);
    consumer1.setNum2(2);// NGパターン

    Stream<String> stream2 = Stream.of("A", "B", "C", "D", "E");
    SampleConsumer consumer2 = new SampleConsumer();
    consumer2.setNum1(3);
    consumer2.setNum2(4);// NGパターン //
                         // インスタンスは別であってもstaticメンバだから、num2はconsumer1と共有してしまう.

    stream1.forEach(consumer1);
    stream2.forEach(consumer2);
    System.out.println("パターン2 end");
  }

  /**
   * パターン3.主処理となるacceptメソッドのみをtメソッド化して匿名内部クラスからの分離を図る
   */
  private static void execパターン3() {
    System.out.println("パターン3 start");
    Stream<String> stream = Stream.of("A", "B", "C", "D", "E");
    stream.forEach(new Consumer<String>() {
      @Override
      public void accept(String str) {
        acceptの主処理(str);
      }
    });
    System.out.println("パターン3 end");
  }

  private static void acceptの主処理(String str) {
    System.out.println("str=" + str + "...任意の処理が書けるっちゅうねん(;´･ω･)");
  }

  /**
   * static修飾子をつけることで、インスタンス化に際して new StreamSample().new
   * SampleConsumer();としなくていい。 インスタンス化に際して、必ずしもpublic
   * classのインスタンス化が不要だよというときなど、色々便利.
   *
   * @see http://tkmtys.hatenablog.com/entry/2015/10/11/033349<br>
   *      <br>
   *      1.クラス間の結びつきの強さを表現できる<br>
   *      2.同じクラス名でも、親クラスを含めて意味を表現できる<br>
   * @author adachi
   */
  static class SampleConsumer implements Consumer<String> {

    private int num1;// staticなクラスだからといって、フィールドまで一律static領域だから危険...ということではない.
                     // マルチスレッド環境下でstaticメソッドを使うことが必ずしも危険ではないのと理屈は同じ
    private static int num2;// 左はstaticなメンバ

    @Override
    public void accept(String str) {
      System.out.println("str=" + str + "...任意の処理が書けるっちゅうねん(;´･ω･)");
      System.out.println("num1=" + num1 + ",num2=" + num2);
    }
    public int getNum1() {
      return num1;
    }
    public void setNum1(int num1) {
      this.num1 = num1;
    }
    public static int getNum2() {
      return num2;
    }
    public static void setNum2(int num2) {
      SampleConsumer.num2 = num2;
    }
  }

}
