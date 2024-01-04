package com.sample.y2024;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.Appender;

public class LogSampleMain {

  public static void main(String[] args) {
    LogSampleMain main = new LogSampleMain();
    main.getAppender();
  }

  /**
   * @see https://portal-freud.hatenablog.com/entry/2022/09/29/024526
   * @see https://qiita.com/shase428q/items/6c7699ea3fd75add2664
   */
  public void getAppender() {
    //LoggerFactory#getILoggerFactoryにて+LoggerContextを取得.
    LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
    //appenderを取得するために、まず紐づくloggerを取得.
    Logger logger = context.getLogger("com.sample");
    //rootロガーを取得する場合
    // logger = context.getLogger("ROOT");

    //appenderの名前を指定すれば、任意のappenderをlogger経由で取得可能。
    // logback.xml側の定義例：<appender name="FILE_APP" class="ch.qos.logback.core.rolling.RollingFileAppender">
    Appender appender = logger.getAppender("FILE_APP");//

    System.out.println(appender.toString());//出力例：ch.qos.logback.core.rolling.RollingFileAppender[FILE_APP]
    logger.debug("testtest!");
  }

}
