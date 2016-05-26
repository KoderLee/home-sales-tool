package org.yynn.resm.dao;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * <p>
 * Title: CrudDAO.java
 * </p>
 * <p>
 * Description: CRUDDAO
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created May 17, 2007
 */
public interface CrudDAO<T, PK extends Serializable> {
	
	public Class<T> getModelType();
  
  public Class<PK> getPkType();
	
  /**
   * ��������Ϊid�����ݶ���
   * @param id
   * @return
   */
	public T load(PK id);

  /**
   * �����ݿ�д��model
   * @param model
   * @return
   */
	public PK create(T model);

  /**
   * �޸�������model��Ӧ������
   * @param model
   */
	public void update(T model);

	/**
   * ɾ��������id��Ӧ������ 
	 * @param id
	 */
	public void delete(PK id);
	
  /**
   * ����hsql����ѯ����
   * 
   * @param hsql
   * @return
   */
	public ArrayList<T> query(String hsql);
}
