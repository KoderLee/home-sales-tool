package org.yynn.resm.web.filter.validator;

import org.yynn.resm.common.UiElementProperty;

/**
 * <p>
 * Title: RegexValidationRule.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created Jun 21, 2007
 */
public class RegexValidationRule extends AbstractValidationRule {
	private String regex;

	public RegexValidationRule(UiElementProperty property) {
		super(property);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.yynn.resm.web.filter.validator.AbstractValidationRule#isValidate(java.lang.Object)
	 */
	@Override
	protected boolean isValidate(Object value) {
		if (value instanceof String)
		return ((String)value).matches(regex);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.yynn.resm.web.filter.validator.AbstractValidationRule#support(java.lang.Class)
	 */
	@Override
	protected boolean support(Class clazz) {
		return clazz.equals(String.class);
	}

	/**
	 * @return the regex
	 */
	public String getRegex() {
		return regex;
	}

	/**
	 * @param regex the regex to set
	 */
	public void setRegex(String regex) {
		this.regex = regex;
	}

}
