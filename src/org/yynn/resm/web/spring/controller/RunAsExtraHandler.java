package org.yynn.resm.web.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.yynn.resm.common.util.StringUtil;

/**
 * <p>
 * Title: RunAsExtraHanler.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created May 30, 2007
 */
public class RunAsExtraHandler implements IExtraHandler {
	protected String projectNoProperty;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.yynn.resm.web.spring.controller.ExtraHandler#handle(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	public void handle(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		String cprojectNo = (String) (new ModelBeanWrapperImpl(command)).getPropertyValue(projectNoProperty);
		if (!StringUtil.isEmpty(cprojectNo)) CurrentProjectNoHolder.setCurrentProjectNo(cprojectNo);
	}

	/**
	 * @return the projectNoProperty
	 */
	public String getProjectNoProperty() {
		return projectNoProperty;
	}

	/**
	 * @param projectNoProperty the projectNoProperty to set
	 */
	public void setProjectNoProperty(String projectNoProperty) {
		this.projectNoProperty = projectNoProperty;
	}
}
