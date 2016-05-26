package org.yynn.resm.web.spring.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.yynn.resm.common.ICrudPropertiesProvider;
import org.yynn.resm.service.CrudService;

/**
 * <p>
 * Title: AbstractCRUDController.java
 * </p>
 * <p>
 * Description: CRUDContoller的抽象实现,使用泛型可以获得更好的类型安全性,暂放.
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created May 17, 2007
 */
public abstract class AbstractCRUDController<T, PK extends Serializable> extends SimpleFormController implements
		InitializingBean {

	public static final String PK_PREFIX = "pk_";

	public static final String PROPERTIES_PROVIDER_KEY = "PROPERTIES_PROVIDER_KEY";

	public static final String DEFAULT_ERROR_BINDING_KEY = "ERROR_BINDING_KEY";

	public final static String DEFAULT_RESULT_KEY = "PROCESS_RESULT_KEY";

	public final static String CONTROLLER_TYPE_KEY = "CONTROLLER_TYPE_KEY";

	private String resultCode = DEFAULT_RESULT_KEY;

	private String errorBindingKey = DEFAULT_ERROR_BINDING_KEY;

	private String successCode = "common.success";

	private String failCode = "common.failure";

	private Map<String, Object> attributes;

	protected ICrudPropertiesProvider crudPropsProvider;

	private IServletRequestDataBinderFactory dataBinderFactory;

	protected CrudService<T, PK> service;

	private ArrayList<IExtraHandler> preExtraHandlers;

	private ArrayList<IExtraHandler> postExtraHandlers;

	/**
	 * @return the dataBinderFactory
	 */
	public IServletRequestDataBinderFactory getDataBinderFactory() {
		return dataBinderFactory;
	}

	/**
	 * @param dataBinderFactory the dataBinderFactory to set
	 */
	public void setDataBinderFactory(IServletRequestDataBinderFactory dataBinderFactory) {
		this.dataBinderFactory = dataBinderFactory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.mvc.BaseCommandController#createBinder(javax.servlet.http.HttpServletRequest,
	 *      java.lang.Object)
	 */
	@Override
	protected ServletRequestDataBinder createBinder(HttpServletRequest request, Object command) throws Exception {
		ServletRequestDataBinder binder = dataBinderFactory.createServletRequestDataBinder(command, getCommandName());
		prepareBinder(binder);
		initBinder(request, binder);
		return binder;
	}

	/**
	 * @param crudPropsProvider the crudPropsProvider to set
	 */
	public void setCrudPropsProvider(ICrudPropertiesProvider crudPropsProvider) {
		this.crudPropsProvider = crudPropsProvider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#handleRequestInternal(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute(PROPERTIES_PROVIDER_KEY, crudPropsProvider);
		request.setAttribute(CONTROLLER_TYPE_KEY, this.getClass());
		if (null != attributes) {
			for (String key : attributes.keySet()) {
				request.setAttribute(key, attributes.get(key));
			}
		}
		return super.handleRequestInternal(request, response);
	}

	/*
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(service, "service can't be null!");
		setCommandClass(service.getModelType());
	}

	public AbstractCRUDController(CrudService<T, PK> service) {
		this.service = service;
	}

	/**
	 * @return the service
	 */
	public CrudService getService() {
		return service;
	}

	/**
	 * @return the attributes
	 */
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	/**
	 * @param attributes the attributes to set
	 */
	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.mvc.BaseCommandController#onBind(javax.servlet.http.HttpServletRequest,
	 *      java.lang.Object, org.springframework.validation.BindException)
	 */
	@Override
	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors) throws Exception {
		request.setAttribute(errorBindingKey, errors);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#processFormSubmission(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	@Override
	protected ModelAndView processFormSubmission(HttpServletRequest request, HttpServletResponse response,
			Object command, BindException errors) throws Exception {
		if (errors.hasErrors()) {
			onFail(request);
			doPostExtraHandle(request, response, command, errors);
		}
		return super.processFormSubmission(request, response, command, errors);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,
			BindException errors) throws Exception {
		doPreExtraHandle(request, response, command, errors);
		doSubmit(request, response, command, errors);
		doPostExtraHandle(request, response, command, errors);
		if (!errors.hasErrors())
			onSuccess(request);
		else
			onFail(request);
		return super.onSubmit(request, response, command, errors);
	}

	protected void doSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors)
			throws Exception {

	}

	private void doPreExtraHandle(HttpServletRequest request, HttpServletResponse response, Object command,
			BindException errors) throws Exception {
		if (null != preExtraHandlers) {
			for (IExtraHandler ehandler : preExtraHandlers) {
				ehandler.handle(request, response, command, errors);
			}
		}

	}

	private void doPostExtraHandle(HttpServletRequest request, HttpServletResponse response, Object command,
			BindException errors) throws Exception {
		if (null != postExtraHandlers) {
			for (IExtraHandler ehandler : postExtraHandlers) {
				ehandler.handle(request, response, command, errors);
			}
		}
	}

	protected void onFail(HttpServletRequest request) {
		request.setAttribute(resultCode, failCode);
	}

	protected void onSuccess(HttpServletRequest request) {
		request.setAttribute(resultCode, successCode);
	}

	/**
	 * @return the errorBindingKey
	 */
	public String getErrorBindingKey() {
		return errorBindingKey;
	}

	/**
	 * @param errorBindingKey the errorBindingKey to set
	 */
	public void setErrorBindingKey(String errorBindingKey) {
		this.errorBindingKey = errorBindingKey;
	}

	/**
	 * @return the resultCode
	 */
	public String getResultCode() {
		return resultCode;
	}

	/**
	 * @param resultCode the resultCode to set
	 */
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	/**
	 * @return the failCode
	 */
	public String getFailCode() {
		return failCode;
	}

	/**
	 * @param failCode the failCode to set
	 */
	public void setFailCode(String failCode) {
		this.failCode = failCode;
	}

	/**
	 * @return the successCode
	 */
	public String getSuccessCode() {
		return successCode;
	}

	/**
	 * @param successCode the successCode to set
	 */
	public void setSuccessCode(String successCode) {
		this.successCode = successCode;
	}

	/**
	 * @return the postExtraHandlers
	 */
	public ArrayList<IExtraHandler> getPostExtraHandlers() {
		return postExtraHandlers;
	}

	/**
	 * @param postExtraHandlers the postExtraHandlers to set
	 */
	public void setPostExtraHandlers(ArrayList postExtraHandlers) {
		this.postExtraHandlers = postExtraHandlers;
	}

	/**
	 * @return the preExtraHandlers
	 */
	public ArrayList<IExtraHandler> getPreExtraHandlers() {
		return preExtraHandlers;
	}

	/**
	 * @param preExtraHandlers the preExtraHandlers to set
	 */
	public void setPreExtraHandlers(ArrayList preExtraHandlers) {
		this.preExtraHandlers = preExtraHandlers;
	}
}
