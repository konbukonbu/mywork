package unittest.com.sample.message.business;

import java.util.ArrayList;

import jp.co.dgic.testing.common.virtualmock.MockObjectManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.sample.message.bean.MessageBoardListBean;
import com.sample.message.business.MessageBoardBusinessLogic;
import com.sample.message.common.DBManager;
import com.sample.message.exception.BusinessLogicException;

/**
 * JUnitコードおよびdJUnitのサンプルコード<br/>
 * djUnitを利用の場合、必要に応じてdjUnitのプラグインをインストールしてください
 * 
 * @author adachi
 * @see Eclipseプラグインインストールの更新サイト
 *      http://works.dgic.co.jp/djunit/update/3.5.x/site.xml
 * @see djUnitのwiki http://works.dgic.co.jp/djwiki/Viewpage.do?pid=@E38386E382B9E38388E382B1E383BCE382B9E381AEE6BA96E58299
 */
public class MessageBoardBusinessLogicTest {
  @Before
  public void setUp() throws Exception {
    MockObjectManager.initialize(); // djUnitのモックオブジェクトの初期化処理
  }

  /**
   * 例外のスローを検証するサンプルコード
   * 
   * @throws Exception
   */
  @Test
  public void test_registerMessage_入力エラーの場合() throws Exception {
    /** テスト条件の設定 */
    MessageBoardListBean bean = new MessageBoardListBean();
    bean.setTitle("");// タイトルに空文字を設定
    bean.setContent("メッセージ内容");

    try {
      /** テスト対象メソッドの実行 */
      MessageBoardBusinessLogic.registerMessage(bean);
      Assert.fail("BusinessLogicExceptionが発生しませんでした。");
    } catch (BusinessLogicException e) {// BusinessLogicExceptionがスローされたことを検証
      /** テスト内容の検証 */
      // エラーメッセージが1件であることを検証する。
      ArrayList<String> errorMsgList = e.getErrorMsgList();
      Assert.assertEquals(1, errorMsgList.size());

      // エラーメッセージ内容が適切であることを検証する。
      String expectedMsg = "タイトルを入力してください。";// 期待値
      String actualMsg = errorMsgList.get(0);// 実際の値
      Assert.assertEquals(expectedMsg, actualMsg);
    }
  }

  /**
   * djUnitを使用して戻り値を設定するサンプルコード
   * 
   * @throws Exception
   */
  @Test
  public void test_registerMessage_DBManagerからインスタンスを取得できない場合() throws Exception {
    /** テスト条件の設定 */
    MessageBoardListBean bean = new MessageBoardListBean();
    bean.setTitle("タイトル");
    bean.setContent("メッセージ内容");

    // DBManager#getInstanceの返り値をnullに設定
    // MockObjectManager#setReturnXXXにてメソッド戻り値を操作できる。
    MockObjectManager.setReturnNullAtAllTimes(DBManager.class, "getInstance");

    try {
      /** テスト対象メソッドの実行 */
      MessageBoardBusinessLogic.registerMessage(bean);
      Assert.fail("BusinessLogicExceptionが発生しませんでした。");
    } catch (BusinessLogicException e) {// BusinessLogicExceptionがスローされたことを検証
      /** テスト内容の検証 */
      // エラーメッセージが1件であることを検証する。
      ArrayList<String> errorMsgList = e.getErrorMsgList();
      Assert.assertEquals(1, errorMsgList.size());

      // エラーメッセージ内容が適切であることを検証する。
      String expectedMsg = "コネクション取得失敗。";// 期待値
      String actualMsg = errorMsgList.get(0);// 実際の値
      Assert.assertEquals(expectedMsg, actualMsg);
    }

  }
}
