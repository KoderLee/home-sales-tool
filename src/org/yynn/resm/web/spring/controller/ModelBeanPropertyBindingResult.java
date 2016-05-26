package org.yynn.resm.web.spring.controller;

import org.springframework.beans.BeanWrapper;
import org.springframework.validation.BeanPropertyBindingResult;

/**
 * <p>
 * Title: ModelBeanPropertyBindingResult.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created Jun 9, 2007
 */
public class ModelBeanPropertyBindingResult extends BeanPropertyBindingResult {
	public ModelBeanPropertyBindingResult(Object target, String objectName) {
		super(target, objectName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.BeanPropertyBindingResult#createBeanWrapper()
	 */
	@Override
	protected BeanWrapper createBeanWrapper() {
		ModelBeanWrapperImpl mbw = new ModelBeanWrapperImpl(getTarget());
		mbw.setAutoCreatePropValue(true);
		return mbw;
	}

}
