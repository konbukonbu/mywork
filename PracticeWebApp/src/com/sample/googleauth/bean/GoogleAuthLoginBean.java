package com.sample.googleauth.bean;

public class GoogleAuthLoginBean {
  /** ユーザーID */
  private String userId;

  /** 認証コード */
  private String authCode;

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getAuthCode() {
    return authCode;
  }

  public void setAuthCode(String authCode) {
    this.authCode = authCode;
  }

}
