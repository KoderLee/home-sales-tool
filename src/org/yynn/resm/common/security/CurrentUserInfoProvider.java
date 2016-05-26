package org.yynn.resm.common.security;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.context.SecurityContext;
import org.acegisecurity.context.SecurityContextHolder;
import org.apache.log4j.Logger;

/**
 * <p>
 * Title: CurrentUserInfoProvider
 * </p>
 * <p>
 * Description: 定义提供当前登录用户的信息
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created 2007-6-6
 */
public class CurrentUserInfoProvider {
	private static final Logger log = Logger.getLogger(CurrentUserInfoProvider.class);

	public static final String ROLE_ADMIN = "auth_0";

	public static final String ROLE_COMMANY = "auth_1";

	public static final String ROLE_PROJECT = "auth_2";

	/**
	 * 获取当前用户的用户标识
	 * </p>
	 * 
	 * @return 当前用户的用户标识
	 */
	public static String getUserId() {
		Authentication authentication = getAuthentication();
		if (null != authentication) {
			return authentication.getName();
		}

		log.warn("No Authentication can be found in current thread.");

		return null;
	}

	/**
	 * 获取当前用户的用户标识
	 * </p>
	 * 
	 * @return 当前用户的用户名
	 */
	public static String getUserName() {
		Authentication authentication = getAuthentication();
		if (null != authentication) {
			return ((UserDetailImpl) authentication.getPrincipal()).getUser().getLeaderName();
		}

		log.warn("No Authentication can be found in current thread.");

		return null;
	}

	/**
	 * 获取当前用户的权限信息
	 * 
	 * @return 当前用户的权限信息
	 */
	public static GrantedAuthority[] getAuthorities() {
		Authentication authentication = getAuthentication();
		if (null != authentication) {
			return authentication.getAuthorities();

		}
		log.warn("No Authentication can be found in current thread.");

		return new GrantedAuthority[] {};
	}

	/**
	 * 判断当前用户是否拥有权限authCodes中的某一项
	 * 
	 * @param authCode
	 * @return
	 */
	public static boolean hasAuth(String[] authCodes) {
		if (null == authCodes) return false;
		GrantedAuthority[] auths = getAuthorities();
		for (String requiredCode : authCodes) {
			for (int i = 0, n = auths.length; i < n; i++) {
				if (requiredCode.equals(auths[i].getAuthority())) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 判断当前用户是否拥有权限authCode
	 * 
	 * @param authCode
	 * @return
	 */
	public static boolean hasAuth(String authCode) {
		if (null == authCode) return false;
		GrantedAuthority[] auths = getAuthorities();
		for (int i = 0, n = auths.length; i < n; i++) {
			if (authCode.equals(auths[i].getAuthority())) {
				return true;
			}
		}
		return false;
	}

	private static Authentication getAuthentication() {
		SecurityContext context = SecurityContextHolder.getContext();
		if (null != context) {
			Authentication authentication = context.getAuthentication();
			return authentication;
		}

		log.warn("No SecurityContext can be found in current thread.");

		return null;
	}

	/**
	 * 获取当前用户项目编号[即登陆标识]
	 * 
	 * @return
	 */
	public static String getProjectNo() {
		return getUserId();
	}

}
