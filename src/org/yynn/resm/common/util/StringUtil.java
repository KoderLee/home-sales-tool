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

	/** ���ַ�����ಹ��ո� */
	public final static int LEFT_SPACE = 0;

	/** ���ַ����Ҳಹ��ո� */
	public final static int RIGHT_SPACE = 1;

	/** ����ַ���ʵ�ʳ��ȳ���ָ�����ȣ������ض� */
	public final static int TRUNC_LEFT = 0;

	/** ����ַ���ʵ�ʳ��ȳ���ָ�����ȣ����Ҳ�ض� */
	public final static int TRUNC_RIGHT = 1;

	/**
	 * �÷���ȥ���ַ�������߿ո�
	 * 
	 * @param String ��Ҫȥ����߿ո���ַ���
	 * @return String �Ѿ�ȥ����߿ո���ַ���
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
	 * �ķ���ȥ���ַ������ұ߿ո�
	 * 
	 * @param String ��Ҫȥ���ұ߿ո���ַ���
	 * @return String �Ѿ�ȥ���ұ߿ո���ַ���
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
	 * ���ַ��������еĿո�ɾ����������ߡ��ұߡ��м䡣
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
	 * �÷��������жϴ�����ַ����Ƿ���null,�����null�򷵻�"",����Դ�����ַ���ִ��trim�����󷵻�trim�Ľ��
	 * 
	 * @param String ��Ҫ���д�����ַ���
	 * @return String ������ɵĽ���ַ���
	 */
	public static String trim(String orin) {
		if (orin == null) {
			orin = "";
		}
		return orin.trim();
	}

	/**
	 * �÷�����������ַ�������Ϊָ�����ȵ��ַ���,���Ȳ���Ļ�,����ո� ����ַ������ȴ���ָ���ĳ���,�ͽضϳ������ǲ����ַ�.
	 * 
	 * @param str ��Ҫ������ַ���
	 * @param len ָ�����ַ�������
	 * @param direct StringUtil.LEFT_SPACE ����ಹ��ո� StringUtil.RIGHT_SPACE ���Ҳಹ��ո�
	 * @param truncWay StringUtil.TRUNC_LEFT �����ضϿո� StringUtil.TRUNC_RIGHT ���Ҳ�ضϿո�
	 * @return ����ָ����ʽ���ַ���
	 */
	public static String alignStr(String str, int len, int direct, int truncWay) {

		return alignStr(str, len, direct, truncWay, ' ');

	}

	/**
	 * �÷�����������ַ�������Ϊָ�����ȵ��ַ���,���Ȳ���Ļ�,�� fixStr ���� ����ַ������ȴ���ָ���ĳ���,�ͽضϳ������ǲ����ַ�.
	 * 
	 * @param str ��Ҫ������ַ���
	 * @param len ָ�����ַ�������
	 * @param direct StringUtil.LEFT_SPACE ����ಹ��ո� StringUtil.RIGHT_SPACE ���Ҳಹ��ո�
	 * @param truncWay StringUtil.TRUNC_LEFT �����ضϿո� StringUtil.TRUNC_RIGHT ���Ҳ�ضϿո�
	 * @param fixStr �������ĵ��ַ�
	 * @return ����ָ����ʽ���ַ���
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
	 * �÷��������ַ���(��������)��ʵ�ʳ���.
	 * 
	 * @param str ��Ҫ���㳤�ȵ��ַ���
	 * @return int �ַ�����ʵ�ʳ���
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
	 * ���SQL�еĶ�̬�ַ�ֵ���Ƿ����'��,������ڣ������ֵ�����ش������ַ�����ʹ�������SQL�����ʹ��
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
	 * �ָ��ַ���
	 * 
	 * @param str ���ָ��ַ���
	 * @param delim �ָ��
	 * @param length �ָ����ַ�������
	 * @return �ָ����ַ�������
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
	 * �ж����ַ���trim���Ƿ����
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
	 * �п�
	 * 
	 * @param test
	 * @return
	 */
	public static boolean isEmpty(String test) {
		return null == test || 0 == test.trim().length();
	}
}