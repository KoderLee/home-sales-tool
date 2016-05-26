package org.yynn.resm.web.spring.controller;

import org.springframework.validation.AbstractPropertyBindingResult;

/**
 * <p>
 * Title: IBindingResultFactory.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created Jun 9, 2007
 */
public interface IBindingResultFactory {
	/**
	 * @param target
	 * @param objectName
	 * @return
	 */
	public AbstractPropertyBindingResult createBindingResult(Object target, String objectName);
}
