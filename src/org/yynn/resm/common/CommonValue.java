package org.yynn.resm.common;

/**
 * <p>
 * Title: CommonValue.java
 * </p>
 * <p>
 * Description: ∑‚◊∞ Ù–‘÷µ
 * </p>
 * 
 * @deprecated see {@link CommonDataValue}
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created May 17, 2007
 */
public class CommonValue<T> {
	private T value;

	private String displayValue;

	private Class<T> type;

	public CommonValue(Class<T> valueType) {
		this.type = valueType;
	}

	/**
	 * @return the displayValue
	 */
	public String getDisplayValue() {
		return displayValue;
	}

	/**
	 * @param displayValue the displayValue to set
	 */
	public void setDisplayValue(String displayValue) {
		this.displayValue = displayValue;
	}

	/**
	 * @return the value
	 */
	public T getValue() {
		return value;
	}

	/**
	 * @return the type
	 */
	public Class<T> getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Class<T> type) {
		this.type = type;
	}

}
