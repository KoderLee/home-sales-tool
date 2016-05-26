package org.yynn.resm.common.util;

import java.io.UnsupportedEncodingException;
import java.util.StringTokenizer;

/**
 * <p>
 * Title: StringUtil.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created May 29, 2007
 */
public class StringUtil {

	/** 在字符串左侧补齐空格 */
	public final static int LEFT_SPACE = 0;

	/** 在字符串右侧补齐空格 */
	public final static int RIGHT_SPACE = 1;

	/** 如果字符串实际长度超出指定长度，将左侧截断 */
	public final static int TRUNC_LEFT = 0;

	/** 如果字符串实际长度超出指定长度，将右侧截断 */
	public final static int TRUNC_RIGHT = 1;

	/**
	 * 该方法去掉字符串的左边空格
	 * 
	 * @param String 需要去掉左边空格的字符串
	 * @return String 已经去掉左边空格的字符串
	 */
	public static String leftTrim(String str) {
		if (str == null) return "";

		byte[] bytes = str.getBytes();
		int index = 0;
		byte ch;
		do {
			ch = bytes[index];
			if (ch != ' ') break;
			index++;
		}
		while (true);
		return str.substring(index);
	}

	/**
	 * 改方法去掉字符串的右边空格
	 * 
	 * @param String 需要去掉右边空格的字符串
	 * @return String 已经去掉右边空格的字符串
	 */
	public static String rightTrim(String str) {
		if (str == null) return "";

		byte[] bytes = str.getBytes();
		int index = StringUtil.length(str);

		if (index == 0) return "";

		index = index - 1;
		byte ch;
		do {
			ch = bytes[index];
			if (ch != ' ') break;
			index--;
		}
		while (index >= 0);

		return new String(str.getBytes(), 0, index + 1);
	}

	/**
	 * 将字符串中所有的空格删除，包括左边、右边、中间。
	 * 
	 * @param str
	 */
	public static String allTrim(String str) {
		if (str == null) return "";
		String tmp = str.trim();
		if (tmp.equals("")) return "";
		int idx = 0;
		int len = 0;
		len = tmp.length();
		idx = tmp.indexOf(" ");
		while (idx > 0) {
			tmp = tmp.substring(0, idx) + tmp.substring(idx + 1, len);
			idx = tmp.indexOf(" ");
			len = tmp.length();
		}
		return tmp;
	}

	/**
	 * 该方法首先判断传入的字符串是否是null,如果是null则返回"",否则对传入的字符串执行trim操作后返回trim的结果
	 * 
	 * @param String 需要进行处理的字符串
	 * @return String 处理完成的结果字符串
	 */
	public static String trim(String orin) {
		if (orin == null) {
			orin = "";
		}
		return orin.trim();
	}

	/**
	 * 该方法将传入的字符串扩充为指定长度的字符串,长度不足的话,补充空格 如果字符串长度大于指定的长度,就截断超出的那部分字符.
	 * 
	 * @param str 需要处理的字符串
	 * @param len 指定的字符串长度
	 * @param direct StringUtil.LEFT_SPACE 从左侧补充空格 StringUtil.RIGHT_SPACE 从右侧补充空格
	 * @param truncWay StringUtil.TRUNC_LEFT 从左侧截断空格 StringUtil.TRUNC_RIGHT 从右侧截断空格
	 * @return 返回指定格式的字符串
	 */
	public static String alignStr(String str, int len, int direct, int truncWay) {

		return alignStr(str, len, direct, truncWay, ' ');

	}

	/**
	 * 该方法将传入的字符串扩充为指定长度的字符串,长度不足的话,以 fixStr 补充 如果字符串长度大于指定的长度,就截断超出的那部分字符.
	 * 
	 * @param str 需要处理的字符串
	 * @param len 指定的字符串长度
	 * @param direct StringUtil.LEFT_SPACE 从左侧补充空格 StringUtil.RIGHT_SPACE 从右侧补充空格
	 * @param truncWay StringUtil.TRUNC_LEFT 从左侧截断空格 StringUtil.TRUNC_RIGHT 从右侧截断空格
	 * @param fixStr 用来填充的的字符
	 * @return 返回指定格式的字符串
	 */
	public static String alignStr(String str, int len, int direct, int truncWay, char fixStr) {

		if (str == null) return "";

		byte[] b = StringUtil.getBytes(str);
		int l = StringUtil.length(str);
		if (l >= len) {
			if (truncWay == StringUtil.TRUNC_LEFT)
				return new String(b, l - len, len);
			else
				return new String(b, 0, len);
		}

		StringBuffer sb = new StringBuffer(str);
		for (int i = l; i < len; i++) {
			if (direct == StringUtil.LEFT_SPACE)
				sb = sb.insert(0, fixStr);
			else
				sb = sb.append(fixStr);
		}
		return sb.substring(0);
	}

	/**
	 * 该方法计算字符串(包括中文)的实际长度.
	 * 
	 * @param str 需要计算长度的字符串
	 * @return int 字符串的实际长度
	 */
	public static int length(String str) {

		if (str == null) return 0;
		try {
			return new String(str.getBytes("GBK"), "8859_1").length();
		}
		catch (UnsupportedEncodingException e) {
			return -1;
		}
	}

	public static byte[] getBytes(String str) {
		try {
			return str.getBytes("GBK");
		}
		catch (UnsupportedEncodingException e) {
			return "".getBytes();
		}
	}

	/**
	 * 检查SQL中的动态字符值中是否存在'号,如果存在，处理该值并返回处理后的字符串，使其可以在SQL语句中使用
	 * 
	 * @param str
	 * @return
	 */
	public static String checkSqlValue(String str) {
		if (str == null) return str;
		String cstr = str;
		int pos = cstr.indexOf("'");

		while (pos >= 0) {
			cstr = cstr.substring(0, pos + 1) + "'" + cstr.substring(pos + 1);
			pos = cstr.indexOf("'", pos + 2);
		}
		return cstr;
	}

	/**
	 * 分割字符串
	 * 
	 * @param str 被分割字符串
	 * @param delim 分割符
	 * @param length 分割后的字符串数量
	 * @return 分割后的字符串数组
	 */
	public static String[] stringTokenizer(String str, String delim, int length) {
		StringTokenizer stk = new StringTokenizer(str, delim);
		String[] returnStr = new String[length];
		int i = 0;
		while (stk.hasMoreTokens()) {
			returnStr[i] = stk.nextToken();
			i++;
		}
		return returnStr;
	}

	/**
	 * 判断两字符串trim后是否相等
	 * 
	 * @param src
	 * @param dest
	 * @return
	 */
	public static boolean isEqualAfterTrim(String src, String dest) {
		if (null == src || null == dest) return false;

		return trim(src).equals(trim(dest));

	}

	/**
	 * 判空
	 * 
	 * @param test
	 * @return
	 */
	public static boolean isEmpty(String test) {
		return null == test || 0 == test.trim().length();
	}
}