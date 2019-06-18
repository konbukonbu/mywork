package com.sample.message.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * エンコーディング処理をするためのFilterクラス
 *
 * @author adachi
 *
 */
public class EncodingFilter implements Filter {
  /** エンコーディング方式の指定 */
  private String encoding = "UTF-8";

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    encoding = filterConfig.getInitParameter("encoding");
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    request.setCharacterEncoding(encoding);
    chain.doFilter(request, response);
  }


  @Override
  public void destroy() {
    // do-nothing
  }

}
