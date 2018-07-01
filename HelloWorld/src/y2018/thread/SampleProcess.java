package y2018.thread;

public class SampleProcess {

  private static int count = 0;

  public static void processExecute() {
    //TODO do-something
    while (true) {
      try {
        count ++;
        Thread.sleep(1000);
        System.out.println("処理中" + count);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

  }
}
