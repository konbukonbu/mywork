package com.sample.message.check;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sample.message.bean.MessageBoardListBean;

/**
 * 掲示板の登録処理時の入力チェック処理をする。
 * 
 * @author adachi
 * 
 */
public class MessageBoardRegisterCheck {
  /** タイトルの最大文字数 */
  private static final int TITLE_MAX_LENGTH = 20;
  /** タイトルの文字型（全角） */
  private static final String TITLE_CHAR_TYPE = "^[^ -~｡-ﾟ]+$";
  /** タイトル未入力のエラーメッセージ */
  private static final String ERR_MSG_TITLE_REQUIRED = "タイトルを入力してください。";
  /** タイトル型チェックのエラーメッセージ */
  private static final String ERR_MSG_TITLE_CHAR_TYPE = "タイトルは全角文字で入力してください。";
  /** タイトル最大文字数オーバーのエラーメッセージ */
  private static final String ERR_MSG_TITLE_MAX_LENGTH = "タイトルは２０字以内で入力してください。";

  /** 投稿内容の最大文字数 */
  private static final int CONTENTS_MAX_LENGTH = 100;
  /** 投稿内容未入力のエラーメッセージ */
  private static final String ERR_MSG_CONTENTS_REQUIRED = "投稿内容を入力してください。";
  /** 投稿内容最大文字数オーバーのエラーメッセージ */
  private static final String ERR_MSG_CONTENTS_MAX_LENGTH = "投稿内容は１００字以内で入力してください。";

  /**
   * インスタンス化させたくないのでprivateコンストラクタを定義する。
   */
  private MessageBoardRegisterCheck() {
    // do-nothing
  }

  /**
   * 
   * @param 掲示板一覧画面での入力データ
   * @return エラーメッセージリスト
   */
  public static List<String> checkInputData(MessageBoardListBean bean) {
    // エラーメッセージを作成
    List<String> errorMessageList = new ArrayList<String>();
    checkTitle(bean.getTitle(), errorMessageList);
    checkContent(bean.getContent(), errorMessageList);

    return errorMessageList;
  }

  private static void checkTitle(String title, List<String> errorMsgList) {
    if (StringUtils.isEmpty(title)) {
      errorMsgList.add(ERR_MSG_TITLE_REQUIRED);
      return;
    }

    if (title.matches(TITLE_CHAR_TYPE) == false) {
      errorMsgList.add(ERR_MSG_TITLE_CHAR_TYPE);
    }
    if (title.length() > TITLE_MAX_LENGTH) {
      errorMsgList.add(ERR_MSG_TITLE_MAX_LENGTH);
    }
    return;
  }

  private static void checkContent(String content, List<String> errorMsgList) {
    if (StringUtils.isEmpty(content)) {
      errorMsgList.add(ERR_MSG_CONTENTS_REQUIRED);
      return;
    }
    if (content.length() > CONTENTS_MAX_LENGTH) {
      errorMsgList.add(ERR_MSG_CONTENTS_MAX_LENGTH);
    }

  }
}
