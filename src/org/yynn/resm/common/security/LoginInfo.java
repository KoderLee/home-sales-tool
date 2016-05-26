package org.yynn.resm.common.security;

import java.io.Serializable;

import org.yynn.resm.common.util.CalendarUtil;

/**
 * <p>
 * Title: LoginInfo.java
 * </p>
 * <p>
 * Description: ��װ�û���¼��Ϣ
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created Jun 6, 2007
 */
public class LoginInfo implements Serializable {
	private String userName;

	private String userHost;

	private boolean isAutoLogout = true;

	/**
	 * @return the isAutoLogout
	 */
	public boolean isAutoLogout() {
		return isAutoLogout;
	}

	/**
	 * @param isAutoLogout the isAutoLogout to set
	 */
	public void setAutoLogout(boolean isAutoLogout) {
		this.isAutoLogout = isAutoLogout;
	}

	public LoginInfo(String userName, String userHost) {
		this.userName = userName;
		this.userHost = userHost;
	}

	public String getLoginLog() {
		return "�û�[" + userName + "],��[" + CalendarUtil.getCurrentTimeStr("yyyy-MM-dd HH:mm:ss")
				+ "]ʹ��IP[" + userHost + "]�ɹ���¼.";
	}

	public String getLoginFailLog() {
		return "�û�[" + userName + "],��[" + CalendarUtil.getCurrentTimeStr("yyyy-MM-dd HH:mm:ss")
				+ "]ʹ��IP[" + userHost + "]��¼ʧ��.";
	}

	public String getLogoutLog() {
		if (isAutoLogout)
			return "�û�[" + userName + "],��[" + CalendarUtil.getCurrentTimeStr("yyyy-MM-dd HH:mm:ss")
					+ "]λ��IP[" + userHost + "]�Զ��˳�.";
		else
			return "�û�[" + userName + "],��[" + CalendarUtil.getCurrentTimeStr("yyyy-MM-dd HH:mm:ss")
					+ "]ʹ��IP[" + userHost + "]�ɹ��˳�.";
	}
}
