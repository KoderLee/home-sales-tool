package org.yynn.resm.web.tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.jsp.JspException;

import org.yynn.resm.common.CommonDataProperty;
import org.yynn.resm.common.ICommonDataValue;
import org.yynn.resm.common.UiElementProperty;
import org.yynn.resm.common.util.CalendarUtil;
import org.yynn.resm.web.spring.controller.AbstractCRUDController;
import org.yynn.resm.web.spring.controller.ModelBeanWrapperImpl;
import org.yynn.resm.web.spring.controller.QueryController;

/**
 * <p>
 * Title: FormEntryTag.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created May 29, 2007
 */
public class QueryListTag extends BaseTagSupport {

	private String uiPropertySet = QueryController.DISPLAYATTRS;

	private String queryResult = "queryResult";

	private String deleteCode = "common.delete";

	private String deletePath;

	private String viewPath;

	private String calendarForm = "yyyy-MM-dd";

	private String queryFormName = "query";

	private String queryEmptyCode = "common.query.empty";

	/**
	 * @return the queryEmptyCode
	 */
	public String getQueryEmptyCode() {
		return queryEmptyCode;
	}

	/**
	 * @param queryEmptyCode the queryEmptyCode to set
	 */
	public void setQueryEmptyCode(String queryEmptyCode) {
		this.queryEmptyCode = queryEmptyCode;
	}

	/**
	 * @return the queryFormName
	 */
	public String getQueryFormName() {
		return queryFormName;
	}

	/**
	 * @param queryFormName the queryFormName to set
	 */
	public void setQueryFormName(String queryFormName) {
		this.queryFormName = queryFormName;
	}

	/**
	 * @return the queryResult
	 */
	public String getQueryResult() {
		return queryResult;
	}

	/**
	 * @param queryResult the queryResult to set
	 */
	public void setQueryResult(String queryResult) {
		this.queryResult = queryResult;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.yynn.resm.web.tag.BaseTagSupport#handleStartTag()
	 */
	@Override
	protected int handleStartTag() throws JspException {

		Object rsObject = getRequestAttribute(queryResult);
		StringBuffer content = new StringBuffer("");
		if (null != rsObject) {
			if (0 == ((ArrayList) rsObject).size()) {
				generateTrBegin(content);
				content.append("<td class=result_info>");
				content.append(getMessage(queryEmptyCode));
				content.append("</td");
				generateTrEnd(content);
			}
			else {
				ArrayList<UiElementProperty> uiElePropList = (ArrayList<UiElementProperty>) getRequestAttribute(uiPropertySet);
				generateTrBegin(content);
				for (UiElementProperty uip : uiElePropList) {
					content.append("<td align=center class=head_h>");
					content.append(getMessage(uip.getDisplayNameCode())).append("</td>");
				}

				if (canDelete() && null != deletePath) {
					content.append("<td align=center class=head_h>&nbsp;</td>");
				}

				generateTrEnd(content);

				generateLines(content, uiElePropList, (ArrayList) rsObject);
			}
		}
		try {
			pageContext.getOut().println(content.toString());
		}
		catch (IOException ex) {
			throw new JspException("Can't write with JspWriter", ex);
		}
		return SKIP_BODY;
	}

	private void generateLines(StringBuffer content, ArrayList<UiElementProperty> displayNameList, ArrayList rsList)
			throws JspException {
		for (int i = 0, n = rsList.size(); i < n; i++) {
			Object model = rsList.get(i);
			String pkName = null;
			Object pkValue = null;
			UiElementProperty pkUp = null;
			generateTrBegin(content);
			for (UiElementProperty uip : displayNameList) {

				content.append("<td class=text1>");

				CommonDataProperty cp = uip.getCproperty();
				String tname = cp.getName();
				ModelBeanWrapperImpl mbw = new ModelBeanWrapperImpl(model);
				Object value = mbw.getPropertyValue(tname);

				if (null == pkName && cp.isPrimary()) {
					pkName = AbstractCRUDController.PK_PREFIX + cp.getName();
					pkValue = value;
					pkUp = uip;
					generateLinkValue(content, pkName, pkValue, uip);
				}
				else {
					generateValue(content, value, uip);
				}

				content.append("</td>");

			}
			generateAction(content, pkName, pkValue, pkUp);
			generateTrEnd(content);
		}
	}

	private void generateAction(StringBuffer content, String pkName, Object pkValue, UiElementProperty up) {
		if (!canDelete() || null == deletePath) return;
		if (null != pkName && null != pkValue) {
			content.append("<td class=text nowrap align=center  width=8%><a href=# onclick=deleteConfirm('");
			content.append(this.queryFormName).append("','");
			content.append(deletePath);
			if (-1 == "?".indexOf(deletePath))
				content.append("?");
			else {
				content.append("&");
			}
			content.append(pkName).append("=");
			generateValue(content, pkValue, up);
			content.append("')>");
			content.append(getMessage(deleteCode));
			content.append("</a></td>");
		}
	}

	private void generateValue(StringBuffer content, Object value, UiElementProperty up) {
		if (up.getDisplayType() == UiElementProperty.DISPLAYTYPE_SELECT) {

			ArrayList<ICommonDataValue> valueList = up.getCproperty().getValueSet();

			String displayValue = null == value ? "" : value.toString();

			for (ICommonDataValue idv : valueList) {
				if (null != idv.getValue() && idv.getValue().equals(value) && null != idv.getDisplayValue()) displayValue = idv.getDisplayValue();
			}
			content.append(null == displayValue ? "" : displayValue);

		}
		else {
			if (value instanceof Date)
				content.append(CalendarUtil.format((Date) value, calendarForm));
			else
				content.append(null == value ? "" : value);
		}
	}

	private void generateLinkValue(StringBuffer content, String pkName, Object pkValue, UiElementProperty up) {
		if (canView() && null != viewPath) {
			content.append("<a href=# onclick=view('");
			content.append(this.queryFormName).append("','");
			content.append(viewPath);
			if (-1 == "?".indexOf(viewPath))
				content.append("?");
			else {
				content.append("&");
			}
			content.append(pkName).append("=");
			generateValue(content, pkValue, up);
			content.append("')>");
			content.append(pkValue);
			content.append("</a>");
		}
		else {
			content.append(pkValue);
		}

	}

	private void generateTrBegin(StringBuffer content) {
		content.append("<tr>");
	}

	private void generateTrEnd(StringBuffer content) {
		content.append("</tr>");
	}

	/**
	 * @return the uiPropertySet
	 */
	public String getUiPropertySet() {
		return uiPropertySet;
	}

	/**
	 * @param uiPropertySet the uiPropertySet to set
	 */
	public void setUiPropertySet(String uiPropertySet) {
		this.uiPropertySet = uiPropertySet;
	}

	/**
	 * @return the deleteCode
	 */
	public String getDeleteCode() {
		return deleteCode;
	}

	/**
	 * @param deleteCode the deleteCode to set
	 */
	public void setDeleteCode(String actionCode) {
		this.deleteCode = actionCode;
	}

	/**
	 * @return the deletePath
	 */
	public String getDeletePath() {
		return deletePath;
	}

	/**
	 * @param deletePath the deletePath to set
	 */
	public void setDeletePath(String actionURI) {
		this.deletePath = actionURI;
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
	 * @return the viewPath
	 */
	public String getViewPath() {
		return viewPath;
	}

	/**
	 * @param viewPath the viewPath to set
	 */
	public void setViewPath(String viewPath) {
		this.viewPath = viewPath;
	}
}
