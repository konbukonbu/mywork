<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="messageBoardListBean" scope="session" class="com.sample.message.bean.MessageBoardListBean" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%--http://tomcat.apache.org/taglibs/standard/--%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>掲示板一覧</title>
</head>
<body>
<!-- エラーメッセージのリスト -->
<div align="center">
  <table width="600px">
    <c:forEach items="${errorMsgList}" var="message" >
      <tr height ="50px">
        <td  width="500px" align ="left"><c:out value="${message}" /></td>
      </tr>
    </c:forEach>
  </table>

<!-- 登録領域 -->
  <form method="post" action="${pageContext.request.contextPath}/messageBoardListServlet">
    <table border="1" height="100px" width="600px">
        <tr>
          <td width = "100px">タイトル</td>
          <td width = "600px" align="left">
            <input type="text" name="title" size="20" maxLength="20"/>（全角20文字）
          </td>
        </tr>
        <tr>
          <td width = "100px">本文</td>
          <td width = "600px" align="left">
            <textarea name="content" rows="4" cols="40" ></textarea>(全半角100文字）</td>
        </tr>
    </table>
    <input type="submit" name="submit" value="メッセージ登録" />
  </form>
<br><br>


<!-- メッセージリスト -->
  <table border="1" width="800px">
    <c:forEach items="${messageBoardListBean.messageBeanList}" var="message" >
      <tr height ="50px">
        <td  width="150px"><c:out value="${message.title}" /></td>
        <td  width="500px"><c:out value="${message.content}" /></td>
        <td  width="150px"><c:out value="${message.registerDate}" /></td>
      </tr>
    </c:forEach>
  </table>
</div>
</body>
</html>