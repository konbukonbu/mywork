package com.sample.googleauth.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.sample.common.dao.DBManager;
import com.sample.googleauth.bean.GoogleAuthLoginBean;
import com.sample.googleauth.dao.UserAuthDao;
import com.warrenstrange.googleauth.GoogleAuthenticator;

public class GoogleAuthLoginServlet extends HttpServlet {
  /** QRコード表示画面のJSPパス */
  private static final String FORWARD_PATH_LOGIN_DISPLAY = "page/googleauth/googleAuthLogin.jsp";

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    GoogleAuthLoginBean googleAuthLoginBean = createBeanFromRequestParameter(request);

    //TODO 入力チェック処理を仮実装（未入力の場合はシステムエラーにする）時間があったらエラー処理を本実装。🐧
    if (StringUtils.isEmpty(googleAuthLoginBean.getUserId())) {
      throw new RuntimeException("ユーザーIDが未入力やねん");
    }

    // リクパラのユーザーIDをもとにDBから秘密鍵を取得
    // ======================================================
    DBManager dbManager = DBManager.getInstance();
    String secretKey = UserAuthDao.getSecretKey(dbManager.getConnection(), googleAuthLoginBean.getUserId());
    // ======================================================

    // 秘密鍵をもとに、リクパラの認証コードの妥当性を検証する.
    // ======================================================
    int authCode = Integer.parseInt(googleAuthLoginBean.getAuthCode());// リクパラから認証コードを取得
    boolean isAuthCheckOK = new GoogleAuthenticator().authorize(secretKey, authCode);
    System.out.println("isAuthCheckOK = " + isAuthCheckOK);// true:認証成功
    // ======================================================

    googleAuthLoginBean.setAuthChecked(true);
    googleAuthLoginBean.setAuthCheckOK(isAuthCheckOK);
    request.setAttribute("googleAuthLoginBean", googleAuthLoginBean);
    request.getRequestDispatcher(FORWARD_PATH_LOGIN_DISPLAY).forward(request, response);

  }

  /**
   * リクエストパラメータをもとにJavaBeanを作成する。
   *
   * @param request
   * @return
   */
  private GoogleAuthLoginBean createBeanFromRequestParameter(HttpServletRequest request) {
    GoogleAuthLoginBean googleAuthLoginBean = new GoogleAuthLoginBean();
    googleAuthLoginBean.setUserId(request.getParameter("userId"));
    googleAuthLoginBean.setAuthCode(request.getParameter("authCode"));
    return googleAuthLoginBean;
  }
}
