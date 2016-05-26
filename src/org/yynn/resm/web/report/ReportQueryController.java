package org.yynn.resm.web.report;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.yynn.resm.common.ICrudPropertiesProvider;
import org.yynn.resm.common.QueryDataEntry;
import org.yynn.resm.common.QueryDataProperty;
import org.yynn.resm.common.security.CurrentUserInfoProvider;
import org.yynn.resm.web.spring.controller.IServletRequestDataBinderFactory;
import org.yynn.resm.web.spring.controller.ModelBeanWrapperImpl;

/**
 * <p>
 * Title: ReportQueryController
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created 2007-6-23
 */
public class ReportQueryController extends SimpleFormController implements InitializingBean {

  public static final String PROPERTIES_PROVIDER_KEY = "PROPERTIES_PROVIDER_KEY";

  public final static String CONTROLLER_TYPE_KEY = "CONTROLLER_TYPE_KEY";

  public final static String DEFAUTL_QUERYAPPEND_KEY = "queryAppend";

  public final static String DEFAULT_CURRENTUSER_NAME_KEY = "cuser";

  private Map<String, Object> attributes;

  protected ICrudPropertiesProvider crudPropsProvider;

  private View reportView;

  private HashMap<String, Object> parameterMap;

  private String queryAppendKey = DEFAUTL_QUERYAPPEND_KEY;

  private IServletRequestDataBinderFactory dataBinderFactory;

  private String currentUserNameKey = DEFAULT_CURRENTUSER_NAME_KEY;

  /**
   * @return the currentUserNameKey
   */
  public String getCurrentUserNameKey() {
    return currentUserNameKey;
  }

  /**
   * @param currentUserNameKey
   *          the currentUserNameKey to set
   */
  public void setCurrentUserNameKey(String currentUserNameKey) {
    this.currentUserNameKey = currentUserNameKey;
  }

  /**
   * @return the dataBinderFactory
   */
  public IServletRequestDataBinderFactory getDataBinderFactory() {
    return dataBinderFactory;
  }

  /**
   * @param dataBinderFactory
   *          the dataBinderFactory to set
   */
  public void setDataBinderFactory(IServletRequestDataBinderFactory dataBinderFactory) {
    this.dataBinderFactory = dataBinderFactory;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.springframework.web.servlet.mvc.BaseCommandController#createBinder(javax.servlet.http.HttpServletRequest,
   *      java.lang.Object)
   */
  @Override
  protected ServletRequestDataBinder createBinder(HttpServletRequest request, Object command)
      throws Exception {
    ServletRequestDataBinder binder = dataBinderFactory.createServletRequestDataBinder(command,
        getCommandName());
    prepareBinder(binder);
    initBinder(request, binder);
    return binder;
  }

  /**
   * @return the queryAppendKey
   */
  public String getQueryAppendKey() {
    return queryAppendKey;
  }

  /**
   * @param queryAppendKey
   *          the queryAppendKey to set
   */
  public void setQueryAppendKey(String queryAppendKey) {
    this.queryAppendKey = queryAppendKey;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.springframework.web.servlet.mvc.AbstractFormController#handleRequestInternal(javax.servlet.http.HttpServletRequest,
   *      javax.servlet.http.HttpServletResponse)
   */
  @Override
  protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    request.setAttribute(PROPERTIES_PROVIDER_KEY, crudPropsProvider);
    request.setAttribute(CONTROLLER_TYPE_KEY, this.getClass());
    if (null != attributes) {
      for (String key : attributes.keySet()) {
        request.setAttribute(key, attributes.get(key));
      }
    }
    return super.handleRequestInternal(request, response);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest,
   *      javax.servlet.http.HttpServletResponse, java.lang.Object,
   *      org.springframework.validation.BindException)
   */
  @Override
  protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response,
      Object command, BindException errors) throws Exception {
    ModelAndView mav = new ModelAndView(reportView);
    ArrayList<QueryDataEntry> ql = new ArrayList<QueryDataEntry>();
    ModelBeanWrapperImpl cmdw = new ModelBeanWrapperImpl(command);
    for (QueryDataProperty property : crudPropsProvider.getQueryDataProperties()) {
      Serializable value = (Serializable) cmdw.getPropertyValue(property.getName());
      mav.addObject(property.getName(), value);
      ql.add(new QueryDataEntry(property, value));
    }

    StringBuffer queryAppend = new StringBuffer("(1=1) ");
    for (QueryDataEntry qo : ql) {
      String temp = qo.generateHql();
      if (null != temp) {
        queryAppend.append("and ");
        queryAppend.append(temp);
      }
    }
    mav.addObject(queryAppendKey, queryAppend.toString());
    mav.addObject(currentUserNameKey, CurrentUserInfoProvider.getUserName());

    mav.addAllObjects(parameterMap);
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

  /**
   * @return the attributes
   */
  public Map<String, Object> getAttributes() {
    return attributes;
  }

  /**
   * @param attributes
   *          the attributes to set
   */
  public void setAttributes(Map<String, Object> attributes) {
    this.attributes = attributes;
  }

  /**
   * @return the crudPropsProvider
   */
  public ICrudPropertiesProvider getCrudPropsProvider() {
    return crudPropsProvider;
  }

  /**
   * @param crudPropsProvider
   *          the crudPropsProvider to set
   */
  public void setCrudPropsProvider(ICrudPropertiesProvider crudPropsProvider) {
    this.crudPropsProvider = crudPropsProvider;
  }

  /**
   * @return the parameterMap
   */
  public HashMap<String, Object> getParameterMap() {
    return parameterMap;
  }

  /**
   * @param parameterMap
   *          the parameterMap to set
   */
  public void setParameterMap(HashMap<String, Object> parameterMap) {
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
}
