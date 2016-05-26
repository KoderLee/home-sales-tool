package org.yynn.resm.common;

import java.util.Date;

import org.yynn.resm.common.util.CalendarUtil;

/**
 * <p>
 * Title: CurrentDateDataValue
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created 2007-6-2
 */
public class CurrentDateDataValue implements ICommonDataValue {

	/*
	 * @see org.yynn.resm.common.ICommonDataValue#getDisplayValue()
	 */
	public String getDisplayValue() {
		return CalendarUtil.getCurrentTimeStr(CalendarUtil.DAFAULTDATEFORMAT);
	}

	/*
	 * @see org.yynn.resm.common.ICommonDataValue#getValue()
	 */
	public Date getValue() {
		return CalendarUtil.getCurrentDate();
	}
}
