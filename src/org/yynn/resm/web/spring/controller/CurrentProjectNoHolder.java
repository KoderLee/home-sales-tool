package org.yynn.resm.web.spring.controller;

import org.yynn.resm.common.security.CurrentUserInfoProvider;

/**
 * <p>
 * Title: CurrentProjectNoHolder.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created Jun 11, 2007
 */
public class CurrentProjectNoHolder {
  private static ThreadLocal<String> currentProjectNo = new ThreadLocal<String>();

  /**
   * @return the currentProjectNo
   */
  public static String getCurrentProjectNo() {
    String cprojectNo = currentProjectNo.get();
    if (null != cprojectNo)
      return cprojectNo;
    return CurrentUserInfoProvider.getProjectNo();
  }

  /**
   * @param currentProjectNo
   *          the currentProjectNo to set
   */
  public static void setCurrentProjectNo(String currentProjectNo) {
    CurrentProjectNoHolder.currentProjectNo.set(currentProjectNo);
  }

}
