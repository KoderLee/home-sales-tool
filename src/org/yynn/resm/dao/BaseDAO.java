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
  // 复杂参数化的批量更新删除
  public int bulkByNamedParam(final String queryString, final String[] paramNames, final Object[] values);

  // 批量更新删除
  public int bulkByNamedParam(final String queryString);

  // 复杂参数化查询。
  List findByNamedParam(final String queryString, final String[] paramNames, final Object[] values);

  // 批量查询
  public List find(final String queryString);

  /**
   * Generic method used to get all objects of a particular type. This is the
   * same as lookup up all rows in a table.
   * 
   * @param clazz
   *          the type of objects (a.k.a. while table) to get data from
   * @return List of populated objects
   */
  // 查询对象
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
  // 根据主键查询对象
  public Object getObject(Class clazz, Serializable id);

  /**
   * Generic method to save an object - handles both update and insert.
   * 
   * @param o
   *          the object to save
   */
  // 更新保存对象
  public void saveObject(Object o);

  /**
   * Generic method to delete an object based on class and id
   * 
   * @param clazz
   *          model class to lookup
   * @param id
   *          the identifier (primary key) of the class
   */
  // 根据主键删除对象
  public void removeObject(Class clazz, Serializable id);

  // 根据sql查询语句获取记录
  public List queryBySql(String hsql);

  // 分页查询
  public PageListData queryByPage(String hql, PageListData pageListData);
}
