package org.yynn.resm.common;

/**
 * <p>
 * Title: CommonDataValue.java
 * </p>
 * <p>
 * Description: ∑‚◊∞ Ù–‘÷µ
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created May 17, 2007
 */
public class CommonDataValue<T> implements ICommonDataValue<T> {
  private T value;

  private String displayValue;

  public CommonDataValue(T value, String displayValue) {
    this.value = value;
    this.displayValue = displayValue;
  }

  public CommonDataValue(T value) {
    this.value = value;
  }

  /**
   * @return the displayValue
   */
  public String getDisplayValue() {
    return displayValue;
  }

  /**
   * @return the value
   */
  public T getValue() {
    return value;
  }

  // public String getValueAsString() {
  // if (!type.isAssignableFrom(String.class)) {
  //
  // if (type.isAssignableFrom(Long.class)) return value.toString();
  //
  // if (type.isAssignableFrom(Calendar.class)) {
  // return CalendarUtil.calendarToString((Calendar) value, "''yyyy-MM-dd''");
  // }
  // if (type.isAssignableFrom(Date.class)) {
  // return CalendarUtil.format((Date) value, "''yyyy-MM-dd''");
  // }
  // }
  //
  // return "'" + value.toString() + "'";
  // }

}
