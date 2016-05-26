package org.yynn.resm.web.spring.controller;

import org.springframework.web.bind.ServletRequestDataBinder;

/**
 * <p>
 * Title: ModelServletRequestDataBinderFactoryImpl.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created Jun 9, 2007
 */
public class ModelServletRequestDataBinderFactoryImpl implements IServletRequestDataBinderFactory {
	private IBindingResultFactory bindingResultFactory;

	/**
	 * @return the bindingResultFactory
	 */
	public IBindingResultFactory getBindingResultFactory() {
		return bindingResultFactory;
	}

	/**
	 * @param bindingResultFactory the bindingResultFactory to set
	 */
	public void setBindingResultFactory(IBindingResultFactory bindingResultFactory) {
		this.bindingResultFactory = bindingResultFactory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.yynn.resm.web.spring.controller.IServletRequestDataBinderFactory#createServletRequestDataBinder(javax.servlet.http.HttpServletRequest,
	 *      java.lang.Object)
	 */
	public ServletRequestDataBinder createServletRequestDataBinder(Object target, String objectName) {
		return new ModelServletRequestDataBinder(target, objectName, bindingResultFactory.createBindingResult(target,
				objectName));
	}

}
