package org.yynn.resm.common;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import org.yynn.resm.common.util.CalendarUtil;
import org.yynn.resm.common.util.StringUtil;

/**
 * <p>
 * Title: QueryDataProperty.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created May 23, 2007
 */
public class QueryDataProperty<T extends Serializable> extends CommonDataProperty<T> {

	private String queryPropertyName;

	private String queryOperator;

	private String calendarForm = "''yyyy-MM-dd''";

	/**
	 * @return the queryPropertyName
	 */
	public String getQueryPropertyName() {
		return queryPropertyName;
	}

	/**
	 * @param queryPropertyName the queryPropertyName to set
	 */
	public void setQueryPropertyName(String queryPropertyName) {
		this.queryPropertyName = queryPropertyName;
	}

	/**
	 * @param type
	 */
	public QueryDataProperty(Class<T> type) {
		super(type);
	}

	public QueryDataProperty(Class<T> type, ICommonDataValueSetProvider<T> provider) {
		super(type, provider);
	}

	public String geratorQueryString(T value) {
		Class<T> type = getType();
		StringBuffer qs = new StringBuffer("");
		if (null != this.queryPropertyName)
			qs.append(this.queryPropertyName);
		else
			qs.append(getName());
		qs.append(queryOperator);
		if (null == value) return null;
		if (type.isAssignableFrom(Integer.class)) {
			qs.append(value.toString());
		}
		else if (type.isAssignableFrom(String.class)) {
			if (StringUtil.isEmpty((String) value)) return null;
			qs.append("'").append(value).append("'");
		}
		else if (type.isAssignableFrom(Long.class)) {
			qs.append(value.toString());
		}
		else if (type.isAssignableFrom(Calendar.class)) {
			qs.append(CalendarUtil.calendarToString((Calendar) value, calendarForm));
		}
		else if (type.isAssignableFrom(Date.class)) {
			qs.append(CalendarUtil.format((Date) value, calendarForm));
		}

		return qs.toString();
	}

	/**
	 * @return the calendarForm
	 */
	public String getCalendarForm() {
		return calendarForm;
	}

	/**
	 * @param calendarForm the calendarForm to set
	 */
	public void setCalendarForm(String calendarForm) {
		this.calendarForm = calendarForm;
	}

	/**
	 * @return the queryOperator
	 */
	public String getQueryOperator() {
		return queryOperator;
	}

	/**
	 * @param queryOperator the queryOperator to set
	 */
	public void setQueryOperator(String queryOperator) {
		this.queryOperator = queryOperator;
	}
}
