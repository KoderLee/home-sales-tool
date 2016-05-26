package org.yynn.resm.web.spring.controller;

import org.springframework.validation.AbstractPropertyBindingResult;

/**
 * <p>
 * Title: ModelBindingResultFactoryImpl.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created Jun 9, 2007
 */
public class ModelBindingResultFactoryImpl implements IBindingResultFactory {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.yynn.resm.web.spring.controller.IBindingResultFactory#createBindingResult(java.lang.Object,
	 *      java.lang.String)
	 */
	public AbstractPropertyBindingResult createBindingResult(Object target, String objectName) {
		return new ModelBeanPropertyBindingResult(target, objectName);
	}

}
