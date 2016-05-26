package org.yynn.resm.common;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * <p>
 * Title: CommonProperty.java
 * </p>
 * <p>
 * Description: 封装属性对象
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created May 17, 2007
 */
public class CommonDataProperty<T extends Serializable> implements InitializingBean {

	private String name;

	private ArrayList<ICommonDataValue<T>> valueSet;

	private ICommonDataValue<T> defaultValue;

	private Class<T> type;

	private boolean isPrimary = false;

	private ICommonDataValueSetProvider<T> valueSetProvider;

	/**
	 * @return the isPrimary
	 */
	public boolean isPrimary() {
		return isPrimary;
	}

	/**
	 * @param isPrimary the isPrimary to set
	 */
	public void setPrimary(boolean isPrimary) {
		this.isPrimary = isPrimary;
	}

	public CommonDataProperty(Class<T> type) {
		this.type = type;
	}

	public CommonDataProperty(Class<T> type, ICommonDataValueSetProvider<T> provider) {
		this.type = type;
		this.valueSetProvider = provider;
		if (provider.isPreLoad()) this.valueSet = provider.getDataValueSet();

	}

	/**
	 * @return the defautlValue
	 */
	public ICommonDataValue getDefaultValue() {
		return defaultValue;
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
	public ArrayList<ICommonDataValue<T>> getValueSet() {
		if (valueSetProvider.isPreLoad())
			return valueSet;
		else
			return valueSetProvider.getDataValueSet();
	}

	/**
	 * @param valueSet the valueSet to set
	 */
	public void setValueSet(ArrayList<ICommonDataValue<T>> valueSet) {
		this.valueSet = valueSet;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(this.name, "name 不可为空！");

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

	/**
	 * @param defautlValue the defautlValue to set
	 */
	public void setDefaultValue(ICommonDataValue<T> defaultValue) {
		this.defaultValue = defaultValue;
	}

}
