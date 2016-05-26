package org.yynn.resm.common;

import java.io.Serializable;

import org.yynn.resm.dao.CrudDAO;
import org.yynn.resm.web.spring.controller.ModelBeanWrapperImpl;

/**
 * <p>
 * Title: CommonMultiValueProviderDBImpl
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created 2007-6-20
 */
public class CommonMultiValueProviderDBImpl<T, PK extends Serializable> extends
    CommonValueProviderDBImpl<T, PK> {
  private String[] displayNames;

  private String seperator = "-";

  public CommonMultiValueProviderDBImpl(CrudDAO<T, PK> crudDao) {
    super(crudDao);
  }

  /*
   * @see org.yynn.resm.common.CommonValueProviderDBImpl#getCommonDataValue(java.lang.Object)
   */
  @Override
  protected ICommonDataValue<PK> getCommonDataValue(T t) {
    ModelBeanWrapperImpl bwi = new ModelBeanWrapperImpl(t);
    PK value = (PK) bwi.getPropertyValue(getPkName());

    if (null != displayNames) {
      StringBuffer displayValue = new StringBuffer("");
      for (String displayName : displayNames) {
        if (null != displayName)
          displayValue.append(bwi.getPropertyValue(displayName)).append(seperator);
      }
      if (displayValue.length() > 0)
        return new CommonDataValue<PK>(value, displayValue.substring(0, displayValue.length() - 1));
    }
    return new CommonDataValue<PK>(value);
  }

  /**
   * @return the displayNames
   */
  public String[] getDisplayNames() {
    return displayNames;
  }

  /**
   * @param displayNames
   *          the displayNames to set
   */
  public void setDisplayNames(String[] displayNames) {
    this.displayNames = displayNames;
  }

  /**
   * @return the seperator
   */
  public String getSeperator() {
    return seperator;
  }

  /**
   * @param seperator
   *          the seperator to set
   */
  public void setSeperator(String seperator) {
    this.seperator = seperator;
  }

}
