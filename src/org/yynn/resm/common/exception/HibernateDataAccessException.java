package org.yynn.resm.common.exception;

/**
 * <p>
 * Title: DataAccessException
 * </p>
 * <p>
 * Description:���ݿ��ȡ�쳣�࣬�漰�����ݿ��ȡ���ֵ��쳣��
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created 2007-5-20
 */
public class HibernateDataAccessException extends org.springframework.dao.DataAccessException {

	public HibernateDataAccessException(String message) {
		super(message);
	}

	public HibernateDataAccessException(String message, Throwable ex) {
		super(message, ex);
	}
}
