package y2018.thread;

public class SampleThread extends Thread {
  private static int count = 0;

  @Override
  public void run() {
    while (true) {
      count++;
      System.out.println("処理中" + count);
      try {
        sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public void destroy() {
    System.out.println("ﾀﾋにました");
  }
}
