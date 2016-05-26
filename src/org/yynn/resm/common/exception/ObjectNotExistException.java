package org.yynn.resm.common.exception;

/**
 * <p>
 * Title: ObjectNotExistException
 * </p>
 * <p>
 * Description:数据库操作异常类，查询某一条数据不存在时抛出
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created 2007-5-20
 */
public class ObjectNotExistException extends BaseException {
	public ObjectNotExistException(String message) {
		super(message);
	}
}
