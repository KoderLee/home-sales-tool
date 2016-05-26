package org.yynn.resm.web.spring;

import java.io.Serializable;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.yynn.resm.common.CommonProperty;
import org.yynn.resm.service.CrudService;

/**
 * <p>
 * Title: AbstractCRUDController.java
 * </p>
 * <p>
 * Description: CRUDContoller的抽象实现
 * </p>
 * @deprecated for nothing,hahhahha
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created May 17, 2007
 */
public abstract class AbstractCRUDController<T, PK extends Serializable> implements Controller {
	protected Class<T> modelType;

	protected Class<PK> pkType;

	protected String viewName;

	protected ArrayList<CommonProperty> properties;

	protected CrudService<T, PK> service;

	public AbstractCRUDController(Class<T> modelType, Class<PK> pkType) {
		this.modelType = modelType;
		this.pkType = pkType;
	}

	/**
	 * @return the properties
	 */
	public ArrayList<CommonProperty> getProperties() {
		return properties;
	}

	/**
	 * @param properties the properties to set
	 */
	public void setProperties(ArrayList<CommonProperty> properties) {
		this.properties = properties;
	}

	/**
	 * @return the service
	 */
	public CrudService<T, PK> getService() {
		return service;
	}

	/**
	 * @param service the service to set
	 */
	public void setService(CrudService<T, PK> service) {
		this.service = service;
	}

	/**
	 * @return the viewName
	 */
	public String getViewName() {
		return viewName;
	}

	/**
	 * @param viewName the viewName to set
	 */
	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	/**
	 * @return the modelType
	 */
	public Class<T> getModelType() {
		return modelType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.mvc.Controller#handleRequest(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public abstract ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1)
			throws Exception;

}
