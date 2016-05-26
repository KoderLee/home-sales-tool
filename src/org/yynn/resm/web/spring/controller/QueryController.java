package org.yynn.resm.web.spring.controller;

import java.io.Serializable;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.yynn.resm.common.QueryDataEntry;
import org.yynn.resm.common.QueryDataProperty;
import org.yynn.resm.service.CrudService;

/**
 * <p>
 * Title: QueryController.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created May 23, 2007
 */
public class QueryController<T, PK extends Serializable> extends AbstractCRUDController<T, PK> {

	public QueryController(CrudService<T, PK> service) {
		super(service);
	}

	public static final String DISPLAYATTRS = "displayAttrs";

	private String queryResultName = "queryResult";

	private String modelName = ViewController.DEFAULT_MODELVALUE_NAME;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	@Override
	protected void doSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		ArrayList<QueryDataEntry> ql = new ArrayList<QueryDataEntry>();
		ModelBeanWrapperImpl cmdw = new ModelBeanWrapperImpl(command);
		for (QueryDataProperty property : crudPropsProvider.getQueryDataProperties()) {
			ql.add(new QueryDataEntry(property, (Serializable) cmdw.getPropertyValue(property.getName())));
		}
		request.setAttribute(queryResultName, service.query(ql));
		request.setAttribute(DISPLAYATTRS, crudPropsProvider.getQueryListProperties());
		request.setAttribute(modelName, command);
	}

	/**
	 * @return the queryResultName
	 */
	public String getQueryResultName() {
		return queryResultName;
	}

	/**
	 * @param queryResultName the queryResultName to set
	 */
	public void setQueryResultName(String queryResultName) {
		this.queryResultName = queryResultName;
	}

	protected void onSuccess(HttpServletRequest request) {
		// nothing to do...
	}

}
