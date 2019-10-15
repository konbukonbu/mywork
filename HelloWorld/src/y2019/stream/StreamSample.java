package y2019.stream;

import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * @see https://qiita.com/rubytomato@github/items/93011c75ee4af6b59452
 * @author adachi
 */
public class StreamSample {

  public static void main(String[] args) {
    Stream<String> stream = Stream.of("A", "B", "C", "D", "E");

    stream.forEach(new Consumer<String>() {
      @Override
      public void accept(String str) {
        System.out.println(str);
        System.out.println("任意の処理が書けるっちゅうねん(;´･ω･)");
      }
    });
  }

}
