package org.yynn.resm.common.exception;

/**
 * <p>
 * Title: BaseException
 * </p>
 * <p>
 * Description:所有异常的基类
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created 2007-5-20
 */
public class BaseException extends Exception {
	public BaseException(String message) {
		super(message);
	}

	public BaseException(Throwable ex) {
		super(ex);
	}
}
