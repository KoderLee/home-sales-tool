package org.yynn.resm.common;

import org.yynn.resm.common.security.CurrentUserInfoProvider;

/**
 * <p>
 * Title: DynCommonDataValue
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created 2007-6-2
 */
public class ProjectNoDataValue implements ICommonDataValue {

  /*
   * @see org.yynn.resm.common.ICommonDataValue#getDisplayValue()
   */
  public String getDisplayValue() {
    return CurrentUserInfoProvider.getProjectNo();
  }

  /*
   * @see org.yynn.resm.common.ICommonDataValue#getValue()
   */
  public String getValue() {
    return CurrentUserInfoProvider.getProjectNo();
  }
}
