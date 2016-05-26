package org.yynn.resm.web.filter.validator;

import java.util.Locale;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyAccessor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.yynn.resm.common.UiElementProperty;

/**
 * <p>
 * Title: AbstractValidationRule.java
 * </p>
 * <p>
 * Description: 抽象校验规则
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created May 23, 2007
 */
public abstract class AbstractValidationRule implements CommonValidationRule, InitializingBean,
		ApplicationContextAware {
	private UiElementProperty property;

	private String errorCode;

	private ApplicationContext context;

	private Locale locale = Locale.CHINA;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	public void afterPropertiesSet() throws Exception {
		Assert.isTrue(support(property.getCproperty().getType()), "本规则不支持属性类型" + property.getClass()
				+ "不匹配!");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = applicationContext;
	}

	public AbstractValidationRule(UiElementProperty property) {
		this.property = property;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.yynn.resm.web.filter.validator.CommonValidationRule#validate(java.lang.Object,
	 *      org.springframework.validation.Errors)
	 */
	public void validate(PropertyAccessor target, Errors errors) {
		if (!isValidate(target.getPropertyValue(property.getCproperty().getName()))) {
			errors.rejectValue(property.getCproperty().getName(), errorCode, new String[] { context
					.getMessage(property.getDisplayNameCode(), null, locale) }, "");
		}
	}

	protected abstract boolean isValidate(Object value);

	protected abstract boolean support(Class clazz);

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the locale
	 */
	public Locale getLocale() {
		return locale;
	}

	/**
	 * @param locale the locale to set
	 */
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
}
