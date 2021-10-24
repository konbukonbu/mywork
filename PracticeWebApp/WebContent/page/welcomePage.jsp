<!DOCTYPE html>

<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>入口画面だぜ</title>
</head>
<body>
・<a href="${pageContext.request.contextPath}/page/messageBoardList.jsp">掲示板サイトの入口</a><BR/>
・<a href="${pageContext.request.contextPath}/page/googleauth/userIdRegister.jsp">GoogleAuthenticatorを使った認証サイト（登録フェーズ）</a><BR/>
・<a href="${pageContext.request.contextPath}/page/googleauth/googleAuthLogin.jsp">GoogleAuthenticatorを使った認証サイト（検証フェーズ）</a><BR/>
</body>
</html>