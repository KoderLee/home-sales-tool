package org.yynn.resm.common.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.acegisecurity.AcegiMessageSource;
import org.acegisecurity.AuthenticationCredentialsNotFoundException;
import org.acegisecurity.context.SecurityContextHolder;
import org.springframework.context.support.MessageSourceAccessor;

/**
 * <p>
 * Title: SecurityEnforcementFilter.java
 * </p>
 * <p>
 * Description:认证入口
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created Apr 23, 2007
 */
public class SecurityEnforcementFilter implements Filter {
	protected MessageSourceAccessor messages = AcegiMessageSource.getAccessor();

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
		// nothing to do.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse,
	 *      javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (SecurityContextHolder.getContext().getAuthentication() == null) {
			credentialsNotFound(messages.getMessage("AbstractSecurityInterceptor.authenticationNotFound",
					"An Authentication object was not found in the SecurityContext"));
		}

		chain.doFilter(request, response);
	}

	/**
	 * Helper method which generates an exception containing the passed reason, and publishes an event to the application
	 * context.
	 * <p>
	 * Always throws an exception.
	 * </p>
	 * 
	 * @param reason to be provided in the exception detail
	 * @param secureObject that was being called
	 * @param configAttribs that were defined for the secureObject
	 */
	private void credentialsNotFound(String reason) {
		AuthenticationCredentialsNotFoundException exception = new AuthenticationCredentialsNotFoundException(
				reason);

		throw exception;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig arg0) throws ServletException {
		// nothing to do ...
	}

}
