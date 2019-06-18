package com.sample.message.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sample.message.bean.MessageBean;
import com.sample.message.bean.MessageBoardListBean;
import com.sample.message.business.MessageBoardBusinessLogic;
import com.sample.message.exception.BusinessLogicException;

/**
 * 掲示板リスト画面でのリクエストを処理する。<br>
 * 本クラスは直列化して利用することを想定していないためserialVersionUIDの定義はしない
 *
 * @author adachi
 * @see http
 *      ://www.ne.jp/asahi/hishidama/home/tech/java/serial.html#serialVersionUID
 */
@SuppressWarnings("serial")
public class MessageBoardListServlet extends HttpServlet {

  /** 掲示板リスト画面のJSPパス */
  private static final String FORWARD_PATH_MESSAGE_LIST = "page/messageBoardList.jsp";

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    MessageBoardListBean messageBoardListBean = createBeanFromRequestParameter(request);

    try {
      // 投稿者の入力したメッセージをDBに登録する。
      MessageBoardBusinessLogic.registerMessage(messageBoardListBean);
      // 掲示板の投稿メッセージをDBより検索する。
      List<MessageBean> messageList = MessageBoardBusinessLogic.searchMessage();
      messageBoardListBean.setMessageBeanList(messageList);
    } catch (BusinessLogicException e) {
      // 業務例外の場合は、例外オブジェクトが保持するエラーメッセージリストをセッションに格納し、掲示板一覧画面に遷移
      ArrayList<String> errorMsgList = e.getErrorMsgList();
      request.setAttribute("errorMsgList", errorMsgList);
      request.getRequestDispatcher(FORWARD_PATH_MESSAGE_LIST).forward(request, response);
      return;
    }

    // 出力内容をセッションに設定する。
    request.getSession().setAttribute("messageBoardListBean", messageBoardListBean);
    // 正常終了の場合は、空のエラーメッセージを設定する。
    ArrayList<String> errorMsgList = new ArrayList<String>();
    request.setAttribute("errorMsgList", errorMsgList);
    request.getRequestDispatcher(FORWARD_PATH_MESSAGE_LIST).forward(request, response);
  }

  /**
   * リクエストパラメータをもとにJavaBeanを作成する。
   *
   * @param request
   * @return
   */
  private MessageBoardListBean createBeanFromRequestParameter(HttpServletRequest request) {
    MessageBoardListBean messageBoardListBean = new MessageBoardListBean();
    messageBoardListBean.setTitle(request.getParameter("title"));
    messageBoardListBean.setContent(request.getParameter("content"));
    return messageBoardListBean;
  }


}
