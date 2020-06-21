package com.sample.googleauth.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class UserAuthDao {

  /**
   * 引数のEntityをもとに、DBにユーザー認証情報を登録する
   */
  public static int register(Connection connection, UserAuthEntity entity) {
    StringBuilder registerSql = new StringBuilder("INSERT INTO USER_AUTH (USER_ID,COMMON_KEY)");
    registerSql.append("VALUES(?,?)");
    PreparedStatement statement = null;
    int insertNumber = 0;
    try {
      statement = (PreparedStatement) connection.prepareStatement(registerSql.toString());
      statement.setString(1, entity.userId);
      statement.setString(2, entity.commonKey);
      insertNumber = statement.executeUpdate();
    } catch (SQLException e) {
      System.out.println(registerSql.toString());
      System.out.println(statement.toString());
      e.printStackTrace();
      return insertNumber;
    }
    return insertNumber;
  }

  /**
   * DBのメッセージ一覧を全件取得する。
   *
   * @param connection
   * @return
   */
  public static String getCommonKey(Connection connection, String userid) {
    StringBuilder selectSql = new StringBuilder("SELECT COMMON_KEY FROM USER_AUTH ");
    selectSql.append("WHERE USER_ID = " + "'" + userid + "'");
    Statement statement = null;
    ResultSet resultSet = null;
    String commonKey = "";
    try {
      statement = (Statement) connection.createStatement();
      resultSet = statement.executeQuery(selectSql.toString());

      resultSet.next();
      commonKey = resultSet.getString("COMMON_KEY");
      System.out.println(commonKey);

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return commonKey;
  }
}
