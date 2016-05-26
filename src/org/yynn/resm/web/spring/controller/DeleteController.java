package org.yynn.resm.web.spring.controller;

import java.io.Serializable;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.BindException;
import org.yynn.resm.common.QueryDataEntry;
import org.yynn.resm.common.QueryDataProperty;
import org.yynn.resm.service.CrudService;

/**
 * <p>
 * Title: QueryController.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created May 23, 2007
 */
public class DeleteController<T, PK extends Serializable> extends BasePkCRUDController<T, PK> {
//  private static final Logger logger = Logger.getLogger(DeleteController.class);

  private String queryResultName = "queryResult";

  private String deleteFailCode = "common.delete.inuse";

  public DeleteController(CrudService<T, PK> service) {
    super(service);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest,
   *      javax.servlet.http.HttpServletResponse, java.lang.Object,
   *      org.springframework.validation.BindException)
   */
  @Override
  protected void doSubmit(HttpServletRequest request, HttpServletResponse response, Object command,
      BindException errors) throws Exception {
    String temp = request.getParameter(pkName);
    try {
      if (service.getPKType().isAssignableFrom(Integer.class)) {
        service.delete((PK) new Integer(temp));
      } else if (service.getPKType().isAssignableFrom(String.class)) {
        service.delete((PK) temp);
      } else if (service.getPKType().isAssignableFrom(Long.class)) {
        service.delete((PK) new Long(temp));
      } else {
        throw new UnsupportedOperationException("PkType Must Be Long or String or Integer,sorry!");
      }
    } catch (DataIntegrityViolationException ex) {
      errors.reject(this.deleteFailCode);
    }
    ArrayList<QueryDataEntry> ql = new ArrayList<QueryDataEntry>();
    ModelBeanWrapperImpl cmdw = new ModelBeanWrapperImpl(command);
    for (QueryDataProperty property : crudPropsProvider.getQueryDataProperties()) {
      ql.add(new QueryDataEntry(property, (Serializable) cmdw.getPropertyValue(property.getName())));
    }
    request.setAttribute(queryResultName, service.query(ql));
    request.setAttribute(QueryController.DISPLAYATTRS, crudPropsProvider.getQueryListProperties());
  }

  /**
   * @return the queryResultName
   */
  public String getQueryResultName() {
    return queryResultName;
  }

  /**
   * @param queryResultName
   *          the queryResultName to set
   */
  public void setQueryResultName(String queryResultName) {
    this.queryResultName = queryResultName;
  }

  /**
   * @return the deleteFailCode
   */
  public String getDeleteFailCode() {
    return deleteFailCode;
  }

  /**
   * @param deleteFailCode
   *          the deleteFailCode to set
   */
  public void setDeleteFailCode(String deleteFailCode) {
    this.deleteFailCode = deleteFailCode;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.yynn.resm.web.spring.controller.BasePkCRUDController#afterPropertiesSet()
   */
  @Override
  public void afterPropertiesSet() throws Exception {
    setFailCode(this.deleteFailCode);
    super.afterPropertiesSet();
  }

}
