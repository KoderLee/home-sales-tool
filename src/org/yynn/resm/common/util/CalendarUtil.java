package org.yynn.resm.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <p>
 * Title: CalendarUtil.java
 * </p>
 * <p>
 * Description: 日期/时间格式处理
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created May 23, 2007
 */
public class CalendarUtil {

	public static final String DAFAULTDATEFORMAT = "yyyy-MM-dd";

	/**
	 * 根据指定的时间显示格式，将一个Date显示成指定格式的时间字符串。
	 * 
	 * @param date 表示时间的Date对象
	 * @param format 表示时间的显示格式字符串：如"yyyyMMdd"或"yyyyMMdd HH:mm"
	 * 
	 * @return 指定格式的时间字符串
	 */
	public static String format(Date date, String format) {
		SimpleDateFormat sf = new SimpleDateFormat(format);
		return sf.format(date);
	}

	/**
	 * 将一个格式化字符串时间值转换成Date对象
	 * 
	 * @param strTime 表示时间的格式化字符串值
	 * @param format 表示时间的显示格式
	 * 
	 * @return 指定时间的Date对象
	 * @throws ParseException
	 */
	public static Date parse(String strTime, String format) throws ParseException {
		if (null != format && format.equals("long")) {
			return new Date(Long.parseLong(strTime));
		}
		SimpleDateFormat sf = new SimpleDateFormat(format);
		return sf.parse(strTime);
	}

	/**
	 * 将一个long time转换成Calendar对象
	 * 
	 * @param time 表示时间的long值
	 * 
	 * @return 指定时间的Calendar对象
	 */
	public static Calendar getCalendar(long time) {
		Calendar cl = Calendar.getInstance();
		cl.setTimeInMillis(time);
		return cl;
	}

	/**
	 * 将一个String转换成Calendar对象
	 * 
	 * @param lTime 表示时间的long值的字符串
	 * 
	 * @return 指定时间的Calendar对象
	 */
	public static Calendar getCalendar(String lTime) {
		Calendar cl = Calendar.getInstance();
		cl.setTimeInMillis(Long.parseLong(lTime));

		return cl;
	}

	/**
	 * 将一个指定了时间显示格式的String转换成Calendar对象.by.ln
	 * 
	 * @param strTime 表示时间的格式字符串
	 * @param format 表示时间的显示格式的字符串
	 * 
	 * @return 指定时间的Calendar对象
	 * @throws ParseException
	 */
	public static Calendar getCalendar(String strTime, String format) throws ParseException {
		Calendar cl = Calendar.getInstance();
		cl.setTime(parse(strTime, format));
		return cl;
	}

	/**
	 * 将一个Calendar对象转换成指定时间显示格式的字符串
	 * 
	 * @param cal 输入字符串
	 * @param format 时间显示格式：如"yyyyMMdd"或"yyyyMMdd HH:mm"，则返回标准时间格式的字符串 "long"，则返回long型值的字符串
	 * @return 指定时间显示格式的字符串
	 */
	public static String calendarToString(Calendar cal, String format) {
		if (format.equals("long")) {
			return String.valueOf(cal.getTime().getTime());
		}

		return format(cal.getTime(), format);
	}

	/**
	 * 将一个表示时间long值的String转换成一定时间显示格式的String
	 * 
	 * @param lTime 表示时间的long值的字符串
	 * 
	 * @param format 时间显示格式：如"yyyyMMdd"或"yyyyMMdd HH:mm"，则返回标准时间格式的字符串 "long"，则返回long型值的字符串
	 * @return 一定时间显示格式的String
	 */
	public static String longDateStrToFormatDateStr(String lTime, String format) {
		Calendar cal = getCalendar(lTime);
		return calendarToString(cal, format);
	}

	/**
	 * 将一个表示时间Calendar值的按小时调整，形成新的时间
	 * 
	 * @param Calendar 表示待调整的时间串
	 * @param hour 调整的小时数，正数为向后调整，负数为向前调整
	 * 
	 * @return 调整后的新时间Calendar
	 */
	public static Calendar getAdjustCalendar(Calendar cal, int hour) {
		return getAdjustCalendar(cal, 0, 0, 0, hour, 0, 0);
	}

	/**
	 * 将一个表示时间Calendar值的按分钟，形成新的时间
	 * 
	 * @param Calendar 表示待调整的时间串
	 * @param field 调整标志值: "YEAR",仅调整年； "MONTH",仅调整月； "DAY"，仅调整天； "HOUR",仅调整小时； "MIN",仅调整分钟； "SECOND",仅调整秒。
	 * 
	 * @param mount 调整的大小，正数为向后调整，负数为向前调整
	 * 
	 * @return 调整后的新时间Calendar
	 */
	public static Calendar getAdjustCalendar(Calendar cal, int field, int mount) {
		Calendar t = null;
		switch (field) {
			case Calendar.YEAR:
				t = getAdjustCalendar(cal, mount, 0, 0, 0, 0, 0);
				break;
			case Calendar.MONTH:
				t = getAdjustCalendar(cal, 0, mount, 0, 0, 0, 0);
				break;
			case Calendar.DATE:
				t = getAdjustCalendar(cal, 0, 0, mount, 0, 0, 0);
				break;
			case Calendar.HOUR:
				t = getAdjustCalendar(cal, 0, 0, 0, mount, 0, 0);
				break;
			case Calendar.MINUTE:
				t = getAdjustCalendar(cal, 0, 0, 0, 0, mount, 0);
				break;
			case Calendar.SECOND:
				t = getAdjustCalendar(cal, 0, 0, 0, 0, 0, mount);
				break;
		}

		return t;
	}

	/**
	 * 将一个表示时间Calendar值的按年，月，日，小时、分钟、秒调整，形成新的时间
	 * 
	 * @param Calendar 表示待调整的时间串
	 * @param hour 调整的小时数，正数为向后调整，负数为向前调整
	 * @param min 调整的分钟数，正数为向后调整，负数为向前调整
	 * @param sec 调整的秒数，正数为向后调整，负数为向前调整
	 * @return 调整后的新时间Calendar
	 */
	public static Calendar getAdjustCalendar(Calendar cal, int year, int month, int day, int hour, int min, int sec) {
		// GregorianCalendar.HOUR
		Calendar adjust = (Calendar) cal.clone();
		adjust.add(Calendar.YEAR, year);
		adjust.add(Calendar.MONTH, month);
		adjust.add(Calendar.DATE, day);
		adjust.add(Calendar.HOUR, hour);
		adjust.add(Calendar.MINUTE, min);
		adjust.add(Calendar.SECOND, sec);

		return adjust;
	}

	/**
	 * 将当前时间以时间显示格式的字符串返回
	 * 
	 * @param format 时间显示格式：如"yyyyMMdd"或"yyyyMMdd HH:mm"，则返回标准时间格式的字符串 "long"，则返回long型值的字符串
	 * 
	 * @return 指定的时间显示格式的当前时间字符串
	 */
	public static String getCurrentTimeStr(String format) {
		Calendar cl = Calendar.getInstance();
		return calendarToString(cl, format);
	}

	/**
	 * 获取当前日期
	 * 
	 * @return
	 */
	public static Date getCurrentDate() {
		Calendar cl = Calendar.getInstance();
		return cl.getTime();
	}

}