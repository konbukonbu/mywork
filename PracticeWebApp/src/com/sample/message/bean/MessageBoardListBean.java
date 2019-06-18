package com.sample.message.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 掲示板一覧画面の情報を表すBeanクラス
 * 
 * @author adachi
 * 
 */
public class MessageBoardListBean {
  /** タイトル */
  private String title;
  /** 本文 */
  private String content;
  /** 掲示板一覧情報 */
  private List<MessageBean> messageBeanList = new ArrayList<MessageBean>();

  /**
   * タイトルを取得します。
   * 
   * @return タイトル
   */
  public String getTitle() {
    return title;
  }

  /**
   * タイトルを設定します。
   * 
   * @param title
   *          タイトル
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * 本文を取得します。
   * 
   * @return 本文
   */
  public String getContent() {
    return content;
  }

  /**
   * 本文を設定します。
   * 
   * @param content
   *          本文
   */
  public void setContent(String content) {
    this.content = content;
  }

  /**
   * 掲示板一覧情報を取得します。
   * 
   * @return 掲示板一覧情報
   */
  public List<MessageBean> getMessageBeanList() {
    return messageBeanList;
  }

  /**
   * 掲示板一覧情報を設定します。
   * 
   * @param messageBeanList
   *          掲示板一覧情報
   */
  public void setMessageBeanList(List<MessageBean> messageBeanList) {
    this.messageBeanList = messageBeanList;
  }
}
