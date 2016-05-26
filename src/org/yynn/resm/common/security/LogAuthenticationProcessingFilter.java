package org.yynn.resm.common.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acegisecurity.Authentication;
import org.acegisecurity.AuthenticationException;
import org.acegisecurity.ui.webapp.AuthenticationProcessingFilter;
import org.apache.log4j.Logger;

/**
 * <p>
 * Title: LogAuthenticationProcessingFilter.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created Jun 6, 2007
 */
public class LogAuthenticationProcessingFilter extends AuthenticationProcessingFilter {

	private static final Logger log = Logger.getLogger(LogAuthenticationProcessingFilter.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.acegisecurity.ui.AbstractProcessingFilter#onSuccessfulAuthentication(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, org.acegisecurity.Authentication)
	 */
	@Override
	protected void onSuccessfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication) throws IOException {
		request.getSession().setAttribute(LogSessionAttributeListener.LOGIN_INFO_IN_SESSION,
				new LoginInfo(authentication.getName(), request.getRemoteHost()));
		super.onSuccessfulAuthentication(request, response, authentication);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.acegisecurity.ui.AbstractProcessingFilter#onUnsuccessfulAuthentication(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, org.acegisecurity.AuthenticationException)
	 */
	@Override
	protected void onUnsuccessfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException ae) throws IOException {
		Authentication auth = ae.getAuthentication();
		log.info((new LoginInfo(null == auth ? "null" : auth.getName(), request.getRemoteHost()))
				.getLoginFailLog());
		super.onUnsuccessfulAuthentication(request, response, ae);
	}

}
