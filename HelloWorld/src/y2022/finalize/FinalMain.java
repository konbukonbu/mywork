package y2022.finalize;

/**
 * 
 * @author User
 *@see https://www.fujitsu.com/jp/documents/products/software/resources/technical/interstage/apserver/guide/Finalizer-GC.pdf
 */
public class FinalMain {

  int cnt = 0;

  public static void main(String[] args) {
    FinalMain finalMain = new FinalMain();
    try {
      finalMain.init();
      finalMain.mainProc();
    } finally {
      finalMain.finalize();
    }
  }

  public void init() {
    System.out.println("init");
  }

  public void mainProc() {
    System.out.println("mainProc");
  }

  @Override
  public void finalize() {
    System.out.println("finalize");

    try {
      FinalProc finalProc = new FinalProc();
      finalProc.method();
    } finally {
      System.out.println("finalize try-finally");
    }
  }
}
