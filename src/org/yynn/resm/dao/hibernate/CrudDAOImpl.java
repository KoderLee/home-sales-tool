package org.yynn.resm.dao.hibernate;

import java.io.Serializable;
import java.util.ArrayList;

import org.yynn.resm.dao.CrudDAO;

/**
 * <p>
 * Title: CrudDAOImpl.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created May 17, 2007
 */
public class CrudDAOImpl<T, PK extends Serializable> extends BaseDAOImpl implements
    CrudDAO<T, PK> {
  private Class<T> modelType;

  private Class<PK> pkType;

  public CrudDAOImpl(Class<T> modelType, Class<PK> pkType) {
    this.modelType = modelType;
    this.pkType = pkType;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.yynn.resm.dao.CrudDAO#create(java.lang.Object)
   */
  public PK create(T model) {
    return (PK) getHibernateTemplate().save(model);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.yynn.resm.dao.CrudDAO#delete(java.lang.Object)
   */
  public void delete(PK id) {
    getHibernateTemplate().delete(load(id));

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.yynn.resm.dao.CrudDAO#load(java.io.Serializable)
   */
  public T load(PK id) {
    return (T) getHibernateTemplate().load(modelType, id);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.yynn.resm.dao.CrudDAO#update(java.lang.Object)
   */
  public void update(T model) {
    getHibernateTemplate().update(model);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.yynn.resm.dao.CrudDAO#query(java.lang.String)
   */
  public ArrayList<T> query(String hsql) {
    return (ArrayList<T>) getHibernateTemplate().find(hsql);
  }

	/**
	 * @return the pkType
	 */
	public Class<PK> getPkType() {
		return pkType;
	}

	/**
	 * @param pkType the pkType to set
	 */
	public void setPkType(Class<PK> pkType) {
		this.pkType = pkType;
	}

	/**
	 * @return the modelType
	 */
	public Class<T> getModelType() {
		return modelType;
	}

	/**
	 * @param modelType the modelType to set
	 */
	public void setModelType(Class<T> modelType) {
		this.modelType = modelType;
	}

}
