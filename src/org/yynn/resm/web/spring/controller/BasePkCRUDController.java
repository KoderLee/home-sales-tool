package org.yynn.resm.web.spring.controller;

import java.io.Serializable;

import org.springframework.util.Assert;
import org.yynn.resm.service.CrudService;

/**
 * <p>
 * Title: BasePkCRUDController
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created 2007-6-2
 */
public abstract class BasePkCRUDController<T, PK extends Serializable> extends
    AbstractCRUDController<T, PK> {
  protected String pkName;

  public BasePkCRUDController(CrudService<T, PK> service) {
    super(service);
  }

  /*
   * @see org.yynn.resm.web.spring.controller.AbstractCRUDController#afterPropertiesSet()
   */
  @Override
  public void afterPropertiesSet() throws Exception {
    pkName = AbstractCRUDController.PK_PREFIX
        + crudPropsProvider.getPkProperty().getCproperty().getName();
    Assert.notNull(pkName, "pkName can't be null!");
    super.afterPropertiesSet();
  }

  /**
   * @return the pkName
   */
  public String getPkName() {
    return pkName;
  }

  /**
   * @param pkName
   *          the pkName to set
   */
  public void setPkName(String pkName) {
    this.pkName = pkName;
  }

}
