package com.sample.googleauth.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

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
}
