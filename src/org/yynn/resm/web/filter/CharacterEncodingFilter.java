package org.yynn.resm.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * <p>
 * Title: CharacterEncodingFilter
 * </p>
 * <p>
 * Description: «Î«Û±‡¬Î…Ë÷√
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created 2007-5-20
 */
public class CharacterEncodingFilter implements Filter {
  private String encoding;

  private boolean ignore;

  /*
   * @see javax.servlet.Filter#destroy()
   */
  public void destroy() {

  }

  /*
   * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
   *      javax.servlet.ServletResponse, javax.servlet.FilterChain)
   */
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    if (!ignore) {
      request.setCharacterEncoding(encoding);
    }
    chain.doFilter(request, response);

  }

  /*
   * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
   */
  public void init(FilterConfig config) throws ServletException {
    encoding = config.getInitParameter("encoding");
    String s = config.getInitParameter("ignore");
    if ("true".equalsIgnoreCase(s))
      ignore = true;
    else
      ignore = false;
    ignore = false;

  }

}
