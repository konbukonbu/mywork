package com.sample.message.dao;

import java.sql.Timestamp;

/**
 * 掲示板メッセージテーブルのレコード情報を表すEntityクラス
 * 
 * @author adachi
 * 
 */
public class MessageEntity {
  /** タイトル */
  public String title;
  /** 投稿内容 */
  public String content;
  /** 登録日 */
  public Timestamp registerData;
}
