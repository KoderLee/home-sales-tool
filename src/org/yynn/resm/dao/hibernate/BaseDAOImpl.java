package org.yynn.resm.dao.hibernate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.yynn.resm.common.util.PageListData;
import org.yynn.resm.dao.BaseDAO;

/**
 * <p>
 * Title: BaseDAOImpl
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created 2007-5-24
 */
public class BaseDAOImpl extends HibernateDaoSupport implements BaseDAO {
  protected final Log log = LogFactory.getLog(getClass());

  /**
   * @param ht
   * @param queryObject
   */
  private void prepareQuery(HibernateTemplate ht, Query queryObject) {
    if (ht.isCacheQueries()) {
      queryObject.setCacheable(true);
      if (ht.getQueryCacheRegion() != null) {
        queryObject.setCacheRegion(ht.getQueryCacheRegion());
      }
    }
    SessionFactoryUtils.applyTransactionTimeout(queryObject, getSessionFactory());
  }

  /**
   * @param queryObject
   * @param paramName
   * @param value
   * @throws HibernateException
   */
  private void applyNamedParameterToQuery(Query queryObject, String paramName, Object value)
      throws HibernateException {
    if (value instanceof Collection) {
      queryObject.setParameterList(paramName, (Collection) value);
    } else if (value instanceof Object[]) {
      queryObject.setParameterList(paramName, (Object[]) value);
    } else {
      queryObject.setParameter(paramName, value);
    }
  }

  /*
   * ���� Javadoc��
   * 
   * @see com.ccae.aomis.common.dao.IDAO#bulkByNamedParam(java.lang.String,
   *      java.lang.String[], java.lang.Object[])
   */
  public int bulkByNamedParam(final String queryString, final String[] paramNames, final Object[] values) {
    if (paramNames.length != values.length) {
      throw new IllegalArgumentException("��������ɾ��ʱ������ı�����ֵ���ȱ���һ��");
    }
    final HibernateTemplate ht = getHibernateTemplate();
    Integer bulkCount = (Integer) (getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) {
        Query queryObject = session.createQuery(queryString);
        prepareQuery(ht, queryObject);
        if (values != null) {
          for (int i = 0; i < values.length; i++) {
            applyNamedParameterToQuery(queryObject, paramNames[i], values[i]);
          }
        }
        return new Integer(queryObject.executeUpdate());

      }
    }));
    return bulkCount.intValue();
  }

  /*
   * ���� Javadoc��
   * 
   * @see com.ccae.aomis.common.dao.IDAO#bulkByNamedParam(java.lang.String)
   */
  public int bulkByNamedParam(final String queryString) {
    Integer bulkCount = (Integer) (getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) {
        int count = session.createQuery(queryString).executeUpdate();
        return new Integer(count);
      }
    }));
    return bulkCount.intValue();
  }

  /*
   * ���� Javadoc��
   * 
   * @see com.ccae.aomis.common.dao.IDAO#findByNamedParam(java.lang.String,
   *      java.lang.String[], java.lang.Object[])
   */
  public List findByNamedParam(final String queryString, final String[] paramNames, final Object[] values) {
    return getHibernateTemplate().findByNamedParam(queryString, paramNames, values);
  }

  /**
   * @see org.appfuse.dao.IDAO#saveObject(java.lang.Object)
   */
  public void saveObject(Object o) {
    getHibernateTemplate().saveOrUpdate(o);
  }

  /**
   * @see org.appfuse.dao.IDAO#getObject(java.lang.Class, java.io.Serializable)
   */
  public Object getObject(Class clazz, Serializable id) {
    Object o = getHibernateTemplate().get(clazz, id);

    if (o == null) {
      throw new ObjectRetrievalFailureException(clazz, id);
    }

    return o;
  }

  /**
   * @see org.appfuse.dao.IDAO#getObjects(java.lang.Class)
   */
  public List getObjects(Class clazz) {
    return getHibernateTemplate().loadAll(clazz);
  }

  /*
   * ���� Javadoc��
   * 
   * @see com.ccae.aomis.common.dao.IDAO#removeObject(java.lang.Class,
   *      java.io.Serializable)
   */
  public void removeObject(Class clazz, Serializable id) {
    getHibernateTemplate().delete(getObject(clazz, id));
  }

  /*
   * ���� Javadoc��
   * 
   * @see com.ccae.aomis.common.dao.IDAO#find(java.lang.String)
   */
  public List find(final String queryString) {
    return getHibernateTemplate().find(queryString);
  }

  /*
   * ���� Javadoc��
   * 
   * @see com.ccae.aomis.common.dao.IDAO#queryBySql(java.lang.String)
   */
  public List queryBySql(String hsql) {
    List list = new ArrayList();
    Session session = getSessionFactory().openSession();
    Query query = session.createSQLQuery(hsql);
    list = query.list();
    return list;
  }

  /**
   * 
   * ��ѯ��ָ��ҳ�ļ�¼
   * 
   * @param hql
   *          ��ѯ���е�hql���
   * @param pageListData
   *          ��ҳ��
   * @return ָ��ҳ�ļ�¼��
   */
  public PageListData queryByPage(String hql, PageListData pageListData) {
    List list = doQueryByPage(hql, pageListData);

    while (list.size() == 0 && pageListData.getCount() > 0) {
      pageListData.setPages(pageListData.getPages() - 1);
      list = doQueryByPage(hql, pageListData);
    }

    pageListData.setDataArray(list);
    return pageListData;
  }

  private List doQueryByPage(String hql, PageListData pageListData) {
    // ��SessionFactory�л��Session
    Session session = getSessionFactory().openSession();
    try {
      // ��pageListData��ȡ��ҳ��
      int page = pageListData.getPages();
      // ��pageListData��ȡ��ÿҳ��¼����
      int pageSize = pageListData.getPageSize();
      // ����Query����
      Query query = session.createQuery(hql);
      // ȡ�����м�¼����
      pageListData.setCount(query.list().size());
      // ���ü�¼��ʼλ��
      query.setFirstResult((page - 1) * pageSize);
      // ���ò�ѯ��¼����
      query.setMaxResults(pageSize);
      List list = query.list();
      // ���ز�ѯ���
      return list;
    } catch (RuntimeException rex) {
      throw rex;
    } finally {
      // �ر�Session
      session.close();
    }
  }
}