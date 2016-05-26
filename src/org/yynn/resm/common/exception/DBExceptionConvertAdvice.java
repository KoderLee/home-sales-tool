package org.yynn.resm.common.exception;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.hibernate.HibernateException;

/**
 * <p>
 * Title: DAO层异常拦截处理
 * </p>
 * <p>
 * Description: 拦截所有Hibernate层抛出的异常，封装为统一的异常DataAccessException
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created 2007-5-20
 */
public class DBExceptionConvertAdvice implements MethodInterceptor {

	/*
	 * @see org.aopalliance.intercept.MethodInterceptor#invoke(org.aopalliance.intercept.MethodInvocation)
	 */
	public Object invoke(MethodInvocation invocation) throws Throwable {
		try {
			Object object = invocation.proceed();
			return object;
		}
		catch (HibernateException ex) {
			throw new HibernateDataAccessException("数据库访问异常.", ex);
		}
	}

}
