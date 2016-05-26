package org.yynn.resm.web.spring.controller;

import java.io.Serializable;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.yynn.resm.service.CrudService;

/**
 * <p>
 * Title: AddController.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created May 30, 2007
 */
public class ViewController<T, PK extends Serializable> extends BasePkCRUDController<T, PK> {

	public ViewController(CrudService<T, PK> service) {
		super(service);
	}

	public static final String DEFAULT_MODELVALUE_NAME = "modelViewValue";

	public static final String DEFAULT_QUERYOBJECT_NAME = "queryObject";

	public static final String DEFAULT_QUERYPROPERTYSET_KEY = "queryPropertySetKey";

	private String modelName = DEFAULT_MODELVALUE_NAME;

	private String queryObjectName = DEFAULT_QUERYOBJECT_NAME;

	private String queryPropertySetKey = DEFAULT_QUERYPROPERTYSET_KEY;

	/*
	 * @see org.yynn.resm.web.spring.controller.AbstractCRUDController#doSubmit(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	@Override
	protected void doSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		String temp = request.getParameter(pkName);
		Object modelValue = null;
		if (service.getPKType().isAssignableFrom(Integer.class)) {
			modelValue = service.read((PK) new Integer(temp));
		}
		else if (service.getPKType().isAssignableFrom(String.class)) {
			modelValue = service.read((PK) temp);
		}
		else if (service.getPKType().isAssignableFrom(Long.class)) {
			modelValue = service.read((PK) new Long(temp));
		}
		else {
			throw new UnsupportedOperationException("PkType Must Be Long or String or Integer,sorry!");
		}

		ArrayList<IExtraHandler> postExtraHandlers = getPostExtraHandlers();
		if (null != postExtraHandlers) {
			for (IExtraHandler handler : postExtraHandlers) {
				if (handler instanceof RunAsExtraHandler) {
					handler.handle(request, response, modelValue, errors);
				}
			}
		}
		request.setAttribute(modelName, modelValue);

		request.setAttribute(queryObjectName, command);
		request.setAttribute(DEFAULT_QUERYPROPERTYSET_KEY, crudPropsProvider.getQueryFormProperties());
	}

	/**
	 * @return the modelName
	 */
	public String getModelName() {
		return modelName;
	}

	/**
	 * @param modelName the modelName to set
	 */
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	/**
	 * @return the queryObjectName
	 */
	public String getQueryObjectName() {
		return queryObjectName;
	}

	/**
	 * @param queryObjectName the queryObjectName to set
	 */
	public void setQueryObjectName(String queryObjectName) {
		this.queryObjectName = queryObjectName;
	}

	/**
	 * @return the queryPropertySetKey
	 */
	public String getQueryPropertySetKey() {
		return queryPropertySetKey;
	}

	/**
	 * @param queryPropertySetKey the queryPropertySetKey to set
	 */
	public void setQueryPropertySetKey(String queryPropertySetKey) {
		this.queryPropertySetKey = queryPropertySetKey;
	}
}
