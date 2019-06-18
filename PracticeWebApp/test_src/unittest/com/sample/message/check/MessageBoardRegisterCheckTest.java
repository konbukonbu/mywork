package unittest.com.sample.message.check;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.sample.message.bean.MessageBoardListBean;
import com.sample.message.check.MessageBoardRegisterCheck;

/**
 * Checkクラスのユニットテストコードのサンプル
 * 
 * @author adachi
 *
 */
public class MessageBoardRegisterCheckTest {
  @Test
  public void test_checkInputData_タイトルが空() throws Exception {
    /** テスト条件の設定 */
    MessageBoardListBean bean = new MessageBoardListBean();
    bean.setTitle("");// タイトルに空文字を設定
    bean.setContent("メッセージ内容");

    /** テスト対象メソッドの実行 */
    List<String> errorMsgList = MessageBoardRegisterCheck.checkInputData(bean);

    /** テスト内容の検証 */
    // エラーメッセージが1件であることを検証する。
    Assert.assertEquals(1, errorMsgList.size());

    // エラーメッセージ内容が適切であることを検証する。
    String expectedMsg = "タイトルを入力してください。";// 期待値
    String actualMsg = errorMsgList.get(0);// 実際の値
    Assert.assertEquals(expectedMsg, actualMsg);
  }

  @Test
  public void test_checkInputData_コンテンツが空() throws Exception {
    /** テスト条件の設定 */
    MessageBoardListBean bean = new MessageBoardListBean();
    bean.setTitle("タイトル");
    bean.setContent("");// コンテンツに空文字を設定

    /** テスト対象メソッドの実行 */
    List<String> errorMsgList = MessageBoardRegisterCheck.checkInputData(bean);

    /** テスト内容の検証 */
    // エラーメッセージが1件であることを検証する。
    Assert.assertEquals(1, errorMsgList.size());

    // エラーメッセージ内容が適切であることを検証する。
    String expectedMsg = "投稿内容を入力してください。";// 期待値
    String actualMsg = errorMsgList.get(0);// 実際の値
    Assert.assertEquals(expectedMsg, actualMsg);
  }
}
