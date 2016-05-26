package org.yynn.resm.web.spring;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

/**
 * <p>
 * Title: CrudController.java
 * </p>
 * <p>
 * Description: CRUD¿ØÖÆÆ÷
 * </p>
 * @deprecated for nothing,hahahha
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created May 17, 2007
 */
public class CrudController<T, PK extends Serializable> extends AbstractCRUDController<T, PK> {
	public static final int TYPE_CREATE = 0;

	public static final int TYPE_READ = 1;

	public static final int TYPE_UPDATE = 2;

	public static final int TYPE_DELETE = 3;

	public static final int TYPE_QUERY = 4;

	private int type;

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	public CrudController(Class<T> modelType, Class<PK> pkType) {
		super(modelType, pkType);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.mvc.Controller#handleRequest(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		switch (type) {
			case TYPE_CREATE:
				return create(request, response);
			case TYPE_READ:
				return read(request, response);
			case TYPE_UPDATE:
				return update(request, response);
			case TYPE_DELETE:
				return delete(request, response);
			case TYPE_QUERY:
				return query(request, response);
			default:
				break;
		}
		return null;
	}

	private ModelAndView read(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

	private ModelAndView create(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

	private ModelAndView update(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

	private ModelAndView delete(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

	private ModelAndView query(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

}
