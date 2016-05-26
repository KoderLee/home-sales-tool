package org.yynn.resm.web.spring.controller;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.BindException;
import org.yynn.resm.service.CrudService;

/**
 * <p>
 * Title: AddController.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created May 30, 2007
 */
public class AddController<T, PK extends Serializable> extends AbstractCRUDController<T, PK> {

  public AddController(CrudService<T, PK> service) {
    super(service);
  }

  private String modelName = ViewController.DEFAULT_MODELVALUE_NAME;

  private String addFailCode = "common.add.exist";

  public static final String ADD_PROCESS_FAIL_KEY = "ADD_PROCESS_FAIL_KEY";

  /**
   * @return the modelName
   */
  public String getModelName() {
    return modelName;
  }

  /**
   * @param modelName
   *          the modelName to set
   */
  public void setModelName(String modelName) {
    this.modelName = modelName;
  }

  /**
   * @return the addFailCode
   */
  public String getAddFailCode() {
    return addFailCode;
  }

  /**
   * @param addFailCode
   *          the addFailCode to set
   */
  public void setAddFailCode(String addFailCode) {
    this.addFailCode = addFailCode;
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
    try {
      service.create((T) command);
    } catch (DataIntegrityViolationException ex) {
      request.setAttribute(ADD_PROCESS_FAIL_KEY, addFailCode);
      errors.reject(this.addFailCode);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.yynn.resm.web.spring.controller.AbstractCRUDController#onBindAndValidate(javax.servlet.http.HttpServletRequest,
   *      java.lang.Object, org.springframework.validation.BindException)
   */
  @Override
  protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
      throws Exception {
    request.setAttribute(modelName, command);
    super.onBindAndValidate(request, command, errors);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.yynn.resm.web.spring.controller.AbstractCRUDController#onSuccess(javax.servlet.http.HttpServletRequest)
   */
  @Override
  protected void onSuccess(HttpServletRequest request) {
    request.setAttribute(modelName, null);
    super.onSuccess(request);
  }
}
