<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.sample.googleauth.bean.QRCodeDisplayBean"%>
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
$(function() {//鍵付きのURL
    $('#qrcode').qrcode(
        {
            width: 250,
            height: 250,
            text:'<%= ((QRCodeDisplayBean)request.getAttribute("qrCodeDisplayBean")).getAuthUrl() %>'
        }
    );
});

$(function() {//GoogleAuthenticatorのダウンロード先
    $('#qrcode2').qrcode(
        {
            width: 250,
            height: 250,
            text:'https://apps.apple.com/jp/app/google-authenticator/id388497605'
        }
    );
});

</script>
<HR/>
<B>１．GoogleAuthenticatorに登録するURL</B><br>
  <div id="qrcode"></div>
<br>
<B>登録対象のURLを文字列で表示</B><br>
<%= ((QRCodeDisplayBean)request.getAttribute("qrCodeDisplayBean")).getAuthUrl() %>
<br>
・GoogleAuthenticatorをインストールしたうえで、↑のURLを登録してください...<br>

<HR/>
<B>２．GoogleAuthenticatorのダウンロード</B><br>
・GoogleAuthenticatorをインストールしてない人は↓でインストしてくださいな。。。🐧<br>
iPhoneのapp画面に遷移するぞよ。Androidの人は、、、めんどいから自分で探してちょんまげ(;´∀｀)<br>
  <div id="qrcode2"></div>
<HR/>

<B><U>URLが流出されるとアウト</U></B>なので、覗き見されんようにしてくだせぇ...(；´Д｀)<br><br>
サーバ側の実装をする人は以下を留意すること(； ･`ω･´)<br>
1.HTMLをキャッシュさせないような作りにすること.<br>
2.通信の暗号化を行うこと.<br><br>

<a href="${pageContext.request.contextPath}/page/googleauth/googleAuthLogin.jsp">ログイン画面へ</a>
</body>
</html>