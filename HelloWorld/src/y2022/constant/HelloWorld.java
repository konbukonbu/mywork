package y2022.constant;

import y2022.constant.PropConsts.AProp;
import y2022.constant.PropConsts.BProp;
import y2022.constant.PropConsts.CProp;

public class HelloWorld {

  public static void main(String[] args) {
    exec_個別クラスからKeyを取得したいとき();
  }

  private static void exec_個別クラスからKeyを取得したいとき() {
    String key = PropConsts.AProp.key_a_1.name();
    System.out.println(key);
    key = PropConsts.BProp.key_b_2.name();
    System.out.println(key);
  }
  
  private static void exec_業務共通の制御クラスからfilePathを取得したいとき() {
  String aPropFilePath=  AProp.getFilePath();
  String bPropFilePath=  BProp.getFilePath();
  String cPropFilePath=  CProp.getFilePath();
  }

}
