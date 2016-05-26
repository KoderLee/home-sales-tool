package org.yynn.resm.common.util;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.util.Date;

/**
 * <p>
 * Title: CustomerDateEditor.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created Jun 8, 2007
 */
public class CustomerDateEditor extends PropertyEditorSupport {
	private String dateFormat;

	public CustomerDateEditor(String dateFormate) {
		this.dateFormat = dateFormate;
	}

	/**
	 * @return the dateFormat
	 */
	public String getDateFormat() {
		return dateFormat;
	}

	public void setAsText(String text) throws IllegalArgumentException {
		if (StringUtil.isEmpty(text))
			setValue(null);
		else {
			try {
				setValue(CalendarUtil.parse(text, dateFormat));
			}
			catch (ParseException e) {
				throw new IllegalArgumentException("Invalid date value [" + text + "]");
			}
		}
	}

	public String getAsText() {
		if (null == getValue()) return "";
		return CalendarUtil.format((Date) getValue(), dateFormat);
	}
}
