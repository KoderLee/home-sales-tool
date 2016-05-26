package org.yynn.resm.web.tag;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.context.MessageSource;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.yynn.resm.common.ICrudPropertiesProvider;
import org.yynn.resm.common.security.CurrentUserInfoProvider;
import org.yynn.resm.web.spring.controller.AbstractCRUDController;

/**
 * <p>
 * Title: BaseTagSupport.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created May 29, 2007
 */
public abstract class BaseTagSupport extends TagSupport {
	// public static final String REQUEST_CONTEXT_PAGE_ATTRIBUTE =
	// "org.springframework.web.servlet.tags.REQUEST_CONTEXT";

	public static final String AUTHCODE_SPERATOR = ",";

	protected String resultKey = AbstractCRUDController.DEFAULT_RESULT_KEY;

	protected String errorKey = AbstractCRUDController.DEFAULT_ERROR_BINDING_KEY;

	private String uiPropertyProviderKey = AbstractCRUDController.PROPERTIES_PROVIDER_KEY;

	private String controllerTypeKey = AbstractCRUDController.CONTROLLER_TYPE_KEY;

	private Locale locale;

	private MessageSource msgSource;

	protected HttpServletRequest servletRequest;

	private String[] deleteAuthCodes = { CurrentUserInfoProvider.ROLE_ADMIN };

	private String[] modifyAuthCodes = { CurrentUserInfoProvider.ROLE_ADMIN };

	private String[] viewAuthCodes = { CurrentUserInfoProvider.ROLE_ADMIN };

	protected ICrudPropertiesProvider uiPropertieProvider;

	protected Class controllerType;

	// private RequestContext requestContext;

	protected boolean canDelete() {
//		for (String dCode : )
		
		return CurrentUserInfoProvider.hasAuth(deleteAuthCodes);
	}

	protected boolean canModify() {
		return CurrentUserInfoProvider.hasAuth(modifyAuthCodes);
	}

	protected boolean canView() {
		return CurrentUserInfoProvider.hasAuth(viewAuthCodes);
	}

	/**
	 * 读取国际化消息资源
	 * 
	 * @param code
	 * @param msgs
	 * @return
	 */
	protected String getMessage(String code) {
		return msgSource.getMessage(code, null, locale);
	}

	/**
	 * 读取国际化消息资源
	 * 
	 * @param code
	 * @param msgs
	 * @return
	 */
	protected String getMessage(String code, Object[] args) {
		return msgSource.getMessage(code, args, locale);
	}

	/**
	 * 
	 */
	private void initMessageResource() {
		this.locale = pageContext.getRequest().getLocale();
		this.msgSource = RequestContextUtils.getWebApplicationContext(pageContext.getRequest());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	@Override
	public int doStartTag() throws JspException {
		initMessageResource();

		initRequest();

		initController();
		return handleStartTag();
	}

	private void initController() {
		this.uiPropertieProvider = (ICrudPropertiesProvider) servletRequest.getAttribute(uiPropertyProviderKey);
		this.controllerType = (Class) servletRequest.getAttribute(controllerTypeKey);
	}

	private void initRequest() {
		servletRequest = (HttpServletRequest) pageContext.getRequest();
	}

	protected abstract int handleStartTag() throws JspException;

	/**
	 * @return the resultKey
	 */
	public String getResultKey() {
		return resultKey;
	}

	/**
	 * @param resultKey the resultKey to set
	 */
	public void setResultKey(String resultKey) {
		this.resultKey = resultKey;
	}

	/**
	 * @return the errorKey
	 */
	public String getErrorKey() {
		return errorKey;
	}

	/**
	 * @param errorKey the errorKey to set
	 */
	public void setErrorKey(String errorKey) {
		this.errorKey = errorKey;
	}

	protected Object getRequestAttribute(String key) {
		return servletRequest.getAttribute(key);
	}

	protected String getParameter(String key) {
		return servletRequest.getParameter(key);
	}

	/**
	 * @return the deleteAuthCodes
	 */
	public String[] getDeleteAuthCodes() {
		return deleteAuthCodes;
	}

	/**
	 * @param deleteAuthCodes the deleteAuthCodes to set
	 */
	public void setDeleteAuthCode(String deleteAuthCode) {
		this.deleteAuthCodes = deleteAuthCode.split(AUTHCODE_SPERATOR);
	}

	/**
	 * @return the modifyAuthCodes
	 */
	public String[] getModifyAuthCodes() {
		return modifyAuthCodes;
	}

	/**
	 * @param modifyAuthCodes the modifyAuthCodes to set
	 */
	public void setModifyAuthCode(String modifyAuthCode) {
		this.modifyAuthCodes = modifyAuthCode.split(AUTHCODE_SPERATOR);
	}

	/**
	 * @return the viewAuthCodes
	 */
	public String[] getViewAuthCodes() {
		return viewAuthCodes;
	}

	/**
	 * @param viewAuthCodes the viewAuthCodes to set
	 */
	public void setViewAuthCode(String viewAuthCode) {
		this.viewAuthCodes = viewAuthCode.split(AUTHCODE_SPERATOR);
	}

	/**
	 * @return the controllerTypeKey
	 */
	public String getControllerTypeKey() {
		return controllerTypeKey;
	}

	/**
	 * @param controllerTypeKey the controllerTypeKey to set
	 */
	public void setControllerTypeKey(String controllerTypeKey) {
		this.controllerTypeKey = controllerTypeKey;
	}

	/**
	 * @return the uiPropertyProviderKey
	 */
	public String getUiPropertyProviderKey() {
		return uiPropertyProviderKey;
	}

	/**
	 * @param uiPropertyProviderKey the uiPropertyProviderKey to set
	 */
	public void setUiPropertyProviderKey(String uiPropertyProviderKey) {
		this.uiPropertyProviderKey = uiPropertyProviderKey;
	}

}
