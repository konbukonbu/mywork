package com.sample.message.bean;

/**
 * 掲示板一覧画面のメッセージ一件分の情報を表すBeanクラス
 * 
 * @author adachi
 * 
 */
public class MessageBean {
  /** タイトル */
  private String title;
  /** 投稿内容 */
  private String content;
  /** 投稿日時 */
  private String registerDate;

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
   * 投稿内容を取得します。
   * 
   * @return 投稿内容
   */
  public String getContent() {
    return content;
  }

  /**
   * 投稿内容を設定します。
   * 
   * @param content
   *          投稿内容
   */
  public void setContent(String content) {
    this.content = content;
  }

  /**
   * 投稿日時を取得します。
   * 
   * @return 投稿日時
   */
  public String getRegisterDate() {
    return registerDate;
  }

  /**
   * 投稿日時を設定します。
   * 
   * @param registerDate
   *          投稿日時
   */
  public void setRegisterDate(String registerDate) {
    this.registerDate = registerDate;
  }
}
