package org.yynn.resm.common;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * <p>
 * Title: CommonProperty.java
 * </p>
 * <p>
 * Description: 封装属性对象
 * </p>
 * @deprecated see {@link CommonDataProperty}
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created May 17, 2007
 */
public class CommonProperty<T> implements InitializingBean {

	public static final int DISPLAYTYPE_TEXT = 1;

	public static final int DISPLAYTYPE_RADIO = 2;

	public static final int DISPLAYTYPE_CHECKBOX = 3;

	public static final int DISPLAYTYPE_SELECT = 4;

	public static final int DISPLAYTYPE_DATE = 5;

	private String name;

	private int displayType;

	private ArrayList<CommonValue> valueSet;

	private String displayName;

	private CommonValue defautlValue;

	/**
	 * @return the defautlValue
	 */
	public CommonValue getDefautlValue() {
		return defautlValue;
	}

	/**
	 * @param defautlValue the defautlValue to set
	 */
	public void setDefautlValue(CommonValue defautlValue) {
		this.defautlValue = defautlValue;
	}

	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * @return the displayType
	 */
	public int getDisplayType() {
		return displayType;
	}

	/**
	 * @param displayType the displayType to set
	 */
	public void setDisplayType(int displayType) {
		this.displayType = displayType;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the valueSet
	 */
	public List getValueSet() {
		return valueSet;
	}

	/**
	 * @param valueSet the valueSet to set
	 */
	public void setValueSet(ArrayList<CommonValue> valueSet) {
		this.valueSet = valueSet;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(this.name, "name 不可为空！");
		Assert.notNull(this.displayType, "displayType 不可为空！");
	}

}
