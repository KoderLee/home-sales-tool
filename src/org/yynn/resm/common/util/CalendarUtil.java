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
 * Description: ����/ʱ���ʽ����
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created May 23, 2007
 */
public class CalendarUtil {

	public static final String DAFAULTDATEFORMAT = "yyyy-MM-dd";

	/**
	 * ����ָ����ʱ����ʾ��ʽ����һ��Date��ʾ��ָ����ʽ��ʱ���ַ�����
	 * 
	 * @param date ��ʾʱ���Date����
	 * @param format ��ʾʱ�����ʾ��ʽ�ַ�������"yyyyMMdd"��"yyyyMMdd HH:mm"
	 * 
	 * @return ָ����ʽ��ʱ���ַ���
	 */
	public static String format(Date date, String format) {
		SimpleDateFormat sf = new SimpleDateFormat(format);
		return sf.format(date);
	}

	/**
	 * ��һ����ʽ���ַ���ʱ��ֵת����Date����
	 * 
	 * @param strTime ��ʾʱ��ĸ�ʽ���ַ���ֵ
	 * @param format ��ʾʱ�����ʾ��ʽ
	 * 
	 * @return ָ��ʱ���Date����
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
	 * ��һ��long timeת����Calendar����
	 * 
	 * @param time ��ʾʱ���longֵ
	 * 
	 * @return ָ��ʱ���Calendar����
	 */
	public static Calendar getCalendar(long time) {
		Calendar cl = Calendar.getInstance();
		cl.setTimeInMillis(time);
		return cl;
	}

	/**
	 * ��һ��Stringת����Calendar����
	 * 
	 * @param lTime ��ʾʱ���longֵ���ַ���
	 * 
	 * @return ָ��ʱ���Calendar����
	 */
	public static Calendar getCalendar(String lTime) {
		Calendar cl = Calendar.getInstance();
		cl.setTimeInMillis(Long.parseLong(lTime));

		return cl;
	}

	/**
	 * ��һ��ָ����ʱ����ʾ��ʽ��Stringת����Calendar����.by.ln
	 * 
	 * @param strTime ��ʾʱ��ĸ�ʽ�ַ���
	 * @param format ��ʾʱ�����ʾ��ʽ���ַ���
	 * 
	 * @return ָ��ʱ���Calendar����
	 * @throws ParseException
	 */
	public static Calendar getCalendar(String strTime, String format) throws ParseException {
		Calendar cl = Calendar.getInstance();
		cl.setTime(parse(strTime, format));
		return cl;
	}

	/**
	 * ��һ��Calendar����ת����ָ��ʱ����ʾ��ʽ���ַ���
	 * 
	 * @param cal �����ַ���
	 * @param format ʱ����ʾ��ʽ����"yyyyMMdd"��"yyyyMMdd HH:mm"���򷵻ر�׼ʱ���ʽ���ַ��� "long"���򷵻�long��ֵ���ַ���
	 * @return ָ��ʱ����ʾ��ʽ���ַ���
	 */
	public static String calendarToString(Calendar cal, String format) {
		if (format.equals("long")) {
			return String.valueOf(cal.getTime().getTime());
		}

		return format(cal.getTime(), format);
	}

	/**
	 * ��һ����ʾʱ��longֵ��Stringת����һ��ʱ����ʾ��ʽ��String
	 * 
	 * @param lTime ��ʾʱ���longֵ���ַ���
	 * 
	 * @param format ʱ����ʾ��ʽ����"yyyyMMdd"��"yyyyMMdd HH:mm"���򷵻ر�׼ʱ���ʽ���ַ��� "long"���򷵻�long��ֵ���ַ���
	 * @return һ��ʱ����ʾ��ʽ��String
	 */
	public static String longDateStrToFormatDateStr(String lTime, String format) {
		Calendar cal = getCalendar(lTime);
		return calendarToString(cal, format);
	}

	/**
	 * ��һ����ʾʱ��Calendarֵ�İ�Сʱ�������γ��µ�ʱ��
	 * 
	 * @param Calendar ��ʾ��������ʱ�䴮
	 * @param hour ������Сʱ��������Ϊ������������Ϊ��ǰ����
	 * 
	 * @return ���������ʱ��Calendar
	 */
	public static Calendar getAdjustCalendar(Calendar cal, int hour) {
		return getAdjustCalendar(cal, 0, 0, 0, hour, 0, 0);
	}

	/**
	 * ��һ����ʾʱ��Calendarֵ�İ����ӣ��γ��µ�ʱ��
	 * 
	 * @param Calendar ��ʾ��������ʱ�䴮
	 * @param field ������־ֵ: "YEAR",�������ꣻ "MONTH",�������£� "DAY"���������죻 "HOUR",������Сʱ�� "MIN",���������ӣ� "SECOND",�������롣
	 * 
	 * @param mount �����Ĵ�С������Ϊ������������Ϊ��ǰ����
	 * 
	 * @return ���������ʱ��Calendar
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
	 * ��һ����ʾʱ��Calendarֵ�İ��꣬�£��գ�Сʱ�����ӡ���������γ��µ�ʱ��
	 * 
	 * @param Calendar ��ʾ��������ʱ�䴮
	 * @param hour ������Сʱ��������Ϊ������������Ϊ��ǰ����
	 * @param min �����ķ�����������Ϊ������������Ϊ��ǰ����
	 * @param sec ����������������Ϊ������������Ϊ��ǰ����
	 * @return ���������ʱ��Calendar
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
	 * ����ǰʱ����ʱ����ʾ��ʽ���ַ�������
	 * 
	 * @param format ʱ����ʾ��ʽ����"yyyyMMdd"��"yyyyMMdd HH:mm"���򷵻ر�׼ʱ���ʽ���ַ��� "long"���򷵻�long��ֵ���ַ���
	 * 
	 * @return ָ����ʱ����ʾ��ʽ�ĵ�ǰʱ���ַ���
	 */
	public static String getCurrentTimeStr(String format) {
		Calendar cl = Calendar.getInstance();
		return calendarToString(cl, format);
	}

	/**
	 * ��ȡ��ǰ����
	 * 
	 * @return
	 */
	public static Date getCurrentDate() {
		Calendar cl = Calendar.getInstance();
		return cl.getTime();
	}

}