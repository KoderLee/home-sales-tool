package org.yynn.resm.common.exception;

/**
 * <p>
 * Title: ObjectCompletenessException
 * </p>
 * <p>
 * Description:数据完整性异常
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created 2007-5-20
 */
public class ObjectCompletenessException extends BaseException {
	public ObjectCompletenessException(String message) {
		super(message);
	}

	public ObjectCompletenessException(Throwable ex) {
		super(ex);
	}
}
