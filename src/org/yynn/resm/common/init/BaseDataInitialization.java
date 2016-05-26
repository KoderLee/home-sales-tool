package org.yynn.resm.common.init;

import java.io.Serializable;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;
import org.yynn.resm.dao.CrudDAO;

/**
 * <p>
 * Title: BaseDataInitialization
 * </p>
 * <p>
 * Description: �������ݳ�ʼ��
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created 2007-6-2
 */
public class BaseDataInitialization<T, PK extends Serializable> implements InitializingBean {
  private static final Logger logger = Logger.getLogger(BaseDataInitialization.class);

  private boolean initData = false;

  private CrudDAO<T, PK> dao;

  private ArrayList<T> dataList;

  /**
   * @return the dataList
   */
  public ArrayList<T> getDataList() {
    return dataList;
  }

  /**
   * @param dataList
   *          the dataList to set
   */
  public void setDataList(ArrayList dataList) {
    this.dataList = dataList;
  }

  /**
   * @return the dao
   */
  public CrudDAO<T, PK> getDao() {
    return dao;
  }

  public BaseDataInitialization(CrudDAO<T, PK> dao) {
    this.dao = dao;
  }

  public void init() {
    if (initData) {
      Class type = dao.getModelType();
      if (dao.query("from " + type.getName()).size() > 0) {
        logger.warn("���ݱ�[" + type.getName().substring(type.getPackage().getName().length() + 1)
            + "]���Ѵ�������,���ݳ�ʼ��������,�������ݿ��Ƿ��ѳ�ʼ��.");
        return;
      }

      for (T model : dataList) {
        dao.create(model);

      }
    }
  }

  /*
   * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
   */
  public void afterPropertiesSet() throws Exception {
    Assert.notNull(dao, "dao required!");

  }

  /**
   * @return the initData
   */
  public boolean isInitData() {
    return initData;
  }

  /**
   * @param initData
   *          the initData to set
   */
  public void setInitData(boolean initData) {
    this.initData = initData;
  }

}
