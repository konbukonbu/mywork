package com.sample.message.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

/**
 * 掲示板メッセージのデータアクセス用クラス
 *
 * @author adachi
 *
 */
public class MessageDao {

  /**
   * 引数のEntityをもとに、DBにメッセージを登録する
   */
  public static int register(Connection connection, MessageEntity entity) {
    StringBuilder registerSql = new StringBuilder("INSERT INTO MESSAGE (TITLE,CONTENT,REGISTER_DATE)");
    registerSql.append("VALUES(?,?,?)");
    PreparedStatement statement = null;
    int insertNumber = 0;
    try {
      statement = (PreparedStatement) connection.prepareStatement(registerSql.toString());
      statement.setString(1, entity.title);
      statement.setString(2, entity.content);
      statement.setTimestamp(3, entity.registerData);
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
  public static List<MessageEntity> getMessageList(Connection connection) {
    StringBuilder selectSql = new StringBuilder("SELECT TITLE,CONTENT,REGISTER_DATE FROM MESSAGE ");
    selectSql.append("ORDER BY REGISTER_DATE DESC");
    Statement statement = null;
    ResultSet resultSet = null;
    List<MessageEntity> messageEntitiyList = new ArrayList<MessageEntity>();
    try {
      statement = (Statement) connection.createStatement();
      resultSet = statement.executeQuery(selectSql.toString());

      while (resultSet.next()) {
        MessageEntity entity = new MessageEntity();
        entity.title = resultSet.getString("title");
        entity.content = resultSet.getString("content");
        entity.registerData = resultSet.getTimestamp("register_date");

        messageEntitiyList.add(entity);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return messageEntitiyList;
  }
}
