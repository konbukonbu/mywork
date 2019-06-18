package com.sample.message.exception;

import java.util.ArrayList;

/**
 * 業務処理の例外クラス
 * 
 * @author adachi
 * 
 */
public class BusinessLogicException extends RuntimeException {
  /** エラーメッセージのリスト */
  private ArrayList<String> errorMsgList;

  public BusinessLogicException() {
    super();
  }

  public BusinessLogicException(String message) {
    super(message);
  }

  public BusinessLogicException(ArrayList<String> errorMsgList) {
    this.errorMsgList = errorMsgList;
  }

  public ArrayList<String> getErrorMsgList() {
    return errorMsgList;
  }
}
