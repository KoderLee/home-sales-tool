package org.yynn.resm.common.security;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import org.apache.log4j.Logger;

/**
 * <p>
 * Title: LogSessionAttributeListener.java
 * </p>
 * <p>
 * Description: 处理登录及退出信息的记录
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created Jun 6, 2007
 */
public class LogSessionAttributeListener implements HttpSessionAttributeListener {
	public static final String LOGIN_INFO_IN_SESSION = "LOGIN_INFO_IN_SESSION";

	public static final String LOGOUT_MANUAL_FLAG_IN_SESSION = "LOGOUT_MANUAL_FLAG_IN_SESSION";

	private static Logger log = Logger.getLogger(LogSessionAttributeListener.class);

	/*
	 * @see javax.servlet.http.HttpSessionAttributeListener#attributeAdded(javax.servlet.http.HttpSessionBindingEvent)
	 */
	public void attributeAdded(HttpSessionBindingEvent event) {
		String name = event.getName();
		Object value = event.getValue();
		if (name.equals(LOGIN_INFO_IN_SESSION)) log.info(((LoginInfo) value).getLoginLog());
	}

	/*
	 * @see javax.servlet.http.HttpSessionAttributeListener#attributeRemoved(javax.servlet.http.HttpSessionBindingEvent)
	 */
	public void attributeRemoved(HttpSessionBindingEvent event) {
		String name = event.getName();
		Object value = event.getValue();
		if (name.equals(LOGIN_INFO_IN_SESSION)) {
			log.info(((LoginInfo) value).getLogoutLog());
		}
	}

	/*
	 * @see javax.servlet.http.HttpSessionAttributeListener#attributeReplaced(javax.servlet.http.HttpSessionBindingEvent)
	 */
	public void attributeReplaced(HttpSessionBindingEvent arg0) {

	}

}
