package org.yynn.resm.web.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 * <p>
 * Title: CommonViewController
 * </p>
 * <p>
 * Description: 普通控制器,可以转发请求到一个view.
 * </p>
 * @deprecated for nothing,hahhhah
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created 2007-5-20
 */
public class CommonController extends AbstractController {
  private String viewName;

  /*
   * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest,
   *      javax.servlet.http.HttpServletResponse)
   */
  @Override
  protected ModelAndView handleRequestInternal(HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    return new ModelAndView(viewName);
  }

  /**
   * @return the viewName
   */
  public String getViewName() {
    return viewName;
  }

  /**
   * @param viewName
   *          the viewName to set
   */
  public void setViewName(String viewName) {
    this.viewName = viewName;
  }

}
