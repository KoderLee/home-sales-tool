package org.yynn.resm.common.exception;

/**
 * <p>
 * Title: DataOperationException
 * </p>
 * <p>
 * Description:���ݿ��ȡ�쳣�࣬�漰�����ݿ��ȡ���ֵ��쳣��
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created 2007-5-20
 */
public class DataOperationException extends BaseException {
	public DataOperationException(String message) {
		super(message);
	}

	public DataOperationException(Throwable ex) {
		super(ex);
	}
}