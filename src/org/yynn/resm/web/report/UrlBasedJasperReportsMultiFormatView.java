package org.yynn.resm.web.report;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.view.jasperreports.JasperReportsMultiFormatView;

/**
 * <p>
 * Title: PreparedJasperReportsMultiFormatView
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created 2007-6-13
 */
public class UrlBasedJasperReportsMultiFormatView extends JasperReportsMultiFormatView {
  private static final Logger logger = Logger.getLogger(UrlBasedJasperReportsMultiFormatView.class);

  private String prefix = "";

  private String suffix = "";

  public static final String SERVLET_CONTEXT_PATH_KEY = "_SERVLET_CONTEXT_PATH_KEY_";

  private String servletContextPathKey = SERVLET_CONTEXT_PATH_KEY;

  private String servletContextPath;


  /*
   * @see org.springframework.web.servlet.view.jasperreports.JasperReportsMultiFormatView#renderReport(net.sf.jasperreports.engine.JasperPrint,
   *      java.util.Map, javax.servlet.http.HttpServletResponse)
   */
  @Override
  protected void renderReport(JasperPrint populatedReport, Map model, HttpServletResponse response)
      throws Exception {
    super.renderReport(populatedReport, model, response);
  }

  /**
   * @return the servletContextPathKey
   */
  public String getServletContextPathKey() {
    return servletContextPathKey;
  }

  /**
   * @param servletContextPathKey
   *          the servletContextPathKey to set
   */
  public void setServletContextPathKey(String servletContextPathKey) {
    this.servletContextPathKey = servletContextPathKey;
  }

  /*
   * @see org.springframework.web.servlet.view.jasperreports.AbstractJasperReportsView#convertParameterValue(net.sf.jasperreports.engine.JRExporterParameter,
   *      java.lang.Object)
   */
  @Override
  protected Object convertParameterValue(JRExporterParameter parameter, Object value) {
    if (null == servletContextPath)
      init();
    if (value instanceof String) {
      String temp = (String) value;
      if (-1 != temp.indexOf(servletContextPathKey))
        value = temp.replaceFirst(servletContextPathKey, servletContextPath);
    }
    return super.convertParameterValue(parameter, value);
  }

  /*
   * @see org.springframework.web.servlet.view.AbstractUrlBasedView#setUrl(java.lang.String)
   */
  @Override
  public void setUrl(String url) {
    super.setUrl(prefix + url + suffix);
  }

  /**
   * @return the prefix
   */
  public String getPrefix() {
    return prefix;
  }

  /**
   * @param prefix
   *          the prefix to set
   */
  public void setPrefix(String prefix) {
    this.prefix = prefix;
  }

  /**
   * @return the suffix
   */
  public String getSuffix() {
    return suffix;
  }

  /**
   * @param suffix
   *          the suffix to set
   */
  public void setSuffix(String suffix) {
    this.suffix = suffix;
  }

  private synchronized void init() {
    if (null == this.servletContextPath) {
      String path;
      try {
        path = getServletContext().getResource("/").getPath();
        path = path.substring(0, path.lastIndexOf("/"));
        this.servletContextPath = path.substring(path.lastIndexOf("/"));
      } catch (MalformedURLException e) {
        logger.warn("Init fail: " + e.getMessage());
      } catch (IllegalStateException e) {
        logger.warn("Init fail: " + e.getMessage());
      }
    }

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.springframework.web.servlet.view.AbstractUrlBasedView#afterPropertiesSet()
   */
  @Override
  public void afterPropertiesSet() throws Exception {
    if (null == servletContextPath)
      init();
    Map exporterParameters = getExporterParameters();
    if (exporterParameters != null && !exporterParameters.isEmpty()) {
      Map replaceMap = new HashMap(exporterParameters.size());
      for (Iterator it = exporterParameters.keySet().iterator(); it.hasNext();) {
        Object key = it.next();
        Object value = exporterParameters.get(key);
        if (value instanceof String) {
          String temp = (String) value;
          if (-1 != temp.indexOf(servletContextPathKey))
            value = temp.replaceFirst(servletContextPathKey, servletContextPath);
        }
        replaceMap.put(key, value);
      }
      super.setExporterParameters(replaceMap);
    }
    super.afterPropertiesSet();
  }

}
