package org.yynn.resm.web.report;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 * <p>
 * Title: ReportController
 * </p>
 * <p>
 * Description: 报表生成控制器
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created 2007-6-13
 */
public class ReportController extends AbstractController implements InitializingBean {
  public static final String DEFAULT_REPORTDATAKEY = "_reportDataKey_";

  private View reportView;

  private HashMap<String, Object> parameterMap;

  /**
   * @return the parameterMap
   */
  public HashMap getParameterMap() {
    return parameterMap;
  }

  /**
   * @param parameterMap
   *          the parameterMap to set
   */
  public void setParameterMap(HashMap parameterMap) {
    this.parameterMap = parameterMap;
  }

  /**
   * @return the reportView
   */
  public View getReportView() {
    return reportView;
  }

  /**
   * @param reportView
   *          the reportView to set
   */
  public void setReportView(View reportView) {
    this.reportView = reportView;
  }

  /*
   * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest,
   *      javax.servlet.http.HttpServletResponse)
   */
  @Override
  protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ModelAndView mav = new ModelAndView(reportView);
    mav.addAllObjects(parameterMap);
    mav.addObject(DEFAULT_REPORTDATAKEY, new WebappDataSource());
    return mav;
  }

  /*
   * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
   */
  public void afterPropertiesSet() throws Exception {
    HashMap<String, Object> tempParameterMap = new HashMap<String, Object>();
    for (String key : parameterMap.keySet()) {
      Object value = parameterMap.get(key);
      if (value instanceof IReportParameterProvider) {
        tempParameterMap.put(key, ((IReportParameterProvider) value).getReportParameter());
      } else {
        tempParameterMap.put(key, value);
      }
    }
    this.parameterMap = tempParameterMap;
  }
}
