package org.yynn.resm.web.spring.controller;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.yynn.resm.service.CrudService;

/**
 * <p>
 * Title: ExtraEditController
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created 2007-6-10
 */
public class PaybackEditController<T, PK extends Serializable> extends EditController<T, PK> {
	private String stateKey = "saleinfo.state";

	public static final String SALE_STATE_PAYBACK = "0";

	/**
	 * @return the stateKey
	 */
	public String getStateKey() {
		return stateKey;
	}

	/**
	 * @param stateKey the stateKey to set
	 */
	public void setStateKey(String stateKey) {
		this.stateKey = stateKey;
	}

	public PaybackEditController(CrudService<T, PK> service) {
		super(service);
	}

	/*
	 * @see org.yynn.resm.web.spring.controller.EditController#doSubmit(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	@Override
	protected void doSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		if (isSaveorView(request)) {
			ModelBeanWrapperImpl bi = new ModelBeanWrapperImpl(command);
			Object temp = bi.getPropertyValue(stateKey);
			bi.setPropertyValue(stateKey, SALE_STATE_PAYBACK);

			super.doSubmit(request, response, command, errors);

			if (errors.hasErrors()) {
				bi.setPropertyValue(stateKey, temp);
			}
		}
		else
			super.doSubmit(request, response, command, errors);
	}

}
