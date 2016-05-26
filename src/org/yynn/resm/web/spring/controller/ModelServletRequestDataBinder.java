package org.yynn.resm.web.spring.controller;

import org.springframework.validation.AbstractPropertyBindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;

/**
 * <p>
 * Title: ModelServletRequestDataBinder.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created Jun 9, 2007
 */
public class ModelServletRequestDataBinder extends ServletRequestDataBinder {

	private AbstractPropertyBindingResult bindingResult;

	public ModelServletRequestDataBinder(Object target, String objectName, AbstractPropertyBindingResult bindingResult) {
		super(target, objectName);
		this.bindingResult = bindingResult;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.DataBinder#getInternalBindingResult()
	 */
	@Override
	protected AbstractPropertyBindingResult getInternalBindingResult() {
		if (null != bindingResult) return this.bindingResult;
		return super.getInternalBindingResult();
	}
}
