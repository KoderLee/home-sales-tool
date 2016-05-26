package org.yynn.resm.common.exception;

/**
 * <p>
 * Title: ObjectAlreadyDeletedException
 * </p>
 * <p>
 * Description:对象已删除异常
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created 2007-5-20
 */
public class ObjectAlreadyDeletedException extends BaseException {
	private Object object = null;

	public ObjectAlreadyDeletedException(String message) {
		super(message);
	}

	public ObjectAlreadyDeletedException(Throwable ex) {
		super(ex);
	}

	/**
	 * 放入已存在的删除记录
	 * 
	 * @param object 已删除记录
	 */
	public void setAlreadyDeletedObject(Object object) {
		this.object = object;
	}

	public Object getObject() {
		return object;
	}
}
