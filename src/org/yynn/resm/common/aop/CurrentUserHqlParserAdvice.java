package org.yynn.resm.common.aop;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;
import org.yynn.resm.web.spring.controller.CurrentProjectNoHolder;

/**
 * <p>
 * Title: CurrentUserHqlParserAdvice.java
 * </p>
 * <p>
 * Description: 将HSQL中的当前用户标识替换为具体的用户ID
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created Jun 8, 2007
 */
public class CurrentUserHqlParserAdvice implements MethodBeforeAdvice {
	public static final String CURRENT_USER_ID_IN_HQL = "_CURRENT_USER_ID_";

	private String userIdInHQL = CURRENT_USER_ID_IN_HQL;

	/**
	 * @return the userIdInHQL
	 */
	public String getUserIdInHQL() {
		return userIdInHQL;
	}

	/**
	 * @param userIdInHQL the userIdInHQL to set
	 */
	public void setUserIdInHQL(String userIdInHQL) {
		this.userIdInHQL = userIdInHQL;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.aop.MethodBeforeAdvice#before(java.lang.reflect.Method, java.lang.Object[],
	 *      java.lang.Object)
	 */
	public void before(Method method, Object[] args, Object target) throws Throwable {
		String queryHql = (String) args[0];

		if (null != queryHql && -1 != queryHql.indexOf(userIdInHQL)) {
			args[0] = queryHql.replaceAll(userIdInHQL, CurrentProjectNoHolder.getCurrentProjectNo());
		}
	}

}
