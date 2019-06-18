package com.sample.message.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DB接続を管理するためのクラス
 *
 * @author adachi
 *
 */
public class DBManager {
  /** 自身のインスタンスを静的プロパティとして保持する */
  private static DBManager instance = new DBManager();
  /** DBドライバクラス */
  private final static String DRV = "com.mysql.jdbc.Driver";
  /** DB接続URL(文字コードUTF-8） */
  // AWSのとき
  // private final static String URL =
  // "jdbc:mysql://aav0bacp73cqpm.cqe5ssi68kdb.us-east-1.rds.amazonaws.com:3306/test";
  private final static String URL = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8";

  /** ユーザー名 */
  private final static String USER_NAME = "root";
  /** パスワード */
  private final static String PASSWORD = "adachi";


  /*
   * : インスタンス生成時にドライバークラスを登録する。
   */
  private DBManager() {
    try {
      Class.forName(DRV);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  /**
   * 自身のインスタンスを返すstaticメソッド
   *
   * @return
   */
  public static DBManager getInstance() {
    return instance;
  }

  /**
   * DB接続のコネクションを取得する
   *
   * @return
   * @throws SQLException
   */
  public synchronized Connection getConnection() {
    Connection connection;
    try {
      connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
    return connection;
  }

  /**
   * DB接続のコネクションをクローズする。
   *
   * @param connection
   */
  public synchronized void close(Connection connection) {
    try {
      if (connection != null && connection.isClosed() == false) {
        connection.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }
}
