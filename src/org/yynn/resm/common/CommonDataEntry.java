package org.yynn.resm.common;

import java.io.Serializable;

/**
 * <p>
 * Title: CommonDataEntry.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @deprecated see ...
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created May 23, 2007
 */
public class CommonDataEntry<T extends Serializable> {
  private CommonDataProperty<T> property;

  private CommonDataValue<T> value;

  /**
   * @return the property
   */
  public CommonDataProperty<T> getProperty() {
    return property;
  }

  /**
   * @param property
   *          the property to set
   */
  public void setProperty(CommonDataProperty<T> property) {
    this.property = property;
  }

  /**
   * @return the value
   */
  public CommonDataValue<T> getValue() {
    return value;
  }

  /**
   * @param value
   *          the value to set
   */
  public void setValue(CommonDataValue<T> value) {
    this.value = value;
  }
}
