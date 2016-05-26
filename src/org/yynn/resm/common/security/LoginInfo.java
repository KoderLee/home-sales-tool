package org.yynn.resm.common.security;

import java.io.Serializable;

import org.yynn.resm.common.util.CalendarUtil;

/**
 * <p>
 * Title: LoginInfo.java
 * </p>
 * <p>
 * Description: 封装用户登录信息
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
		return "用户[" + userName + "],于[" + CalendarUtil.getCurrentTimeStr("yyyy-MM-dd HH:mm:ss")
				+ "]使用IP[" + userHost + "]成功登录.";
	}

	public String getLoginFailLog() {
		return "用户[" + userName + "],于[" + CalendarUtil.getCurrentTimeStr("yyyy-MM-dd HH:mm:ss")
				+ "]使用IP[" + userHost + "]登录失败.";
	}

	public String getLogoutLog() {
		if (isAutoLogout)
			return "用户[" + userName + "],于[" + CalendarUtil.getCurrentTimeStr("yyyy-MM-dd HH:mm:ss")
					+ "]位于IP[" + userHost + "]自动退出.";
		else
			return "用户[" + userName + "],于[" + CalendarUtil.getCurrentTimeStr("yyyy-MM-dd HH:mm:ss")
					+ "]使用IP[" + userHost + "]成功退出.";
	}
}
