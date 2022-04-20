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
    PropHolder.getProp(AProp.getFilePath());//Apropのシングルトンインスタンスを取得
    
    
    key = PropConsts.BProp.key_b_2.name();
    PropHolder.getProp(BProp.getFilePath());//Bpropのシングルトンインスタンスを取得
  }

  private static void exec_業務共通の制御クラスからfilePathを取得したいとき() {
    String aPropFilePath = AProp.getFilePath();
    //APropのロード
    //Apropのシングルトンインスタンスをゲット
    PropHolder.putProp(aPropFilePath, new Object());//ホルダーにてApropインスタンスを管理

    String bPropFilePath = BProp.getFilePath();
    //BPropのロード
    //Bpropのシングルトンインスタンスをゲット
    PropHolder.putProp(bPropFilePath, new Object());//ホルダーにてBpropインスタンスを管理

    
    String cPropFilePath = CProp.getFilePath();
    //CPropのロード
    //Cpropのシングルトンインスタンスをゲット
    PropHolder.putProp(cPropFilePath, new Object());//ホルダーにてCpropインスタンスを管理
  }

}
