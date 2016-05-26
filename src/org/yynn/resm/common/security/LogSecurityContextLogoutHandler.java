package org.yynn.resm.common.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acegisecurity.Authentication;
import org.acegisecurity.ui.logout.SecurityContextLogoutHandler;

/**
 * <p>
 * Title: LogSecurityContextLogoutHandler.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created Jun 6, 2007
 */
public class LogSecurityContextLogoutHandler extends SecurityContextLogoutHandler {
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.acegisecurity.ui.logout.SecurityContextLogoutHandler#logout(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, org.acegisecurity.Authentication)
	 */
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) {
		LoginInfo li = (LoginInfo) request.getSession().getAttribute(
				LogSessionAttributeListener.LOGIN_INFO_IN_SESSION);
		if (null != li) li.setAutoLogout(false);

		super.logout(request, response, authentication);
	}

}
