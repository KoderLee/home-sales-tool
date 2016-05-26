package org.yynn.resm.common;

import java.io.Serializable;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * <p>
 * Title: UiElementProperty.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created May 29, 2007
 */
public class UiElementProperty<T extends Serializable> implements InitializingBean {
	private CommonDataProperty<T> cproperty;

	private int displayType;

	private int colspan = 2;

	private int size = 0;

	private int maxLength = 0;

	private String displayNameCode;

	private String helpInfoCode;

	public static final int DISPLAYTYPE_TEXT = 1;

	public static final int DISPLAYTYPE_RADIO = 2;

	public static final int DISPLAYTYPE_CHECKBOX = 3;

	public static final int DISPLAYTYPE_SELECT = 4;

	public static final int DISPLAYTYPE_DATE = 5;

	public static final int DISPLAYTYPE_HIDDEN = 6;

	public static final int DISPLAYTYPE_PASSWORD = 7;

	public static final int DISPLAYTYPE_IMAGE = 8;

	public static final int DISPLAYTYPE_WITH_SUFFIX = 9;

	public static final int DISPLAYTYPE_MULTI_TEXT = 10;

	private String valueSuffix;

	private boolean isDisable = false;

	private int textRows = 3;

	/**
	 * @return the isDisable
	 */
	public boolean isDisable() {
		return isDisable;
	}

	/**
	 * @param isDisable the isDisable to set
	 */
	public void setDisable(boolean isDisable) {
		this.isDisable = isDisable;
	}

	public UiElementProperty(CommonDataProperty<T> cproperty) {
		this.cproperty = cproperty;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(this.displayType, "displayType 不可为空!");
		if (DISPLAYTYPE_HIDDEN != displayType) Assert.notNull(this.displayNameCode, "显式属性需要指定displayNameCode!");
	}

	/**
	 * @return the cproperty
	 */
	public CommonDataProperty<T> getCproperty() {
		return cproperty;
	}

	/**
	 * @param cproperty the cproperty to set
	 */
	public void setCproperty(CommonDataProperty<T> cproperty) {
		this.cproperty = cproperty;
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
	 * @return the colspan
	 */
	public int getColspan() {
		return colspan;
	}

	/**
	 * @param colspan the colspan to set
	 */
	public void setColspan(int colspan) {
		this.colspan = colspan;
	}

	/**
	 * @return the displayNameCode
	 */
	public String getDisplayNameCode() {
		return displayNameCode;
	}

	/**
	 * @param displayNameCode the displayNameCode to set
	 */
	public void setDisplayNameCode(String displayNameCode) {
		this.displayNameCode = displayNameCode;
	}

	/**
	 * @return the helpInfoCode
	 */
	public String getHelpInfoCode() {
		return helpInfoCode;
	}

	/**
	 * @param helpInfoCode the helpInfoCode to set
	 */
	public void setHelpInfoCode(String helpInfoCode) {
		this.helpInfoCode = helpInfoCode;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(int length) {
		this.size = length;
	}

	/**
	 * @return the maxLength
	 */
	public int getMaxLength() {
		return maxLength;
	}

	/**
	 * @param maxLength the maxLength to set
	 */
	public void setMaxLength(int maxLenght) {
		this.maxLength = maxLenght;
	}

	/**
	 * @return the valueSuffix
	 */
	public String getValueSuffix() {
		return valueSuffix;
	}

	/**
	 * @param valueSuffix the valueSuffix to set
	 */
	public void setValueSuffix(String valueSuffix) {
		this.valueSuffix = valueSuffix;
	}

	/**
	 * @return the textRows
	 */
	public int getTextRows() {
		return textRows;
	}

	/**
	 * @param textRows the textRows to set
	 */
	public void setTextRows(int textRows) {
		this.textRows = textRows;
	}

}
