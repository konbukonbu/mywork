<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="userIdRegisterBean" scope="session" class="com.sample.googleauth.bean.UserIdRegisterBean" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%--http://tomcat.apache.org/taglibs/standard/--%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザーID登録</title>
</head>
<body>
<!-- 登録領域-->
  <form method="post" action="${pageContext.request.contextPath}/userIdRegisterServlet">
    <table border="1" height="70px" width="900px">
        <tr>
          <td width = "100px">ユーザーID</td>
          <td width = "600px" align="left">
            <input type="text" name="userId" size="80" maxLength="255"/>（半角255文字）
          </td>
        </tr>
    </table>
    <input type="submit" name="submit" value="ユーザーID登録" />
  </form>
<br><br>

</body>
</html>