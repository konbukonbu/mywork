package com.sample.googleauth.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sample.common.dao.DBManager;
import com.sample.googleauth.bean.QRCodeDisplayBean;
import com.sample.googleauth.bean.UserIdRegisterBean;
import com.sample.googleauth.dao.UserAuthDao;
import com.sample.googleauth.dao.UserAuthEntity;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;

public class UserIdRegisterServlet extends HttpServlet {

  /** QRコード表示画面のJSPパス */
  private static final String FORWARD_PATH_QR_CODE_DISPLAY = "page/googleauth/qrCodeDisplay.jsp";

  /**
   * GoogleAuthenticator用に共通鍵を生成、認証情報を登録する.<br>
   * 主処理を追いやすくするため異常系処理は一旦、実装しない.
   */
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    UserIdRegisterBean bean = createBeanFromRequestParameter(request);

    // 認証に必要なクライアント・サーバで保持する秘密鍵(シード)を生成する.
    // ======================================================
    GoogleAuthenticatorKey key = new GoogleAuthenticator().createCredentials();
    String secretKey = key.getKey();// 秘密鍵(シード)
    // ======================================================

    // 秘密鍵をサーバ側でも保持するため、ユーザーIDとともにDB登録する.
    // =======================================================
    // Entityを生成し、ユーザーID、秘密鍵を設定.
    UserAuthEntity entity = new UserAuthEntity();
    entity.userId = bean.getUserId();// ユーザーID（リクパラ）
    entity.secretKey = secretKey;// 秘密鍵

    // entityとconnectionをもとにDB登録処理を実行.
    DBManager dbManager = DBManager.getInstance();
    UserAuthDao.register(dbManager.getConnection(), entity);
    // =======================================================

    // クライアントに返却する認証用URL(秘密鍵含む)を生成する.
    // =======================================================
    String userId = bean.getUserId();// ユーザーID
    String serviceName = "adachi.jp";// サービス名
    String authUrl = "otpauth://totp/" + serviceName + ":" + userId + "?secret=" + secretKey + "&issuer=" + serviceName;
    // =======================================================

    System.out.println("authUrl=" + authUrl);
    QRCodeDisplayBean qrCodeDisplayBean = new QRCodeDisplayBean();
    qrCodeDisplayBean.setAuthUrl(authUrl);

    request.setAttribute("qrCodeDisplayBean", qrCodeDisplayBean);
    request.getRequestDispatcher(FORWARD_PATH_QR_CODE_DISPLAY).forward(request, response);
    return;
  }

  /**
   * リクエストパラメータをもとにJavaBeanを作成する。
   *
   * @param request
   * @return
   */
  private UserIdRegisterBean createBeanFromRequestParameter(HttpServletRequest request) {
    UserIdRegisterBean userIdRegisterBean = new UserIdRegisterBean();
    userIdRegisterBean.setUserId(request.getParameter("userId"));
    return userIdRegisterBean;
  }
}
