package org.yynn.resm.common.exception;

/**
 * <p>
 * Title: ObjectAlreadyExistException
 * </p>
 * <p>
 * Description:数据库操作异常类，新增的记录违反唯一性时抛出
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created 2007-5-20
 */
public class ObjectAlreadyExistException extends BaseException {
	public ObjectAlreadyExistException(String message) {
		super(message);
	}
}
