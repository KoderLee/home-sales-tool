package org.yynn.resm.common.exception;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.hibernate.HibernateException;

/**
 * <p>
 * Title: DAO���쳣���ش���
 * </p>
 * <p>
 * Description: ��������Hibernate���׳����쳣����װΪͳһ���쳣DataAccessException
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
			throw new HibernateDataAccessException("���ݿ�����쳣.", ex);
		}
	}

}
