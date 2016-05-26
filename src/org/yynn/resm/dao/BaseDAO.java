package org.yynn.resm.dao;

import java.io.Serializable;
import java.util.List;

import org.yynn.resm.common.util.PageListData;


/**
 * <p>
 * Title: BaseDAO
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created 2007-5-24
 */
public interface BaseDAO {
  // ���Ӳ���������������ɾ��
  public int bulkByNamedParam(final String queryString, final String[] paramNames, final Object[] values);

  // ��������ɾ��
  public int bulkByNamedParam(final String queryString);

  // ���Ӳ�������ѯ��
  List findByNamedParam(final String queryString, final String[] paramNames, final Object[] values);

  // ������ѯ
  public List find(final String queryString);

  /**
   * Generic method used to get all objects of a particular type. This is the
   * same as lookup up all rows in a table.
   * 
   * @param clazz
   *          the type of objects (a.k.a. while table) to get data from
   * @return List of populated objects
   */
  // ��ѯ����
  public List getObjects(Class clazz);

  /**
   * Generic method to get an object based on class and identifier. An
   * ObjectRetrievalFailureException Runtime Exception is thrown if nothing is
   * found.
   * 
   * @param clazz
   *          model class to lookup
   * @param id
   *          the identifier (primary key) of the class
   * @return a populated object
   * @see org.springframework.orm.ObjectRetrievalFailureException
   */
  // ����������ѯ����
  public Object getObject(Class clazz, Serializable id);

  /**
   * Generic method to save an object - handles both update and insert.
   * 
   * @param o
   *          the object to save
   */
  // ���±������
  public void saveObject(Object o);

  /**
   * Generic method to delete an object based on class and id
   * 
   * @param clazz
   *          model class to lookup
   * @param id
   *          the identifier (primary key) of the class
   */
  // ��������ɾ������
  public void removeObject(Class clazz, Serializable id);

  // ����sql��ѯ����ȡ��¼
  public List queryBySql(String hsql);

  // ��ҳ��ѯ
  public PageListData queryByPage(String hql, PageListData pageListData);
}
