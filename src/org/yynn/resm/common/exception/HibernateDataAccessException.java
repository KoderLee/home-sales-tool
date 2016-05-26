package org.yynn.resm.common.exception;

/**
 * <p>
 * Title: DataAccessException
 * </p>
 * <p>
 * Description:数据库存取异常类，涉及到数据库存取出现的异常。
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
