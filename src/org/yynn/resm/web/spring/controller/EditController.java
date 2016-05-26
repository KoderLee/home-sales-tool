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
public class EditController<T, PK extends Serializable> extends BasePkCRUDController<T, PK> {
	private String modelName = ViewController.DEFAULT_MODELVALUE_NAME;

	public EditController(CrudService<T, PK> service) {
		super(service);
	}

	/*
	 * @see org.springframework.web.servlet.mvc.BaseCommandController#suppressValidation(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected boolean suppressValidation(HttpServletRequest request) {
		return !isSaveorView(request);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.mvc.BaseCommandController#suppressBinding(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected boolean suppressBinding(HttpServletRequest request) {
		return !isSaveorView(request);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	@Override
	protected void doSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		if (!isSaveorView(request)) {
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
		}
		else {
			service.update((T) command);
			request.setAttribute(modelName, command);
		}
	}

	/*
	 * @see org.yynn.resm.web.spring.controller.AbstractCRUDController#onSuccess(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected void onSuccess(HttpServletRequest request) {
		if (isSaveorView(request)) super.onSuccess(request);

	}

	/**
	 * @param request
	 * @return true if save, or false;
	 */
	protected boolean isSaveorView(HttpServletRequest request) {
		return null == request.getParameter(pkName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.yynn.resm.web.spring.controller.AbstractCRUDController#onBindAndValidate(javax.servlet.http.HttpServletRequest,
	 *      java.lang.Object, org.springframework.validation.BindException)
	 */
	@Override
	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors) throws Exception {
		request.setAttribute(modelName, command);
		super.onBindAndValidate(request, command, errors);
	}

}
