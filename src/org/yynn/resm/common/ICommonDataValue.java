package org.yynn.resm.common;

/**
 * <p>
 * Title: ICommonDataValue
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created 2007-6-2
 */
public interface ICommonDataValue<T> {
  /**
   * @return the displayValue
   */
  public String getDisplayValue();

  /**
   * @return the value
   */
  public T getValue();
}
