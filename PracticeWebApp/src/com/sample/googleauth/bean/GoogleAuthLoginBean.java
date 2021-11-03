package com.sample.googleauth.bean;

public class GoogleAuthLoginBean {
  /** ユーザーID */
  private String userId;

  /** 認証コード */
  private String authCode;

  /** 認証チェックがOKかの判定用プロパティ(trueなら認証OK） */
  private boolean isAuthCheckOK;
  
  /** 認証したかどうかのフラグ(1回でもしていたらtrue） */
  private boolean isAuthChecked = false;

  public boolean isAuthChecked() {
    return isAuthChecked;
  }

  public void setAuthChecked(boolean isAuthChecked) {
    this.isAuthChecked = isAuthChecked;
  }

  public boolean isAuthCheckOK() {
    return isAuthCheckOK;
  }

  public void setAuthCheckOK(boolean isAuthCheckOK) {
    this.isAuthCheckOK = isAuthCheckOK;
  }

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
