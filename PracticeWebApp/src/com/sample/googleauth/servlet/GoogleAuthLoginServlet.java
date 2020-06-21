package com.sample.googleauth.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sample.common.dao.DBManager;
import com.sample.googleauth.bean.GoogleAuthLoginBean;
import com.sample.googleauth.dao.UserAuthDao;
import com.warrenstrange.googleauth.GoogleAuthenticator;

public class GoogleAuthLoginServlet extends HttpServlet {
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    GoogleAuthLoginBean bean = createBeanFromRequestParameter(request);
    System.out.println(bean.getUserId());
    System.out.println(bean.getAuthCode());

    // リクパラのユーザーIDをもとにDBから共通鍵を取得
    // ======================================================
    DBManager dbManager = DBManager.getInstance();
    String commonKey = UserAuthDao.getCommonKey(dbManager.getConnection(), bean.getUserId());
    // ======================================================

    // 共通鍵をもとに、認証コードの妥当性を検証する.
    // ======================================================
    int authCode = Integer.parseInt(bean.getAuthCode());// リクパラから認証コードを取得
    boolean isAuthCheckOK = new GoogleAuthenticator().authorize(commonKey, authCode);// true:認証成功
    System.out.println("isAuthCheckOK = " + isAuthCheckOK);
    // ======================================================
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
