package org.yynn.resm.web.filter.validator;

import org.yynn.resm.common.UiElementProperty;

/**
 * <p>
 * Title: NumberValidationRule.java
 * </p>
 * <p>
 * Description: 数值校验
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created May 23, 2007
 */
public class NumberValidationRule extends AbstractValidationRule {
	private boolean checkMaxMin = false;

	private long max = Long.MAX_VALUE;

	private long min = Long.MIN_VALUE;

	public NumberValidationRule(UiElementProperty property) {
		super(property);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.yynn.resm.web.filter.validator.AbstractValidationRule#isValidate(java.lang.Object)
	 */
	@Override
	protected boolean isValidate(Object value) {
		if (null == value) return false;
		if (!(value instanceof Number)) return false;
		Number num = (Number) value;
		if (checkMaxMin && (num.longValue() < min || num.longValue() > max)) return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.yynn.resm.web.filter.validator.AbstractValidationRule#support(java.lang.Class)
	 */
	@Override
	protected boolean support(Class clazz) {
		return Number.class.isAssignableFrom(clazz);
	}

}
