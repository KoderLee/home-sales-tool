package org.yynn.resm.web.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;

/**
 * <p>
 * Title: ExtraHanler.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created Jun 11, 2007
 */
public interface IExtraHandler {
	public void handle(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors)
			throws Exception;
}
