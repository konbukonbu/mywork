<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="qrCodeDisplayBean" scope="session" class="com.sample.googleauth.bean.QRCodeDisplayBean" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>QRコード表示</title>
</head>
<script type="text/javascript" src="./js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="./js/jquery.qrcode.min.js"></script>
<body>

<script>
$(function() {
    $('#qrcode').qrcode(
        {
            width: 300,
            height: 300,
            text: '<c:out value="${qrCodeDisplayBean.authUrl}" />'
        }
    );
});
</script>
<B>googleAuthenticator用のURLを表示(QRコード）</B><br>
<div id="qrcode"></div>
<br>

<B>googleAuthenticator用のURLを表示(文字列）</B><br>
&nbsp;&nbsp;<c:out value="${qrCodeDisplayBean.authUrl}" />
<br><br>
googleAuthenticatorをインストールしたうえで、↑のURLを登録してください...<br>
<B><U>この画面のURLが流出されるとアウト</U></B>なので、覗き見されんようにしてくだせぇ...(；´Д｀)<br><br>
サーバ側の実装をする人は以下を留意すること(； ･`ω･´)<br>
1.HTMLをキャッシュさせないような作りにすること.<br>
2.通信の暗号化を行うこと.<br><br>

<a href="${pageContext.request.contextPath}/page/googleauth/googleAuthLogin.jsp">ログイン画面へ</a>
</body>
</html>