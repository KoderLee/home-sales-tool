package org.yynn.resm.service.impl;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.beans.factory.InitializingBean;
import org.yynn.resm.common.QueryDataEntry;
import org.yynn.resm.dao.CrudDAO;
import org.yynn.resm.service.CrudService;

/**
 * <p>
 * Title: CrudServiceImpl.java
 * </p>
 * <p>
 * Description: CRUD业务实现
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created May 23, 2007
 */
public class CrudServiceImpl<T, PK extends Serializable> implements CrudService<T, PK>, InitializingBean {
  /*
   * (non-Javadoc)
   * 
   * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
   */
  public void afterPropertiesSet() throws Exception {
    if (null == hsqlPreffix) {
      StringBuffer qhsql = new StringBuffer("from ");
      qhsql.append(crudDao.getModelType().getName());
      qhsql.append(" as ").append(asName);
      qhsql.append(" where (1=1) ");

      this.hsqlPreffix = qhsql.toString();
    }
  }

  private CrudDAO<T, PK> crudDao;

  private String hsqlPreffix;

  private String asName = "model";

  private String queryAppend;

  public CrudServiceImpl(CrudDAO<T, PK> crudDao) {
    this.crudDao = crudDao;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.yynn.resm.service.CrudService#create(java.lang.Object)
   */
  public PK create(T model) {
    return crudDao.create(model);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.yynn.resm.service.CrudService#delete(java.lang.Object)
   */
  public void delete(PK id) {
    crudDao.delete(id);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.yynn.resm.service.CrudService#query(java.util.ArrayList)
   */
  public ArrayList<T> query(ArrayList<QueryDataEntry> qoList) {
    StringBuffer qhsql = new StringBuffer(hsqlPreffix);
    for (QueryDataEntry qo : qoList) {
      String temp = qo.generateHql();
      if (null != temp) {
        qhsql.append("and ").append(asName).append(".");
        qhsql.append(temp);
      }
    }
    if (null != queryAppend)
      qhsql.append("and ").append(queryAppend);
    return crudDao.query(qhsql.toString());
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.yynn.resm.service.CrudService#read(java.io.Serializable)
   */
  public T read(PK id) {
    return crudDao.load(id);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.yynn.resm.service.CrudService#update(java.lang.Object)
   */
  public void update(T model) {
    crudDao.update(model);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.yynn.resm.service.CrudService#getModelType()
   */
  public Class<T> getModelType() {
    return crudDao.getModelType();
  }

  /**
   * @return the hsqlPreffix
   */
  public String getHsqlPreffix() {
    return hsqlPreffix;
  }

  /**
   * @param hsqlPreffix
   *          the hsqlPreffix to set
   */
  public void setHsqlPreffix(String hsqlPreffix) {
    this.hsqlPreffix = hsqlPreffix;
  }

  /**
   * @return the asName
   */
  public String getAsName() {
    return asName;
  }

  /**
   * @param asName
   *          the asName to set
   */
  public void setAsName(String asName) {
    this.asName = asName;
  }

  /**
   * @return the queryAppend
   */
  public String getQueryAppend() {
    return queryAppend;
  }

  /**
   * @param queryAppend
   *          the queryAppend to set
   */
  public void setQueryAppend(String queryAppend) {
    this.queryAppend = queryAppend;
  }

  /*
   * @see org.yynn.resm.service.CrudService#getPKType()
   */
  public Class<PK> getPKType() {
    return crudDao.getPkType();
  }

}
