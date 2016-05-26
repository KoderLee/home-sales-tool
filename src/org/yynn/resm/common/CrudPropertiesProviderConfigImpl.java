package org.yynn.resm.common;

import java.util.ArrayList;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * <p>
 * Title: CrudPropertiesProviderConfigImpl
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created 2007-6-5
 */
public class CrudPropertiesProviderConfigImpl implements ICrudPropertiesProvider, InitializingBean {

  private ArrayList<QueryDataProperty> queryDataProperties;

  private ArrayList<UiElementProperty> createFormProperties;

  private ArrayList<UiElementProperty> editFormProperties;

  private ArrayList<UiElementProperty> queryFormProperties;

  private ArrayList<UiElementProperty> queryListProperties;

  private ArrayList<UiElementProperty> viewFormProperties;

  private UiElementProperty pkElement;

  /**
   * @param createFormProperties
   *          the createFormProperties to set
   */
  public void setCreateFormProperties(ArrayList createFormProperties) {
    this.createFormProperties = createFormProperties;
  }

  /**
   * @param editFormProperties
   *          the editFormProperties to set
   */
  public void setEditFormProperties(ArrayList editFormProperties) {
    this.editFormProperties = editFormProperties;
  }

  /**
   * @param queryDataProperties
   *          the queryDataProperties to set
   */
  public void setQueryDataProperties(ArrayList queryDataProperties) {
    this.queryDataProperties = queryDataProperties;
  }

  /**
   * @param queryFormProperties
   *          the queryFormProperties to set
   */
  public void setQueryFormProperties(ArrayList queryFormProperties) {
    this.queryFormProperties = queryFormProperties;
  }

  /**
   * @param queryListProperties
   *          the queryListProperties to set
   */
  public void setQueryListProperties(ArrayList queryListProperties) {
    this.queryListProperties = queryListProperties;
  }

  /**
   * @param viewFormProperties
   *          the viewFormProperties to set
   */
  public void setViewListProperties(ArrayList viewFormProperties) {
    this.viewFormProperties = viewFormProperties;
  }

  /*
   * @see org.yynn.resm.common.ICrudPropertiesProvider#getCreateFormProperties()
   */
  public ArrayList<UiElementProperty> getCreateFormProperties() {
    return createFormProperties;
  }

  /*
   * @see org.yynn.resm.common.ICrudPropertiesProvider#getEditFormProperties()
   */
  public ArrayList<UiElementProperty> getEditFormProperties() {
    return editFormProperties;
  }

  /*
   * @see org.yynn.resm.common.ICrudPropertiesProvider#getQueryDataProperties()
   */
  public ArrayList<QueryDataProperty> getQueryDataProperties() {
    return queryDataProperties;
  }

  /*
   * @see org.yynn.resm.common.ICrudPropertiesProvider#getQueryFormProperties()
   */
  public ArrayList<UiElementProperty> getQueryFormProperties() {
    return queryFormProperties;
  }

  /*
   * @see org.yynn.resm.common.ICrudPropertiesProvider#getQueryListProperties()
   */
  public ArrayList<UiElementProperty> getQueryListProperties() {

    return queryListProperties;
  }

  /*
   * @see org.yynn.resm.common.ICrudPropertiesProvider#getViewFormProperties()
   */
  public ArrayList<UiElementProperty> getViewFormProperties() {
    return viewFormProperties;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.yynn.resm.common.ICrudPropertiesProvider#getPkProperty()
   */
  public UiElementProperty getPkProperty() {
    return this.pkElement;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
   */
  public void afterPropertiesSet() throws Exception {
    if (null != editFormProperties) {
      for (UiElementProperty up : editFormProperties) {
        if (up.getCproperty().isPrimary()) {
          this.pkElement = up;
          break;
        }
      }
      Assert.notNull(pkElement, "Must be a pk property in editFormProperties!");
    }
    if (null != queryDataProperties) {
      for (Object up : queryDataProperties) {
        Assert.isTrue(up instanceof QueryDataProperty,
            "Each element must be an instance of QueryDataProperty!");
      }
    }
  }

}
