package org.yynn.resm.web.spring.controller;

import org.springframework.web.bind.ServletRequestDataBinder;

/**
 * <p>
 * Title: IServletRequestDataBinderFactory.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created Jun 9, 2007
 */
public interface IServletRequestDataBinderFactory {
	/**
	 * @param request
	 * @param command
	 * @return
	 */
	public ServletRequestDataBinder createServletRequestDataBinder(Object target, String objectName);
}
