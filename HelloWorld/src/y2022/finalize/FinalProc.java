package y2022.finalize;

public class FinalProc {

  public void method() {
    System.out.println("Final Proc");
    // クラッシュするコード
    byte[] buffer = new byte[Integer.MAX_VALUE];
    
    
//    Runtime r = Runtime.getRuntime() ;//現在のJavaアプリケーションに関連したRuntimeインスタンスを生成
//    // クラッシュするコード
//    StringBuilder sb = new StringBuilder();
//    while (true) {
//        sb.append("1234567890");
//    }
  }
}
