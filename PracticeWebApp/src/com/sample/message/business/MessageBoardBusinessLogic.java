package com.sample.message.business;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.sample.message.bean.MessageBean;
import com.sample.message.bean.MessageBoardListBean;
import com.sample.message.check.MessageBoardRegisterCheck;
import com.sample.message.common.DBManager;
import com.sample.message.dao.MessageDao;
import com.sample.message.dao.MessageEntity;
import com.sample.message.exception.BusinessLogicException;

/**
 * 掲示板一覧画面での業務処理を担当するクラス
 * 
 * @author adachi
 * 
 */
public class MessageBoardBusinessLogic {
  /**
   * インスタンス化させたくないのでprivateコンストラクタを定義する。
   */
  private MessageBoardBusinessLogic() {
    // do-nothing
  }

  /**
   * 入力した投稿メッセージのチェック処理とDBへの登録処理を行う。
   * 
   * @param bean
   */
  public static void registerMessage(MessageBoardListBean bean) {
    ArrayList<String> errorMsgList = (ArrayList<String>) MessageBoardRegisterCheck.checkInputData(bean);
    if (errorMsgList.isEmpty() == false) {
      throw new BusinessLogicException(errorMsgList);
    }

    MessageEntity entity = new MessageEntity();
    entity.registerData = new Timestamp(System.currentTimeMillis());
    entity.title = bean.getTitle();
    entity.content = bean.getContent();
    DBManager dmManager = DBManager.getInstance();
    if (dmManager == null) {
      errorMsgList.add("コネクション取得失敗");
      throw new BusinessLogicException(errorMsgList);
    }

    int registerNumber = MessageDao.register(dmManager.getConnection(), entity);
    if (registerNumber != 1) {
      errorMsgList.add("DB登録処理に失敗しました。");
      throw new BusinessLogicException(errorMsgList);
    }

  }

  /**
   * DBに格納している投稿メッセージ一覧を取得する。
   * 
   * @return
   */
  public static List<MessageBean> searchMessage() {
    ArrayList<String> errorMsgList = new ArrayList<String>();
    DBManager dmManager = DBManager.getInstance();
    if (dmManager == null) {
      errorMsgList.add("DBコネクションの取得に失敗しました");
      throw new BusinessLogicException(errorMsgList);
    }

    ArrayList<MessageEntity> entityList = (ArrayList<MessageEntity>) MessageDao.getMessageList(dmManager.getConnection());
    List<MessageBean> messageBeanList = new ArrayList<MessageBean>();

    for (MessageEntity entity : entityList) {
      MessageBean bean = new MessageBean();
      bean.setTitle(entity.title);
      bean.setContent(entity.content);
      bean.setRegisterDate(entity.registerData.toString());
      messageBeanList.add(bean);
    }
    return messageBeanList;
  }
}
